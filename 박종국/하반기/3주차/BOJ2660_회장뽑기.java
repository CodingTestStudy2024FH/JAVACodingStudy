import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<List<Integer>> graph = new ArrayList<>();
    static int N;
    static StringTokenizer st;
    // value 값이 제일 작은 원소들을 우선적으로 꺼내기
    static PriorityQueue<Node> pq = new PriorityQueue<>(
            Comparator.comparingInt((Node node) -> node.time) // 시간(time)을 기준으로 먼저 정렬
                    .thenComparingInt(node -> node.index) // 시간이 같을 경우에는 index를 기준으로 정렬
    );
    static boolean[] visited;
    // BFS 를 제일 적게 돈 노드들이 회장이 되는 문제인듯?
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 회원의 수
        N = Integer.parseInt(br.readLine());

        for(int n = 0; n <= N; n++) graph.add(new ArrayList<>());

        while(true){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if(from == -1 && to == -1) break;
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        for(int n = 1; n <= N; n++){
            int cnt = BFS(n);
            pq.add(new Node(n, cnt));
        }
        int score = pq.peek().time;
        List<Integer> same = new ArrayList<>();
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(node.time != score) break;
            same.add(node.index);
        }
        System.out.println(score + " " + same.size());
        for (int v: same) System.out.print(v + " ");

    }

    static int BFS(int index){
        int cnt = 0;
        visited = new boolean[N+1];
        visited[index] = true;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(index, 0));
        while(!queue.isEmpty()){
            Node node = queue.poll();
            for(int v: graph.get(node.index)){
                if(visited[v]) continue;
                visited[v] = true;
                queue.add(new Node(v, node.time+1));
            }
            cnt = node.time;
        }
        return cnt;
    }

    static class Node{
        int index, time;

        public Node(int index, int time) {
            this.index = index;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", time=" + time +
                    '}';
        }
    }
}
