package week_3rd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ27211_도넛행성 {
    static int N, M;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] map;
    static boolean[][] visit;

    // 본인의 집 0,0
    // 숲에 막히지 않고 갈 수 있으면 같은 구역임
    // 탐험할 수 있는 빈구역의 개수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];

        int count = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 갈 수 있는 위치인데 방문하지 않았다면
                if (!visit[i][j] && (map[i][j] == 0)) {
                    count++;
                    BFS(i, j);
                }
            }
        }

        System.out.println(count);


    }

    static void BFS(int y, int x) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y, x});
        visit[y][x] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curY = cur[0];
            int curX = cur[1];

            for(int i = 0; i < 4; i++){
                int nextY = curY + dy[i];
                int nextX = curX + dx[i];

                // 범위를 넘어가는 경우 돌아온다
                if(nextY < 0) nextY = N -1;
                else if(nextY >= N) nextY = 0;
                if(nextX < 0) nextX = M -1;
                else if(nextX >= M) nextX = 0;

                if(visit[nextY][nextX]) continue;
                if(map[nextY][nextX] == 1) continue;

                visit[nextY][nextX] = true;
                q.offer(new int[] {nextY, nextX});
            }
        }
    }
}
