package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2170_선_긋기 {
    static int N;
    static StringTokenizer st;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Node> linkedLine = new ArrayList<>();
        N = Integer.parseInt(br.readLine());

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.add(new Node(start, end));
        }

        Node currentNode = pq.poll();;

        if(pq.isEmpty()){
            System.out.println(calculateNodeLine(currentNode));
            return;
        }

        while(!pq.isEmpty()){
            Node nextNode = pq.poll();
            if(currentNode.end >= nextNode.end) continue;
            if(currentNode.end >= nextNode.start) currentNode.end = nextNode.end;
            if(currentNode.end < nextNode.start){
                linkedLine.add(currentNode);
                currentNode = nextNode;
            }
        }

        if( linkedLine.isEmpty() || currentNode.end != linkedLine.get(linkedLine.size()-1).end)
                linkedLine.add(currentNode);

        System.out.println(makeResult(linkedLine));

    }

    static long makeResult(ArrayList<Node> linkedLine){
        long answer = 0L;
        for(Node node : linkedLine) answer += calculateNodeLine(node);
        return answer;
    }

    static long calculateNodeLine(Node node){
        return Math.abs(node.end - node.start);
    }
    static class Node implements Comparable<Node>{
        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            if(this.start == o.start) return o.end - this.end;
            return this.start - o.start;
        }
    }
}
