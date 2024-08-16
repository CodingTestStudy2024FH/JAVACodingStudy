import java.util.*;
import java.io.*;

class Solution {
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    boolean[] visited;
    int min = 987_654_321;
    public int solution(int n, int[][] wires) {
        int answer = -1;
        for(int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for(int[] wire: wires){
            int from = wire[0];
            int to = wire[1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        
        for(int[] wire: wires){
            // int로 선언하면 remove에서 error
            Integer from = wire[0];
            Integer to = wire[1];
            visited = new boolean[n+1];
            graph.get(from).remove(to);
            graph.get(to).remove(from);
            
            int cnt = dfs(1);
            min = Math.min(min, Math.abs(cnt - (n - cnt)));
            
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        
        return min;
    }
    
    private int dfs(int idx){
        visited[idx] = true;
        int cnt = 1;
        for(int next: graph.get(idx)){
            if(visited[next]) continue;
            cnt += dfs(next);
        }
        
        return cnt;
    }
}
