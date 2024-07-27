package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ15663_N과M {
    /*
       N개의 자연수 중 M개를 고른 수열.
       사전순

       단순 DFS, Set사용하면 될듯?
       조합은 String으로 하면 될듯함. String비교
     */
    static int N,M;
    static int[] arr;
    static Set<String> set;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        set = new HashSet<>();
        arr = new int[N]; //전체 N
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 사전순 정렬
        Arrays.sort(arr);

        StringBuilder sb =new StringBuilder();
        //순열
        perm(0, new int[M], new boolean[N],sb);

        System.out.println(sb);
    }
    static void perm(int cnt, int[] selected, boolean[] visited,StringBuilder sb) {
        if(cnt == M) {
            StringBuilder temp = new StringBuilder();
            for(int i=0;i<cnt;i++) {
                temp.append(selected[i]).append(' ');
            }
            String str = temp.toString();
            if(!set.contains(str)) {	// set에 들어있는지 확인
                set.add(str);
                sb.append(temp).append('\n');
            }

            return;
        }
        for(int i=0;i<N;i++) {
            if(!visited[i]) {
                selected[cnt] = arr[i];
                visited[i] = true;
                perm(cnt+1, selected, visited,sb);
                visited[i] = false;
            }
        }
    }
}
