import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] floyd = new int[n+1][n+1];
        for(int[] result: results){
            int winner = result[0];
            int loser = result[1];
            floyd[winner][loser] = 1;
            floyd[loser][winner] = -1;
        }
        // for(int[] flo: floyd) System.out.println(Arrays.toString(flo));
        
        // floyd warshall
        // 3중 for문으로 모든 노드들을 비교해서 승패 결과 표시
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                for(int k = 1; k <= n; k++){
                    if(floyd[i][j] == 1 && floyd[j][k] == 1){
                        floyd[i][k] = 1;
                        floyd[k][i] = -1;
                    }
                    else if(floyd[i][j] == -1 && floyd[j][k] == -1){
                        floyd[i][k] = -1;
                        floyd[k][i] = 1;
                    }
                }
            }
        }
        for(int[] flo: floyd) System.out.println(Arrays.toString(flo));
        
        for(int i = 1; i <= n; i++){
            int cnt = 0;
            for(int j = 1; j <= n; j++) if(floyd[i][j] != 0) cnt++;
            if(cnt == n-1) answer++;
            
        }
        return answer;
    }
}
