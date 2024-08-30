import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Stack<Integer> s = new Stack<>();   // 가격 떨어지지 않는 '인덱스' 저장
        
        // prices 배열 순회
        for(int i=0; i<prices.length; i++){
            while (!s.isEmpty() && (prices[s.peek()] > prices[i])){
                int idx = s.pop();
                answer[idx] = i-idx;  // 초 계산 가능
            }
            
            // 아닌 경우 증가하므로 스택에 추가
            s.push(i);
        }
        
        // 스택에 남아있는 경우(끝까지 가격이 떨어지지 않은 경우) 처리
        while(!s.isEmpty()){
            int idx = s.pop();
            answer[idx] = prices.length - 1 - idx;  // 배열 길이 - idx
        }
        
        return answer;
    }
}
