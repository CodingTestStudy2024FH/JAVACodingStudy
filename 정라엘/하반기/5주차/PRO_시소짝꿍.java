import java.util.HashMap;
import java.util.Map;

class Solution {
    public long solution(int[] weights) {
        Map<Integer, Integer> weightCount = new HashMap<>();

        // 무게의 빈도 수 계산
        for (int weight : weights) {
            weightCount.put(weight, weightCount.getOrDefault(weight, 0) + 1);
        }

        long pairCount = 0;

        // 무게 쌍을 찾아서 조건을 만족하는지 확인
        for (int weight1 : weightCount.keySet()) {
            for (int weight2 : weightCount.keySet()) {
                if (weight1 < weight2) {
                    if (isSeeSawBalanced(weight1, weight2)) {
                        pairCount += (long) weightCount.get(weight1) * weightCount.get(weight2);
                    }
                } else if (weight1 == weight2) {
                    int count = weightCount.get(weight1);
                    pairCount += (long) count * (count - 1) / 2;
                }
            }
        }

        return pairCount;
    }

    private static boolean isSeeSawBalanced(int weight1, int weight2) {
        // 시소 균형 조건
        return weight1 == weight2 || weight1 * 2 == weight2 * 3 || weight1 * 3 == weight2 * 2
                || weight1 * 4 == weight2 * 3 || weight1 * 3 == weight2 * 4
                || weight1 * 2 == weight2 || weight1 == weight2 * 2;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] weights = {40, 40, 60, 80};
        long result = solution.solution(weights);
        System.out.println(result);  // 결과를 출력, 예상 결과: 4
    }
}
