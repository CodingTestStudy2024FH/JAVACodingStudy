import java.util.*; 

class Solution {
    
    // X 바다, 숫자는 무인도 
    static int[] dx = {1, -1, 0, 0}; 
    static int[] dy = {0, 0, -1, 1}; 
    static int N, M; 
    static boolean[][] isVisit;
    static Queue<int[]> q = new ArrayDeque<>(); 
    static List<Integer> answerList = new ArrayList<>(); 
    static int oneAnswer = 0; 
    
    public int[] solution(String[] maps) {
        
        M = maps.length; // 세로 
        N = maps[0].length(); // 가로 
        isVisit = new boolean[M][N];
        
        for(int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                if(Character.isDigit(maps[i].charAt(j)) && !isVisit[i][j]){
                    BFS(i, j, maps);
                }
            }
        }
        
        
        int[] answer = {-1}; 
        Collections.sort(answerList); 
        if(answerList.size() == 0){
             answer = new int[] {-1}; 
        }else {
            answer = new int[answerList.size()];
            for(int i = 0; i < answer.length; i++){
                answer[i] = answerList.get(i); 
            }
        }
        Collections.sort(answerList); 
        
        return answer;
    }
    
    static void BFS(int y, int x, String[] maps) {
        int answer = 0; 
        isVisit[y][x] = true;
        // maps[y].chatAt(x) = 'X'; // 이게 되나 ;; 응 안돼
        q.clear(); 
        q.offer(new int[] {y, x});
        int ny, nx, nny, nnx; 
        
        while(!q.isEmpty()){
            ny = q.peek()[0];
            nx = q.poll()[1]; 
            answer += maps[ny].charAt(nx) - '0'; 
            
            for(int i = 0; i < 4; i++){
                
                nny = ny + dy[i]; 
                nnx = nx + dx[i]; 
                
                if(nny < 0 || nny >= M || nnx < 0 || nnx >= N) continue; 
                if(isVisit[nny][nnx]) continue; 
                if(!Character.isDigit(maps[nny].charAt(nnx))) continue; 
                
                isVisit[nny][nnx] = true; 
                q.offer(new int[] {nny, nnx}); 
            }
        }
        
        answerList.add(answer);  
    }
    
//     static void DFS(int y, int x, String[] maps){
        
//         if(isVisit[y][x]) return; 
//         isVisit[y][x] = true; 
        
//         oneAnswer += maps[y].charAt(x) - '0'; 
        
//         for(int i = 0; i < 4; i++){
                
//             nny = ny + dy[i]; 
//             nnx = nx + dx[i]; 
                
//             if(nny < 0 || nny >= M || nnx < 0 || nnx >= N) return; 
//             if(isVisit[nny][nnx]) return; 
//             if(!Character.isDigit(maps[nny].charAt(nnx))) return; 
            
//             dfs(nny, nnx, maps); 
//         }
        
//     }
}
