import java.util.*;
import java.io.*;

class Solution {
    int area, max, n, m; 
    int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    boolean[][] visited;
    Deque<Node> dq = new ArrayDeque<>();
    public int[] solution(int m, int n, int[][] picture) {
        visited = new boolean[m][n];
        this.m = m;
        this.n = n;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(visited[i][j] || picture[i][j] == 0) continue;
                area++;
                bfs(i, j, picture);
            }
        }
        return new int[]{area, max};
    }
    
    void bfs(int x, int y, int[][] picture){
        dq.addFirst(new Node(x,y));
        int color = picture[x][y];
        int cnt = 0;
        visited[x][y] = true;
        while(!dq.isEmpty()){
            Node current = dq.pollFirst();
            for(int d = 0; d < 4; d++){
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];
                if(nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny] || picture[nx][ny] != color) continue;
                visited[nx][ny] = true;
                dq.addLast(new Node(nx, ny));
            }
            cnt++;
        }
        max = Math.max(max, cnt);
    }
    
    class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
