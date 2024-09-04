class Solution {
    public int[] solution(int[] prices) {
        int size = prices.length;
        int[] answer = new int[size];

        for(int i = 0; i < size; i++){
            for(int j = i+1; j < size; j++){
                answer[i]++;
                if(prices[i] > prices[j]) break;
            }
        }
        return answer;
    }
}
