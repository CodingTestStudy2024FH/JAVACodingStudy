import java.io.*;
import java.util.*;

public class Main {
    static int N, p[];
    public static PriorityQueue<Edge> edges;

    public static class Edge implements Comparable<Edge> {
        int n1, n2, cost;

        public Edge(int n1, int n2, int cost){
            this.n1 = n1;
            this.n2 = n2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static int find(int n1){
        if(p[n1]==0){
            return n1;
        }
        return p[n1] = find(p[n1]);
    }

    public static boolean union(int n1, int n2){
        n1 = find(n1);
        n2 = find(n2);
        if(n1 != n2){
            p[n2] = n1;
            return true;
        }
        return false;
    }

    public static long kruskal(){
        int cnt = 0;
        long result = 0;

        while(!edges.isEmpty()){
            Edge edge = edges.poll();
            if(union(edge.n1, edge.n2)){
                result += edge.cost;
                if(++cnt==N-1){break;}
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        edges = new PriorityQueue<>();
        p = new int[N+1];
        int idx = 0;
        for(int i=1; i<=N; i++){
            String input[] = br.readLine().split(" ");
            for(int j=i+1; j<=N; j++){
                edges.add(new Edge(i, j, Integer.parseInt(input[j-1])));
            }
        }
        System.out.println(kruskal());
    }
}
