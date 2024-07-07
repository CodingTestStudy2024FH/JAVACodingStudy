package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11660_구간_합_구하기_5 {
    static int N, M;
    static long[][] memoi;
    static int[][] array;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memoi = new long[N+1][N+1];
        array = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                array[i][j] = array[i][j-1] + array[i-1][j] - array[i-1][j-1] + Integer.parseInt(st.nextToken());
            }
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            long result = array[x2][y2] - array[x2][y1-1] - array[x1-1][y2] + array[x1-1][y1-1];
            System.out.println(result);
        }

    }
}