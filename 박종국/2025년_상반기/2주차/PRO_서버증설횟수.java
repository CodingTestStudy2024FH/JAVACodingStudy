package simulation;

import java.util.Arrays;

public class PRO_서버_증설_횟수 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] players = {0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1};
        int m = 1, k = 1;
        System.out.println(solution.solution(players, m, k));
    }
    static class Solution{
        int solution(int[] players, int m, int k){
            int answer = 0;
            int[] dp = new int[players.length];
            Arrays.fill(dp, m-1);
            for(int index = 0; index < dp.length; index++){
                int currentPlayer = players[index];
                if(currentPlayer <= dp[index]) continue;
                int lastIndex = Math.min(dp.length-1, index+k-1);
                int requiredPersonCount = currentPlayer - dp[index];
                int requiredServerCount = (requiredPersonCount/m + 1);
                if(requiredPersonCount % m == 0) requiredServerCount--;
                for(int j = index; j <= lastIndex; j++) dp[j] += requiredServerCount * m;
                answer += requiredServerCount;
            }
            return answer;
        }
    }
}

