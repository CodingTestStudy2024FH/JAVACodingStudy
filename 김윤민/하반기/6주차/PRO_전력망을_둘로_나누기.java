package PRO;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class PRO_전력망을_둘로_나누기 {
    /*
        트리형태.
        전선을 하나 끊어서 전력망 네트워크를 2개로 분할 하려고 할때,
        두 전력망이 갖게 되는 송전탑의 최소 차이 ( 절대값 )
     */
    public static void main(String[] args) {
        int n=9;
        int[][] arr = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        System.out.println(solution(n,arr));
    }
    static int num,cnt,min;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> tree;
    public static int solution(int n, int[][] wires) {

        // 초기화 시작
        tree = new ArrayList<>();
        min = Integer.MAX_VALUE;
        num = wires.length;// wires개수
        for(int i=0;i<=n;i++){
            tree.add(new ArrayList<>());
        }
        for(int i=0;i<num;i++){
            int a = wires[i][0];
            int b = wires[i][1];

            tree.get(a).add(b);
            tree.get(b).add(a);
        }
        //초기화 끝

        //Logic
        for(int i=0;i<num;i++){//하나씩 제외
            visited = new boolean[n+1]; //초기화 하고
            cnt = 1;//첫번째 요소랑 연결된게 몇 개인지 세기
            int a = wires[i][0];
            int b = wires[i][1];
            
            tree.get(a).remove((Integer)b); //연결 지우고
            tree.get(b).remove((Integer)a);

            //탐색 시작
            bfs(1);
            min = Math.min(min,Math.abs((n-cnt)-cnt));
            tree.get(a).add(b); //원복
            tree.get(b).add(a);
        }


        return min;
    }
    static void bfs(int a){

        Deque<Integer> q = new ArrayDeque<>();
        q.offer(a);
        visited[a]=true;
        while(!q.isEmpty()){
            Integer now = q.poll();
            for(int next:tree.get(now)){//now와 연결된거 모두 q에 넣기
                if(visited[next]) continue;//이미 넣은거면 넘기고
                q.offer(next); //아니면 넣기
                visited[next]=true; // 체크
                cnt++;
            }
        }
    }
}
