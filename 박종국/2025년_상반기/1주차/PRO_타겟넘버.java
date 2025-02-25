public class PRO_타겟_넘버 {
    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(new Solution().solution(numbers, target));
    }
    static class Solution {
        int answer;
        public int solution(int[] numbers, int target) {
            dfs(0, 0, numbers, target);

            return answer;
        }
        void dfs(int depth, int sum, int[] numbers, int target){
            if(depth == numbers.length){
                if(sum == target) answer++;
                return;
            }
            dfs(depth+1, sum+numbers[depth], numbers, target);
            dfs(depth+1, sum-numbers[depth], numbers, target);
        }
    }
}
