package simulation;

import java.util.Arrays;

public class PRO_퍼즐_게임_챌린지 {
    public static void main(String[] args) {
        int[] diffs = {1, 5, 3};
        int[] times = {2, 4, 7};
        long limit = 30;
        System.out.println(new Solution().solution(diffs, times, limit));
    }
    static class Solution {
        long start = 1, end;
        public int solution(int[] diffs, int[] times, long limit) {
            end = Arrays.stream(diffs).max().orElse(0);

            while(start < end){
                long mid = (start + end)/2;
                if(calculateLevel(diffs, times, mid) <= limit) end = mid;
                else start = mid+1;
            }

            return (int) end;
        }
        long calculateLevel(int[] diffs, int[] times, long level) {
            long answer = 0;
            long prevTime = 0;

            for(int i = 0; i < diffs.length; i++){
                int diff = diffs[i];
                int time = times[i];
                if(diff <= level) answer += time;
                else answer += (time + prevTime) * (diff - level) + time;
                prevTime = time;
            }
            return answer;
        }
    }
}
