package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9663_N_Queen {
    static int N, cnt;
    static int[][] queens;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        queens = new int[N][N];
        visited = new boolean[N][N];

        findQueen(0);
        System.out.println(cnt);

    }
    static void findQueen(int row) {
        if(row == N) {
            cnt++;
            return;
        }
        for(int c = 0; c < N; c++) {
            if(isQueen(row, c)){
                visited[row][c] = true;
                findQueen(row+1);
                visited[row][c] = false;
            }
        }
    }
    // 퀸을 넣을 수 있는 자리인지 아닌지 구분
    static boolean isQueen(int row, int col){
        for(int n = 0; n < N; n++) if (visited[n][col]) return false;

        // 대각선 왼쪽
        int cRow = row - 1;
        int cCol = col - 1;

        while(cRow >= 0 && cCol >= 0) if(visited[cRow--][cCol--]) return false;

        // 대각선 오른쪽
        cRow = row - 1;
        cCol = col + 1;

        while(cRow >= 0 && cCol < N) if(visited[cRow--][cCol++]) return false;

        return true;
    }
}