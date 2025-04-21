package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2116_주사위_쌓기 {
    static int N;
    static int[][] dices;
    static int[] oppositeSide = {5, 3, 4, 1, 2, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        dices = new int[N][6];
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < 6; m++) dices[n][m] = Integer.parseInt(st.nextToken());
        }
        System.out.println(getOneSideMaxValue(dices));
    }
    static int getOneSideMaxValue(int[][] dices){
        int max = 0;
        // 제일 아래 주사위 지정
        for(int d = 0; d < 6; d++){
            int backMax = 0;
            int bottom = dices[0][d];
            int top = dices[0][oppositeSide[d]];
            backMax += getMaxValueExcludeTopBottom(dices, 0, top, bottom);
            for(int s = 1; s < dices.length; s++){
                int nextTopIndex = 0;
                for(int c = 0; c < 6; c++) {
                    int nextTop = dices[s][c];
                    if(nextTop != top) continue;
                    nextTopIndex = c;
                    break;
                }
                bottom = dices[s][nextTopIndex];
                top = dices[s][oppositeSide[nextTopIndex]];
                backMax += getMaxValueExcludeTopBottom(dices, s, top, bottom);
            }
            max = Math.max(max, backMax);
        }
        return max;
    }
    static int getMaxValueExcludeTopBottom(int[][] dices, int index, int top, int bottom){
        return Arrays.stream(dices[index])
                .filter(v -> v != top && v != bottom)
                .max()
                .orElse(0);
    }
}
