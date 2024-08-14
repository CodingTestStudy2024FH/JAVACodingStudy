import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int vertex, edge;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));
    static int[] time;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        vertex = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        time = new int[vertex+1];
        Arrays.fill(time, Integer.MAX_VALUE);
        for(int v = 0; v <= vertex; v++) graph.add(new ArrayList<>());

        for(int e = 0; e < edge; e++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, time));
            graph.get(to).add(new Node(from, time));
        }

        dijkstra();
        System.out.println(time[vertex]);
    }

    static void dijkstra(){
        time[1] = 0;
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()){
            Node currentNode = pq.poll();
            for(Node nextNode: graph.get(currentNode.idx)){
                if(time[nextNode.idx] > time[currentNode.idx] + nextNode.time){
                    time[nextNode.idx] = nextNode.time + time[currentNode.idx];
                    pq.add(new Node(nextNode.idx, time[nextNode.idx]));
                }
            }
        }
    }
    static class Node{
        int idx, time;

        public Node(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }
}
