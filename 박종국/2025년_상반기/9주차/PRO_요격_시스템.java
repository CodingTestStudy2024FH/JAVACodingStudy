package simulation;

import java.util.ArrayList;
import java.util.Collections;

public class PRO_요격_시스템 {
    public static void main(String[] args) {
        int[][] targets = {{4,5}, {4, 8}, {10, 14},{11, 13}, {5, 12}, {3, 7}, {1,4}};
        System.out.println(new Solution().solution(targets));
    }
    static class Solution {
        ArrayList<Target> targets = new ArrayList<>();
        int progress;
        public int solution(int[][] targets) {
            int answer = 0;
            for(int[] target : targets) this.targets.add(new Target(target[0], target[1]));
            Collections.sort(this.targets);

            for(Target target : this.targets){
                if(progress > target.start) continue;
                progress = target.end;
                answer++;
            }

            return answer;
        }
        class Target implements Comparable<Target>{
            int start, end;

            public Target(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public String toString() {
                return "Target{" +
                        "start=" + start +
                        ", end=" + end +
                        '}';
            }

            @Override
            public int compareTo(Target o) {
                return this.end - o.end;
            }
        }
    }
}
