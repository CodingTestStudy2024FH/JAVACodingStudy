import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1198_컴백홈 {

    static int count;
    static char[][] map;
    static int R, C, K;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] isVisit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        isVisit = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // 시작점
        isVisit[R - 1][0] = true;
        dfs(R - 1, 0, 0);

        System.out.println(count);
    }

    static void dfs(int y, int x, int depth) {
        //System.out.println(y + ", " + x + " : " + depth);
        if (depth > K) return;
        if (y == 0 && x == (C - 1) && depth == (K - 1)) {
            count++;
            return;
        }


        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
            if (map[ny][nx] == 'T') continue;
            if (isVisit[ny][nx]) continue;
            isVisit[ny][nx] = true;
            dfs(ny, nx, depth + 1);
            isVisit[ny][nx] = false;
        }

    }
}
