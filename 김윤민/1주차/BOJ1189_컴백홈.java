import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1189_컴백홈 {
    /*
        한수는 집에 돌아가는 방법이 다양하다.
        단, 한수는 똑똑하여 한번 지나친 곳을 다시 방문하지는 않는다. -> visited
     */
    // T : 표시된 부분은 가지 못하는 부분
    // R x C 맵에 못가는 부분
    // 집까지도 도착하는 경우 중 거리가 K인 가짓수
    // K : 거리
    static int R, C, K, ans;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        //input start
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                if (s.charAt(j) == 'T') {
                    map[i][j] = -1;
                }
            }
        }

        //input end

        //거리가 K인 경로의 경우의 수
        visited[R - 1][0] = true;
        dfs(1, R - 1, 0); // 거리는 1부터 시작.
        System.out.println(ans);
    }

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static void dfs(int n, int y, int x) {
        if (y == 0 && x == C - 1 && n == K) {//최종 도착지 map[0][C-1]이고, 거리가 K만큼이면 조건 만족
            ans++; // 경우의 수 추가
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (can(ny, nx) && !visited[ny][nx]) { //갈 수 있고, 방문하지 않은곳이면
                visited[ny][nx] = true; //방문한걸로 치고
                dfs(n + 1, ny, nx); //dfs 진행.
                visited[ny][nx] = false;
            }

        }
    }

    //범위 & 가면 안되는 T의 경우 false 리턴
    static boolean can(int y, int x) {
        if (y < 0 || x < 0 || y >= R || x >= C || map[y][x] == -1) {
            return false;
        }
        return true;
    }


}
