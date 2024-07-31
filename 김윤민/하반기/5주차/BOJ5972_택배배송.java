package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ5972_택배배송 {
    /*
       최소한의 소를 만나면서 여물을 주면서감.
       이거 머야 그래프야?

       다익스트라 최소경로.
     */
    static int N,M;
    static boolean[] visited;
    static int[] ans=new int[50000];
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        //초기화시작
        visited = new boolean[N];

        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, dis));
            graph.get(b).add(new Node(a, dis));
        }
        //초기화 끝

        //로직 시작
        logic(1);//처음 시작은 무조건 1
        //1~N이니까.
        System.out.println(ans[N]);

    }
    //다익스트라 시작
    public static void logic(int st){
        Arrays.fill(ans,Integer.MAX_VALUE);//초기화

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(st, 0));
        ans[st]=0;//가기 위한 거리는 0

        while(!pq.isEmpty()){
            Node temp = pq.poll();
            int next = temp.next;
            int distance = temp.distance;

            if(ans[next]<distance) continue;//이미 처리된적 있으면 패스
            for(int i=0;i<graph.get(next).size();i++){ //현재 노드와 연결된 다른 인접한 노드들을 확인
                int cost = ans[next] + graph.get(next).get(i).distance;
                if(cost<ans[graph.get(next).get(i).next]){ //현재 노드를 통해 다른 노드로 이동하는것이 거리가 더 짧으면
                    ans[graph.get(next).get(i).next]=cost;
                    pq.offer(new Node( graph.get(next).get(i).next, cost));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int next;
        int distance;

        public Node(int next, int distance) {
            this.next = next;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance-o.distance;
        }
    }
}
