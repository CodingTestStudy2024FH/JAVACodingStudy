import java.util.*; 

class Solution {
    
    // dfs돌면서 복원 시켜주기 아이디어뿐... 
    
    static int dungeonsCount = 0; 
    static boolean[] isVisit ; 
    static int myStatus = 0; 
    static int answer = 0; 
    
    public int solution(int k, int[][] dungeons) {
        
        dungeonsCount = dungeons.length; 
        isVisit = new boolean[dungeonsCount]; // 개수만큼 방문 체크 
        
        for(int i = 0; i < dungeonsCount; i++){
            Arrays.fill(isVisit, false); 
            myStatus = k; 
            if(myStatus >= dungeons[i][0]){
                isVisit[i] = true; 
                // System.out.println(i + "번째부터 시작 ---"); 
                dfs(i, myStatus - dungeons[i][1], 1, dungeons); 
                isVisit[i] = false; 
            }
        }
        
        return answer;
    }
    
    static void dfs(int idx, int status, int count, int[][] dungeons){

        
        // 다음 갈 수 있는 (방문하지 않은 던전 확인)
        // 갈 수 있다면 ? 피로도 깎고 방문하기 (복원하는거 하지말고 파라미터로 빼기)
        
        // 다음 갈 수 없으면 ? 개수 max 비교하기 
        
        for(int i = 0; i < dungeonsCount; i++){
            // 아직 방문하지 않은 던전이 있는데 
            if(!isVisit[i]){
                // 던전에 방문이 가능한 상태라면 
                if(dungeons[i][0] <= status){
                    // 방문하고
                    isVisit[i] = true; 
                    dfs(i, status - dungeons[i][1], count+1, dungeons);
                    isVisit[i] = false; 
                }
            }
        }
        
        answer = Math.max(answer, count); 
        
    }
}
