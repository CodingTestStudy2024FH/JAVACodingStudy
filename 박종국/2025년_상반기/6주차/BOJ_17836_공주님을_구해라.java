package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_17836_공주님을_구해라 {
    static int N, M, T;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static final char EMPTY = '0', WALL = '1', PRAM = '2';
    static char[][] maps;
    // 검은 주운 이후 다시 왔던 길을 돌아가는 경우도 존재하기에 3차원으로 해결해야함
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        maps = new char[N][M];
        visited = new boolean[N][M][2];

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){
                maps[n][m] = st.nextToken().charAt(0);
            }
        }

        System.out.println(savePrincess());
    }

    static String savePrincess() {
        Deque<Hero> dq = new ArrayDeque<>();
        dq.add(new Hero(0, 0, 0, false));
        visited[0][0][0] = true;

        while(!dq.isEmpty()){
            Hero hero = dq.poll();
            boolean hasPram = hero.hasPram;
            if(hero.y == N-1 && hero.x == M-1) return String.valueOf(hero.time);
            if(hero.time > T) return "Fail";
            for(int d = 0; d < 4; d++){
                int ny = hero.y + dy[d];
                int nx = hero.x + dx[d];
                if(isRight(ny, nx, hasPram)) continue;
                boolean isPlacedPram = isPlacedPram(ny, nx, hasPram);
                int visitedInPram = isPlacedPram ? 1 : 0;
                visited[ny][nx][visitedInPram] = true;
                dq.add(new Hero(ny, nx, hero.time+1, isPlacedPram));

            }
        }

        return "Fail";
    }

    static boolean isPlacedPram(int y, int x, boolean hasPram){
        return hasPram || maps[y][x] == PRAM ;
    }

    static boolean isRight(int y, int x, boolean hasPram){
        if(hasPram) return y < 0 || x < 0 || y >= N || x >= M || visited[y][x][1];
        return y < 0 || x < 0 || y >= N || x >= M || maps[y][x] == WALL || visited[y][x][0];
    }

    static class Hero{
        int y, x, time;
        boolean hasPram;

        public Hero(int y, int x, int time, boolean hasPram) {
            this.y = y;
            this.x = x;
            this.time = time;
            this.hasPram = hasPram;
        }

        @Override
        public String toString() {
            return "Hero{" +
                    "y=" + y +
                    ", x=" + x +
                    ", time=" + time +
                    ", hasPram=" + hasPram +
                    '}';
        }
    }
}
