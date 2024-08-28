import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int N, cnt, edgeSize;
    static long sum;
    static int[] parents;
    static ArrayList<Node> nodes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parents = new int[N+1];
        edgeSize = getEdgeSize();
        for(int n = 1; n <= N; n++) parents[n] = n;

        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            for(int j = i+1; j < N; j++){
                nodes.add(new Node(i+1, j+1, Integer.parseInt(str[j])));
            }
        }
        Collections.sort(nodes);

        for(Node node : nodes){
            if(!union(node.from-1, node.to-1)) continue;
            sum += node.cost;
            cnt++;
            if(cnt == edgeSize) break;
        }
        System.out.println(sum);
    }

    static int getEdgeSize(){
        if(N <= 1) return N-1;
        if(N == 3) return 3;
        return ((N-1) * (N-2))/2;
    }

    static int find(int v){
        if(parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;

        parents[aRoot] = bRoot;
        return true;
    }



    static class Node implements Comparable<Node>{
        int from, to, cost;
        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        @Override
        public String toString() {
            return String.format("[%d -> %d, cost: %d]", from, to, cost);
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

}
