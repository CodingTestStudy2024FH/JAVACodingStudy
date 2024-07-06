import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1504_특정한최단경로 {

    /**
     * 정 ~ 안풀린다면 질문게시판에 3, 10, 75 % 반례 넣어보면서 하기 ^^& 미래의 김아림 화이팅 
     */
    
    
    static int N, E, startNode;
    static long[][] graph;
    static long[][] dist; // 다익스트라 거리 저장 배열 (1번에서 N번까지)
    static boolean[] visit;
    static int u, v; // 무조건 거쳐야하는 정점 2개
    static long case1, case2;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 방향성 없는 그래프가 주어짐
        // 1번에서 N번으로 최단거리로 이동하려고함 (다익스트라)

        // 주어진 두 정점은 반드시 통과해야함에 유의
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        // 정점 번호는 1번부터 시작함
        graph = new long[N+1][N+1];
        dist = new long[3][N+1];
        for(int i = 1; i <= N; i++){
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            Long d = Long.parseLong(st.nextToken());

            // 양방향이므로
            graph[v1][v2] = graph[v2][v1] = d;
        }

        // 무조건 거쳐야하는 정점 2개
        st = new StringTokenizer(br.readLine());
        u = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        /**
         * 아이디어 1.
         * case 1 )
         * 1번(출발고정) 에서 정점 u까지 가는 최단 거리
         * u에서 v까지 가는 최단거리
         * v에서 N까지 가는 최단거리
         *
         * case 2 )
         * 1번(출발고정) 에서 정점 v까지 가는 최단 거리
         * v에서 u까지 가는 최단거리
         * u에서 N까지 가는 최단거리
         *
         * => 두개의 케이스를 비교해본다...
         */

        // 1번, u번, v번에서 다른 모든정점까지 가는 최단 거리를 구하자 -> 각각 시작 노드가 될 수 있음 
        int[] startVertex = {1, u, v};
        for(int idx = 0; idx < 3; idx++){
            Arrays.fill(dist[idx], Integer.MAX_VALUE);
            visit = new boolean[N+1];
            startNode = startVertex[idx];
            visit[startNode] = true;

            // dist 배열 초기화
            for(int i = 1; i <= N; i++){
                if(graph[startNode][i] != 0) dist[idx][i] = graph[startNode][i];
            }
            dist[idx][startNode] = 0L;
            // System.out.println(Arrays.toString(dist[idx]) + " === start === ");

            while(true){
                // 시작노드가 제일 작다고 생각하고 -> 중복검사 x 어차피 visit에서 걸림 
                int minNode = startNode;
                long minDist = Integer.MAX_VALUE;

                // 방문하지 않은 노드중 가장 짧은 거리의 노드 찾기
                for(int i = 1; i <= N; i++){
                    if(!visit[i]){
                        if(minDist > dist[idx][i]){
                            minNode = i;
                            minDist = dist[idx][i];
                        }
                    }
                }
                
                if(visit[minNode]) break; // 똑같은걸 다시방문하려고 한다? => 더이상 방문할 곳이 없다는 뜻임 

                // 방문 처리
                visit[minNode] = true;
                // System.out.println(minNode + " 번 노드 방문 처리 완료 ");
                // 찾은 노드를 꼭 방문한채 다른 노드 방문해보기
                // 만약 현재 dist보다 작다면? 갱신해주기
                for(int i = 1; i <= N; i++){
                    if(dist[idx][minNode] + graph[minNode][i] < dist[idx][i]){
                        // System.out.println(dist[idx][minNode] + " " + graph[minNode][i] + " < " + dist[idx][i]);
                        dist[idx][i] = dist[idx][minNode] + graph[minNode][i];
                        // System.out.println(startNode + " -> " + minNode + " -> " + i + " : " + dist[idx][i] + "로 갱신");
                    }
                }
            }

            // System.out.println(Arrays.toString(dist[idx]));
        }

        // 1 -> u -> v -> N
        case1 = dist[0][u] + dist[1][v] + dist[2][N];
        case2 = dist[0][v] + dist[2][u] + dist[1][N];

        // case1 방법에서 연결이 끊기는 경우 찾기
        if(dist[0][u] == Integer.MAX_VALUE
        || dist[1][v] == Integer.MAX_VALUE
        || dist[2][N] == Integer.MAX_VALUE)
            case1 = -1;

        // case2 방법에서 연결이 끊기는 경우 찾기
        if(dist[0][v] == Integer.MAX_VALUE
                || dist[2][u] == Integer.MAX_VALUE
                || dist[1][N] == Integer.MAX_VALUE)
            case2 = -1;


        if(case1 != -1 && case2 != -1){
            long answer = Math.min(case1, case2);
            System.out.println(answer);
        } else if (case1 == -1) {
            System.out.println(case2);
        }else if(case2 == -1)
        {
            System.out.println(case1);
        }else
            System.out.println(-1);

    }
}
