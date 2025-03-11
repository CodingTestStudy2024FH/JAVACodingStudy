import java.io.*;
import java.util.*;

class Solution {
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int answer;
    static int[][] map;
    static boolean[][] visit;
    
    public int solution(int[][] maps) {
        //1은 갈 수 있는 길, 0은 벽
        //0,0에서 4,4로 이동
        //최단거리를 리턴(도착 불가시 -1)
        map = maps;
        n = map.length;
        m = map[0].length;
        visit = new boolean[n][m];
        
        answer = -1;
        visit[0][0] = true;
        bfs(0,0);
        
        return answer;
    }
    
    public static void bfs(int x, int y){
        // 큐 정의 후, 큐에 cnt까지 같이 넣기
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y, 1});
        
        // 큐에 있을 동안 뽑아서 판단
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int cnt = curr[2];
            
            //방문 처리 및, 도착지 확인
            if(curr[1] == n-1 && curr[0] == m-1){
                answer = cnt;
                return;
            }
            
            for(int d=0; d<4; d++){
                int ny = curr[1] + dy[d];
                int nx = curr[0] + dx[d];
                
                if(ny<0 || nx<0 || ny>=n || nx>=m) continue;
                if(map[ny][nx] == 0 || visit[ny][nx]) continue;
                
                //범위 벗어나거나, 벽이 아니라면 큐에 추가
                visit[ny][nx] = true;
                q.add(new int[]{nx, ny, cnt+1});
            }
        }
    } 
}
