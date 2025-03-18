package recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2447_별_찍기_10 {
    static int N;
    static char[][] stars;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stars = new char[N][N];
        for(char[] col: stars) Arrays.fill(col, ' ');
        makeStar(N, 0, 0);
        StringBuilder sb = new StringBuilder();
        for(char[] row: stars) sb.append(row).append("\n");
        System.out.println(sb);
    }
    static void makeStar(int depth, int y, int x){
        if(depth == 1) {
//            System.out.printf("(%d, %d) ", y, x);
            stars[y][x] = '*';
            return;
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(i == 1 && j == 1) continue;
                // parameter
                // 27 0 0
                // 9 0 0
                // 3 0 0
                // 1 0 0
                // 3 0 1
                // 1 0 1
                // 3 0 2
                // 1 0 2
                makeStar(depth/3, y + i*depth/3, x + j*depth/3);
            }
//            System.out.println();
        }
    }
}


