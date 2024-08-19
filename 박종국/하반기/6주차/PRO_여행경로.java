import java.util.*;
import java.io.*;

class Solution {
    PriorityQueue<String> pq = new PriorityQueue<>();
    int length; 
    boolean[] visited;
    ArrayList<String> answer;
    public String[] solution(String[][] tickets) {
        answer = new ArrayList<>();
        this.length = tickets.length;
        visited = new boolean[length];
        dfs(tickets, "ICN", 0, "ICN");
        return pq.peek().split(" ");
    }
    
    private void dfs(String[][] tickets, String place, int cnt, String path){
        if(cnt == length) {
            pq.add(path);
            return;
        }
        for(int i = 0; i < length; i++){
            if(visited[i] || !tickets[i][0].equals(place)) continue;
            visited[i] = true;
            String nextPlace = tickets[i][1];
            dfs(tickets, nextPlace, cnt+1, path + " " + nextPlace);
            visited[i] = false;
        }
    }
}
