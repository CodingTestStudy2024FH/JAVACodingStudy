package week_2nd;

public class PRO_연속된_부분수열의_합 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[] {1, 2, 3, 4, 5}, 7));
    }
}

class Solution {
    // 합이 k인 부분수열이어야 함
    // 투포인터 ? 하나씩 땡겨오면서 해야할듯
    // 비 내림차순은 뭐야 오름차순.. ?


    // 0 이고 하나씩 오른쪽으로 땡기다가 k를 넘어버리는 경우 왼쪽을 하나씩 땡기기 ㄱㄱ
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        int l = sequence.length + 1; // 여기 + 1 안해줬음 ㅜ.ㅜ
        /**
         왜 해줘야 하나 ? 최대로 나올 수 있는 길이 =  sequence.length 만큼의 길이 이므로
         */
        int sum = 0;

        for(int si = 0, ei = 0; si < sequence.length; si++){
            while(sum < k && ei < sequence.length){
                sum += sequence[ei++];
            }

            if(sum == k) {
                int length = ei - si;
                if(length < l) {
                    l = length;
                    answer = new int[] {si, ei - 1};
                }
            }

            sum -= sequence[si];
        }

        return answer;
    }
}