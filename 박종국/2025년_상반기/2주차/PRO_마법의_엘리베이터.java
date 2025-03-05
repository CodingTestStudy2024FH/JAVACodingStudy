package math;

public class PRO_마법의_엘리베이터 {
    public static void main(String[] args) {
        int storey = 16;
        Solution solution = new Solution();
        System.out.println(solution.solution(storey));
    }
    static class Solution {
        public int solution(int storey) {
            int answer = 0;

            while(storey > 0){
                int lastDigit = (storey % 10);
                if (lastDigit > 5) {
                    answer += (10 - lastDigit);
                    storey = (storey / 10) + 1;
                }
                else if (lastDigit < 5) {
                    answer += lastDigit;
                    storey = storey / 10;
                }

                else {
                    int nextDigit = (storey / 10) % 10;
                    if (nextDigit >= 5) {
                        answer += (10 - lastDigit);
                        storey = (storey / 10) + 1;
                    } else {
                        answer += lastDigit;
                        storey = storey / 10;
                    }
                }
            }
            return answer;
        }
    }
}
