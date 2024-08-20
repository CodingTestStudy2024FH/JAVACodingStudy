import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int row, col, max;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static char[][] map;
    static boolean[] visited;
    static int ALL_NUMBER_OF_ALPHABET = 26;
    static int MAKE_CHAR_TO_NUMBER = 65;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new char[row][col];
        visited = new boolean[ALL_NUMBER_OF_ALPHABET];

        for (int i = 0; i < row; i++) map[i] = br.readLine().toCharArray();
        visited[map[0][0] - 65] = true;
        dfs(0, 0, 1);
        System.out.println(max);

    }
    static void dfs(int x, int y, int depth){
        max = Math.max(max, depth);
        for(int d = 0; d < 4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(isOutRangeAndVisited(nx, ny)) continue;
            visited[map[nx][ny] - MAKE_CHAR_TO_NUMBER] = true;
            dfs(nx, ny, depth+1);
            visited[map[nx][ny] - MAKE_CHAR_TO_NUMBER] = false;
        }
    }
    // 범위 밖이거나 방문한적이 있다면 true;
    static boolean isOutRangeAndVisited(int x, int y) {
        return (x < 0 || x >= row || y < 0 || y >= col || visited[map[x][y] - MAKE_CHAR_TO_NUMBER]);
    }
}
