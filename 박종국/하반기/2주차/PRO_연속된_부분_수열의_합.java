package etc;

import java.io.IOException;
import java.util.Arrays;

public class PRO_연속된_부분_수열의_합 {
    public static void main(String[] args) throws IOException {
        int[] sequence = {1, 1, 1, 2, 3, 4, 5};
        int k = 5;
        System.out.println(Arrays.toString(solution(sequence, k)));
    }

    static int[] solution(int[] sequence, int k){
        int MIN = Integer.MAX_VALUE;

        int[] answer = new int[2];
        int start = 0; int end = 1;
        int limit = sequence.length;
        int sum = sequence[start];
        while(start < end){
            // 두 인덱스의 값이 k와 같을 때
            if(sum == k){
                if(MIN > end - start) {
                    MIN = end - start;
                    answer[0] = start;
                    answer[1] = end-1;
                }
                sum -= sequence[start++];
            }
            // 목표값보다 커서 start와 end의 간격을 줄어야할때
            else if(sum > k){
                sum -= sequence[start++];
            }
            // 목표값보다 작으면서 최대 범위를 넘어가지 않는 조건
            else if (end < limit){
                sum += sequence[end++];
            }
            // 위에 해당되지 않는 범위라면 탈출
            else break;

        }

        return answer;
    }
}
