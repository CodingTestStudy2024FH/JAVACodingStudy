package bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_13549_숨바꼭질_3 {
    static boolean[] visited = new boolean[100_000+1];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int subin = Integer.parseInt(st.nextToken());
        int brother = Integer.parseInt(st.nextToken());
        System.out.println(findBrother(subin, brother));
    }
    static int findBrother(int subin, int brother) {
        Deque<Node> subinPosition = new ArrayDeque<>();
        subinPosition.addFirst(new Node(subin, 0));
        visited[subin] = true;
        int result = 0;
        while(!subinPosition.isEmpty()){
            Node cur = subinPosition.pollFirst();
            int position = cur.position;
            int time = cur.time;
            if(position == brother) {
                result = time;
                break;
            }
            if(isOk(position * 2, visited)) {
                visited[position*2] = true;
                subinPosition.addLast(new Node(position * 2, time));
            }
            if(isOk(position-1,visited)) {
                visited[position-1] = true;
                subinPosition.addLast(new Node(position-1, time+1));
            }
            if(isOk(position+1,visited)) {
                visited[position+1] = true;
                subinPosition.addLast(new Node(position + 1, time+1));
            }

        }
        return result;
    }
    static boolean isOk(int dist, boolean[] visited){
        return dist >= 0 && dist < 100_000+1 && !visited[dist];
    }
    static class Node{
        int position, time;

        public Node(int position, int time) {
            this.position = position;
            this.time = time;
        }
    }
}
