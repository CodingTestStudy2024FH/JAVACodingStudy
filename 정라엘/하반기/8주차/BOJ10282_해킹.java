import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int index;
    int distance;

    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
        return this.distance - o.distance;
    }
}

public class Main {
    static int INF = 1000000000; // 무한대 값 설정
    static int n, d, c;
    static List<List<Node>> graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int t = Integer.parseInt(br.readLine());
        
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            dist = new int[n + 1];
            Arrays.fill(dist, INF);

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph.get(b).add(new Node(a, s));
            }

            dijkstra(c);

            int count = 0;
            int maxTime = 0;
            for (int i = 1; i <= n; i++) {
                if (dist[i] != INF) {
                    count++;
                    maxTime = Math.max(maxTime, dist[i]);
                }
            }

            System.out.println(count + " " + maxTime);
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentIndex = current.index;
            int currentDist = current.distance;

            if (dist[currentIndex] < currentDist) continue;

            for (Node next : graph.get(currentIndex)) {
                int newDist = dist[currentIndex] + next.distance;

                if (newDist < dist[next.index]) {
                    dist[next.index] = newDist;
                    pq.add(new Node(next.index, newDist));
                }
            }
        }
    }
}
