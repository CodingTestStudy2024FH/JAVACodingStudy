import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 0-1 bfs
public class Main {
    static int N, M;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int[][] graph;
    static boolean[][] visited;
    static Deque<Spot> dq = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N = x축, M = y축
        // 입력값이 반대로 되어있었음
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visited = new boolean[N][M];

        for(int n = 0; n < N; n++) graph[n] = strToIntArray(br.readLine());
//        System.out.println(Arrays.deepToString(graph));

        zeroOneBfs();
        System.out.println(graph[N - 1][M - 1]);
    }
    static void zeroOneBfs() {
        dq.addLast(new Spot(0, 0, 0));
        visited[0][0] = true;
        while(!dq.isEmpty()) {
            Spot current = dq.pollFirst();
            for(int d = 0; d < 4; d++){
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];
                if(isOutRange(nx, ny)) continue;
                // 여기서 우선순위별로 앞에 넣고 뒤에 넣고 했었는데...
                if(graph[nx][ny] == 1) {
                    graph[nx][ny] = current.time+1;
                    dq.addLast(new Spot(nx, ny, current.time + 1));
                }
                else {
                    graph[nx][ny] = current.time;
                    dq.addFirst(new Spot(nx, ny, current.time));
                }

                visited[nx][ny] = true;
            }
        }
    }

    static boolean isOutRange(int x, int y){
        return (x < 0 || x >= N || y < 0 || y >= M || visited[x][y]);
    }
    static int[] strToIntArray(String s) {
        int[] arr = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            arr[i] = s.charAt(i) - '0';
        }
        return arr;
    }
    static class Spot{
        int x, y, time;

        public Spot(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public String toString() {
            return String.format("x: %d, y: %d, time: %d", x, y, time);
        }
    }
}
