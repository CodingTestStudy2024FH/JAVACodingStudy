import java.util.Scanner;

public class Main {
    static int R, C;
    static char[][] board;
    static boolean[] visited = new boolean[26];
    static int maxCount = 0;
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        board = new char[R][C];
        
        for (int i = 0; i < R; i++) {
            board[i] = sc.next().toCharArray();
        }
        
        // 첫 번째 칸의 알파벳 방문 처리
        visited[board[0][0] - 'A'] = true;
        dfs(0, 0, 1);
        
        System.out.println(maxCount);
    }
    
    static void dfs(int x, int y, int count) {
        maxCount = Math.max(maxCount, count);
        
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                int index = board[nx][ny] - 'A';
                if (!visited[index]) {
                    visited[index] = true;
                    dfs(nx, ny, count + 1);
                    visited[index] = false; // 백트래킹
                }
            }
        }
    }
}
