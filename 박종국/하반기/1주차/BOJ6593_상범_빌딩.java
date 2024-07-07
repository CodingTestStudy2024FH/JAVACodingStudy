package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6593_상범_빌딩 {
    static StringTokenizer st;
    static boolean[][][] visited;
    static char[][][] buildings;
    static StringBuilder sb = new StringBuilder();
    static int Z, X, Y;
    static int[] dz = {-1, 1, 0, 0, 0, 0}, dx = {0, 0, -1, 1, 0, 0}, dy = {0, 0, 0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            st = new StringTokenizer(br.readLine());
            Z = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            if( Z == 0 && X == 0 && Y == 0) break;
            visited = new boolean[Z][X][Y];
            buildings = new char[Z][X][Y];
            input(br);
            int time = escape(getStartRoom());
            if(time == 0) sb.append("Trapped!").append("\n");
            else sb.append("Escaped in ").append(time).append(" minute(s).\n");
        }
        System.out.println(sb.toString());
    }
    static int escape(int[] zxy){
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(zxy[0], zxy[1], zxy[2], 0));
        while(!queue.isEmpty()){
            Node current = queue.poll();

            for(int d = 0; d < 6; d++){
                int nz = current.z + dz[d];
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];
                if(isRange(nz, nx, ny)) continue;
                if(buildings[nz][nx][ny] == '#') continue;
                if(buildings[nz][nx][ny] == 'E') return current.time + 1;
                queue.add(new Node(nz, nx, ny, current.time + 1));
                visited[nz][nx][ny] = true;
            }
        }
        return 0;
    }
    // true 라면 범위를 벗어난 것
    static boolean isRange(int z, int x, int y){
        return (z < 0 || z >= Z || x < 0 || x >= X || y < 0 || y >= Y || visited[z][x][y]);
    }

    static void input(BufferedReader br) throws IOException {
        for(int z = 0; z < Z; z++) {
            for(int x = 0; x < X; x++) {
                buildings[z][x] = br.readLine().toCharArray();
            }
            br.readLine();
        }

    }

    static int[] getStartRoom(){
        int[] result = new int[3];
        for(int z = 0; z < Z; z++) {
            for(int x = 0; x < X; x++) {
                for(int y = 0; y < Y; y++) {
                    if(buildings[z][x][y] == 'S') {
                        result[0] = z;
                        result[1] = x;
                        result[2] = y;
                        return result;
                    }
                }
            }
        }
        return result;
    }
    static class Node{
        int z, x, y, time;

        public Node(int z, int x, int y, int time) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}