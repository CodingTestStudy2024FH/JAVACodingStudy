package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2170_선_긋기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        StringTokenizer st;
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.add(new Node(start, end));
        }

        Node current = pq.poll();

        for(int n = 1; n < N; n++){
            Node next = pq.poll();
            // 겹치는 경우 end 확장
            if(current.isConnected(next)) current.end = Math.max(current.end, next.end);
            else{
                result += (current.end - current.start);
                current = next;
            }
        }
        result += (current.end - current.start);
        System.out.println(result);
    }

    static class Node implements Comparable<Node>{
        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean isConnected(Node next){
            return this.end >= next.start;
        }

        @Override
        public int compareTo(Node o) {
            if(this.start == o.start) return this.end - o.end;
            return this.start - o.start;
        }
    }
}
