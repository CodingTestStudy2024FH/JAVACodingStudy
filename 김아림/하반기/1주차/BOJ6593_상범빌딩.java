package week_1st;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ6593_상범빌딩
public class BOJ6593_상범빌딩 {
    static int L, R, C;
    static char[][][] map;
    static int[] dx = {0, 0, 0, 0, 1, -1};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {1, -1, 0, 0, 0, 0};
    static int[][][] count;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        while (!(L == 0 && R == 0 && C == 0)) {

            map = new char[L][R][C];
            count = new int[L][R][C];

            int sz, sy, sx;
            int ez, ey, ex;
            sz = sy = sx = 0;
            ez = ey = ex = 0;


            for (int l = 0; l < L; l++) {
                for (int r = 0; r < R; r++) {
                    String str = br.readLine();
                    for (int c = 0; c < C; c++) {
                        char ch = str.charAt(c);
                        map[l][r][c] = ch;
                        if(ch == 'S') {
                            sz = l;
                            sy = r;
                            sx = c;
                        }else if(ch == 'E') {
                            ez = l;
                            ey = r;
                            ex = c;
                        }
                    }
                }
                br.readLine(); // 빈줄
            }

            BFS(sz, sy, sx);
            if(count[ez][ey][ex] == 0) System.out.println("Trapped!");
            else System.out.println("Escaped in " + count[ez][ey][ex] + " minute(s).");

            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
        }
    }

    static void BFS(int z, int y, int x) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visit = new boolean[L][R][C];
        visit[z][y][x] = true;
        q.offer(new int[]{z, y, x});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 6; i++) {

                int nextZ = now[0] + dz[i];
                int nextY = now[1] + dy[i];
                int nextX = now[2] + dx[i];

                if (nextZ < 0 || nextZ >= L || nextY < 0 || nextY >= R || nextX < 0 || nextX >= C) continue;
                if (visit[nextZ][nextY][nextX] || map[nextZ][nextY][nextX] == '#') continue;
                count[nextZ][nextY][nextX] = count[now[0]][now[1]][now[2]] + 1;
                visit[nextZ][nextY][nextX] = true;
                if(map[nextZ][nextY][nextX] == 'E') {
                    return;
                }
                q.offer(new int[] {nextZ, nextY, nextX});
            }
        }
    }

}
