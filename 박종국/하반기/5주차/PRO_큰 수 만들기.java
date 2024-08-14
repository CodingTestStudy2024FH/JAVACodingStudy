class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        int length = number.length();
        int start = 0;
        for(int i = 0; i < length-k; i++){
            int max = 0;
            for(int j = start; j <= i+k; j++){
                int current = number.charAt(j) - '0';
                if(current > max){
                    max = current;
                    start = j+1;
                }
            }
            answer.append(max);
        }
        return answer.toString();
    }
}
