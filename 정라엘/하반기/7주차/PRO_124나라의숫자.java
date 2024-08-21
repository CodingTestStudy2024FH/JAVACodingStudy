class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        int[] nums = {4, 1, 2}; // 1, 2, 4 나라의 숫자 표현

        while (n > 0) {
            int remainder = n % 3; // n을 3으로 나눈 나머지
            n /= 3; // n을 3으로 나눔
            
            if (remainder == 0) {
                n--; // 나머지가 0일 때는 n을 1 줄여줌
            }
            
            // 나머지가 1일 때는 '1', 2일 때는 '2', 0일 때는 '4'를 추가
            answer.insert(0, nums[remainder]);
        }

        return answer.toString();
    }
}
