package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_15663_N과_M_9 {
    static int N, M;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static int[] arr, src;
    // Set은 순서를 유지하지 않지만 예외적으로 LinkedHashSet은 순서를 유지할 수 있음
    static Set<String> set = new LinkedHashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        src = new int[M];
        visited = new boolean[N];
        comb(0);
        for (String s : set) System.out.println(s);
    }
    static void comb(int depth){
        if(depth == M){
            set.add(elementToString(src));
            sb.setLength(0);
            return;
        }
        for(int i = 0; i < N; i++){
            if(visited[i]) continue;
            visited[i] = true;
            src[depth] = arr[i];
            comb(depth+1);
            visited[i] = false;
        }
    }
    static String elementToString(int[] arr){
        for (int j : arr) sb.append(j).append(" ");
        return sb.toString();
    }
}
