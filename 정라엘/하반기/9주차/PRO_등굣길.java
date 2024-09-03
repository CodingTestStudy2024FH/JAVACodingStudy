// 모듈러...? 이런게 나올지 몰랐지 죄송합니다 여러분

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n + 1][m + 1];
        
        // 웅덩이 위치를 표시
        for (int[] puddle : puddles) {
            dp[puddle[1]][puddle[0]] = -1;
        }
        
        dp[1][1] = 1; // 시작 위치
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (dp[i][j] == -1) {
                    dp[i][j] = 0; // 웅덩이 위치는 경로가 없음
                } else {
                    if (i > 1) dp[i][j] += dp[i-1][j];
                    if (j > 1) dp[i][j] += dp[i][j-1];
                    
                    dp[i][j] %= 1000000007; // 큰 수에 대비해 모듈러 연산
                }
            }
        }
        
        return dp[n][m];
    }
}
