import java.io.*;
import java.util.*;

public class BOJ_2458_키순서 {
    static int N, M;
    static LinkedList<Integer>[] adjList;
    static LinkedList<Integer>[] reverseAdjList;
    static boolean[] visited;
    static int[] count;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 그래프와 역방향 그래프 초기화
        adjList = new LinkedList[N+1];
        reverseAdjList = new LinkedList[N+1];
        for(int i=1; i<=N; i++){
            adjList[i] = new LinkedList<>();
            reverseAdjList[i] = new LinkedList<>();
        }

        // 입력 처리
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            // n1 < n2, 정방향과 역방향 그래프에 추가
            adjList[n1].add(n2);
            reverseAdjList[n2].add(n1);
        }
        
        // 각 학생에 대해 탐색 진행
        count = new int[N+1];
        for(int i=1; i<=N; i++){
            visited = new boolean[N+1];
            dfs(i, adjList);         // 정방향 탐색
            dfs(i, reverseAdjList);  // 역방향 탐색
        }

        // 모든 학생과 비교할 수 있는 학생 수 계산
        int result = 0;
        for(int i=1; i<=N; i++){
            if(count[i] == N-1) {  // 자기 자신을 제외한 모든 학생과의 관계를 알 수 있다면
                result++;
            }
        }

        System.out.println(result);
    }

    // DFS 탐색 메서드
    static void dfs(int v, LinkedList<Integer>[] list){
        visited[v] = true;
        for(int u : list[v]){
            if(!visited[u]){
                count[u]++;
                dfs(u, list);
            }
        }
    }
}
