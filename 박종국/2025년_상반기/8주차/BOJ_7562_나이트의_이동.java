package bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_7562_나이트의_이동 {
    static int N;
    static int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1}, dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static Node start, target;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int n = 0; n < N; n++){
            int range = Integer.parseInt(br.readLine());
            start = splitNode(br.readLine());
            target = splitNode(br.readLine());
            System.out.println(searchMinimumRoad(range));
        }

    }

    static int searchMinimumRoad(int range){
        boolean[][] visited = new boolean[range][range];
        Deque<Node> dq = new ArrayDeque<>();
        int time = 0;
        visited[start.y][start.x] = true;
        dq.add(start);
        while(!dq.isEmpty()){
            Node current = dq.pollFirst();

            for(int i = 0; i < 8; i++){
                int ny = current.y + dy[i];
                int nx = current.x + dx[i];

                if(!isRange(ny, nx, range, visited)) continue;
                if(ny == target.y && nx == target.x) return current.time+1;
                dq.addLast(new Node(ny, nx, current.time+1));
                visited[ny][nx] = true;
            }
        }
        return time;
    }

    static boolean isRange(int y, int x, int range, boolean[][] visited){
        return y >= 0 && x >= 0 && y < range && x < range && !visited[y][x];
    }
    static Node splitNode(String line){
        st = new StringTokenizer(line);
        return new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
    static class Node{
        int y, x, time;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Node(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return y == node.y && x == node.x;
        }

    }
}
