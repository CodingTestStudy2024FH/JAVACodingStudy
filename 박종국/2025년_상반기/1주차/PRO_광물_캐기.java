package simulation;
import java.util.Arrays;

public class PGS_광물_캐기 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] picks = {1, 0, 1};
        String[] minerals = {"iron", "iron", "iron", "iron", "iron", "diamond"};
        System.out.println(solution.solution(picks, minerals));
    }

    static public class Solution {
        static final String DIA = "diamond", IRON = "iron", STONE = "stone";
        public int solution(int[] picks, String[] minerals) {
            int maximumIndexNumber = Arrays.stream(picks).sum();
            int mineChances = 5;
            int answer = 0;
            // sum을 기준으로 내림차순 정렬
            Node[] mineralSum = makeStonesMaximumSum(minerals, mineChances);
            for(Node node: mineralSum) {
                // 곡괭이의 갯수와 배열의 index가 같다면 도달할 수 없는 공간
                if(node.index == maximumIndexNumber) continue;
                answer += calcTiredness(picks, node.sum);
            }
            System.out.println(Arrays.toString(mineralSum));

            return answer;
        }

        int calcTiredness(int[] picks, int mineralValue){
            int index = 0;
            int result = 0;
            while(index < 3){
                if(picks[index] == 0){
                    index++;
                    continue;
                }

                if(index == 0) result = 5;
                if(index == 1) result = mineralValue/5 + mineralValue%5;
                if(index == 2) result = mineralValue;
                picks[index]--;
                return result;
            }
            return 0;
        }

        Node[] makeStonesMaximumSum(String[] minerals, int mineChances) {
            int mineralsLength = minerals.length;
            int resultSize = mineralsLength/mineChances + (mineralsLength%mineChances == 0 ? 0 : 1);
            System.out.println(resultSize);
            Node[] result = new Node[resultSize];
            for(int s = 0; s < resultSize; s++){
                int sum = 0;
                int diamondCount = 0, ironCount = 0, stoneCount = 0;
                for(int p = s*mineChances; p < (s+1)*mineChances && p < mineralsLength; p++){
                    String mineral = minerals[p];
                    switch(mineral){
                        case DIA -> diamondCount++;
                        case IRON -> ironCount++;
                        case STONE -> stoneCount++;
                    }
                    sum += mineralValue(mineral);
                }
                result[s] = new Node(sum, s, diamondCount, ironCount, stoneCount);
            }
            Arrays.sort(result);
            return result;
        }

        int mineralValue(String mineral){
            if(mineral.equals(DIA)) return 25;
            if(mineral.equals(IRON)) return 5;
            // stone과 같은 상황
            return 1;
        }

        static class Node implements Comparable<Node>{
            int sum, index, diamondCount, ironCount, stoneCount;

            public Node(int sum, int index, int diamondCount, int ironCount, int stoneCount) {
                this.sum = sum;
                this.index = index;
                this.diamondCount = diamondCount;
                this.ironCount = ironCount;
                this.stoneCount = stoneCount;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "sum=" + sum +
                        ", index=" + index +
                        ", diamondCount=" + diamondCount +
                        ", ironCount=" + ironCount +
                        ", stoneCount=" + stoneCount +
                        '}';
            }

            @Override
            public int compareTo(Node o) {
                return o.sum - this.sum;
            }
        }
    }
}

