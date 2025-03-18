package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16928_뱀과_사다리_게임 {
    static int N, M;
    static final int HUNDRED = 100;
    static boolean[] visited = new boolean[HUNDRED+1];
    static HashMap<Integer, Integer> snakeAndALadderMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N+M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            snakeAndALadderMap.put(from, to);
        }
        playSnakeAndLadderGame();
    }
    static void playSnakeAndLadderGame(){
        Deque<Node> dq = new ArrayDeque<>();
        dq.add(new Node(1, 0));
        visited[1] = true;
        while(!dq.isEmpty()){
            Node node = dq.pollFirst();
            if(node.position == HUNDRED){
                System.out.println(node.time);
                return;
            }
            for(int i = 1; i <= 6; i++){
                int next = node.position + i;
                if(!isValid(next)) continue;
                if(snakeAndALadderMap.containsKey(next)){
                    if(visited[snakeAndALadderMap.get(next)]) continue;
                    next = snakeAndALadderMap.get(next);
                }
                visited[next] = true;
                dq.addLast(new Node(next, node.time+1));
            }
        }
    }

    static boolean isValid(int pos){
        return pos <= HUNDRED && !visited[pos];
    }
    static class Node{
        int position, time;

        public Node(int position, int time) {
            this.position = position;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "position=" + position +
                    ", time=" + time +
                    '}';
        }
    }
}
