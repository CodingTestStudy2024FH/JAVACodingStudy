import java.util.*;

class Solution {
    
    // bfs 돌면서 석유의 위치 파악하기 
    // 석유가 꽂힌 위치의 x 값이 중요함 
    // 각 x마다 y를 돌면서 ->
    
    static int[] dy = {1,-1,0,0}; 
    static int[] dx = {0,0,1,-1}; 
    static int H, W; 
    static boolean[][] isVisit; 
    static int size; 
    static List<Integer> listX = new ArrayList<>(); 
    static int[] answerXList ; 

    
    public int solution(int[][] land) {
        int answer = 0;
        
        // 세로길이 H, 가로길이 W
        H = land.length; 
        W = land[0].length; 
        isVisit = new boolean[H][W]; 
        answerXList = new int[W]; 
        
        for(int y = 0; y < H; y++){
            for(int x = 0; x < W; x++){
                if(!isVisit[y][x] && land[y][x] == 1){
                    size = 0; 
                    listX = new ArrayList<>(); 
                    BFS(y,x,land); 
                    //System.out.println(y + "," + x + " => " + size);
                    for(int locX : listX){
                        //System.out.println(locX + " "); 
                        answerXList[locX] += size; 
                    }
                    //System.out.println(); 
                }
            }
        }
        
        
        answer = answerXList[0]; 
        for(int i = 1; i < W; i++){
            //System.out.println(answerXList[i]);
            answer = Math.max(answer, answerXList[i]); 
        }
        
        
        return answer;
        
    }
    
    static void BFS(int y, int x, int[][] land){
        
        Queue<int[]> q = new ArrayDeque<>(); 
        q.add(new int[] {y,x}); 
        isVisit[y][x] = true; 
        
        
        while(!q.isEmpty()){
            size++; 
            int[] cur = q.poll(); 
            
            if(!listX.contains(cur[1])){
                listX.add(cur[1]); 
            }
            
            for(int i = 0; i <4; i++){
                int ny = cur[0] + dy[i]; 
                int nx = cur[1] + dx[i]; 
                
                if(nx < 0 || nx >= W || ny < 0 || ny >= H) continue; 
                if(isVisit[ny][nx]) continue; 
                if(land[ny][nx] != 1) continue; 
                isVisit[ny][nx] = true; 
                q.offer(new int[] {ny,nx}); 
            }
        }
     
    }
}
