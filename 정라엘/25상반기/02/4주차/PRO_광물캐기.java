import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    int[][] fatigue = { // 피로도 표
            {1, 1, 1},   // 다이아 곡괭이
            {5, 1, 1},   // 철 곡괭이
            {25, 5, 1}   // 돌 곡괭이
    };
    
    public int solution(int[] picks, String[] minerals) {
        dfs(0, 0, picks, minerals);
        return answer;
    }

    private void dfs(int idx, int totalFatigue, int[] picks, String[] minerals) {
        // 종료 조건 (광물을 다 캤거나, 곡괭이가 없을 때)
        if (idx >= minerals.length || (picks[0] == 0 && picks[1] == 0 && picks[2] == 0)) {
            answer = Math.min(answer, totalFatigue);
            return;
        }

        // 5개씩 광물 묶기
        int endIdx = Math.min(idx + 5, minerals.length);
        int[] count = new int[3]; // 광물 개수 (0: 다이아, 1: 철, 2: 돌)
        for (int i = idx; i < endIdx; i++) {
            switch (minerals[i]) {
                case "diamond": count[0]++; break;
                case "iron": count[1]++; break;
                case "stone": count[2]++; break;
            }
        }

        // 곡괭이 순서대로 사용 (다이아 -> 철 -> 돌)
        for (int i = 0; i < 3; i++) {
            if (picks[i] > 0) { // 해당 곡괭이가 있을 경우
                picks[i]--; // 곡괭이 사용
                int fatigueSum = count[0] * fatigue[i][0] + count[1] * fatigue[i][1] + count[2] * fatigue[i][2];
                dfs(endIdx, totalFatigue + fatigueSum, picks, minerals);
                picks[i]++; // 곡괭이 복구
            }
        }
    }
}
