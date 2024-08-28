package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16398_행성연결 {
    /*
        중심은 T, N개의 행성 간 플로우
        모든 행성을 연결하면서 관리비용을 최소화.


        ㅠㅠㅠ 뭘로 풀어야하지?
        가중치 있으니까. 크루스칼 아니면 프림???

        크루스칼 알고리즘. = Union& Find로 구현.

     */
    static int N;
    static int[] parent;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        //input start
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        //init
        for(int i=0;i<N;i++){
            parent[i]=i;
        }
        map = new int[N][N];
        PriorityQueue<Point> pq = new PriorityQueue<>();
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(i==j) continue; //같은경우는 0이니까 넘어가고
                if(map[i][j]!= 0){ //다른데 가중치가 있으면
                    pq.add(new Point(i, j, map[i][j])); //pq 넣어서 자동으로 오름차순 정렬
                }
            }
        }
        long result=0;
        int idx=0;

        for(int i=0;i<pq.size();i++){ //pq값에서
            if(idx==N-1)break; //모든 행성이 연결됐으면 = N-1개면,그만하고
            Point now = pq.poll(); //아니면 계속 연결
            if(union(now.from,now.to)){ // union이 안된상태면
                result+=now.w; // 비용 더하고
                idx++; //연결 몇개 성립 됐는지 count
            }
        }

        System.out.println(result);


    }
    public static int find(int a){
        if(parent[a]==a) return a;
        return parent[a] = find(parent[a]);//** 중요 **
    }
    public static boolean union(int a,int b){
        int A = find(a);
        int B = find(b);
        if(A==B)return false;
        parent[b]=a;
        return true;
    }


    static class Point implements Comparable<Point> {
        int from,to,w;

        public Point(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Point o) {
            return 0;
        }
    }


}
