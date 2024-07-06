import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2668_숫자고르기 {

    static int N;
    static int[][] arr; //숫자 담는 배열 
    static boolean[] isVisit; // 방문 체크 
    static int ans;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[2][N+1];
        isVisit = new boolean[N+1];
        for(int i = 1; i <= N; i++){
            arr[0][i] = i+1;
            arr[1][i] = Integer.parseInt(br.readLine());
        }

        /**
         * 그래프로 접근하기
         * 사이클이 만들어 진다는것 -> 돌고 돌아 나를 가리킨다는 것 => 집합에 포함됨 
         * 총 만들어지는 사이클의 개수 => 최대 속할수 있는 집합의 원소 수 
         * 숫자 각각이 노드고 서로 위아래면 가리킬 수 있다고 생각해야함 
         */

        for(int i = 1; i <= N; i++){
            // 사이클 검사는 쭉 검사하다가 처음 지점으로 돌아오기만 하면 사이클이라고 볼 수 있음
            isVisit[i] = true;
            dfs(i, i);
            isVisit[i] = false;
        }

        System.out.println(list.size());
        Collections.sort(list);
        for(Integer i : list){
            System.out.println(i);
        }
    }

    // idx번째 숫자를 꺼내어서 검사 -> idx를 인덱스값으로 하는 숫자로 다시 찾아감 (사이클 찾기)
    // 만약 방문하지 않았다면 -> 방문 체크하고 dfs (이후 복원해놓기 -> 다음도 검사하러 가야돼)
    static void dfs(int idx, int startPoint){

        // 방문한 적 없으면
        if(!isVisit[arr[1][idx]]){
            isVisit[arr[1][idx]] = true;
            dfs(arr[1][idx], startPoint);
            isVisit[arr[1][idx]] = false;
        }

        // 사이클 찾음 ( 제일 처음 dfs 시작점 = 현재도착한 지점) -> 사이클이 생겼다는 것을 의미함 
        if(arr[1][idx] == startPoint) list.add(startPoint);
    }
}
