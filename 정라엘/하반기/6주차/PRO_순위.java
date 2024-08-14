// 플로이드 워셜 어렵다...
import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] graph = new int[n + 1][n + 1];

        // 1. 그래프 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            graph[i][i] = 0; // 시작은 0
        }

        // 2. 승패 결정
        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            graph[winner][loser] = 1; // 승자는 패자보다 1단계 위
        }

        // 3. 플로이드 워셜
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        // 4. 순위 결정
        for (int i = 1; i <= n; i++) {
            boolean canDetermineRank = true;
            for (int j = 1; j <= n; j++) {
                if (i != j && graph[i][j] == Integer.MAX_VALUE && graph[j][i] == Integer.MAX_VALUE) {
                    canDetermineRank = false;
                    break;
                }
            }
            if (canDetermineRank) {
                answer++;
            }
        }

        return answer;
    }
}
