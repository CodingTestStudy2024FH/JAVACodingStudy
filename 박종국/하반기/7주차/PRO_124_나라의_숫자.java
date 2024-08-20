class Solution {
    public String solution(int n) {
        int[] fourOneTwo = {4, 1, 2};
        StringBuilder answer = new StringBuilder();
        while(n > 0){
            int div = n%3;
            answer.insert(0, fourOneTwo[div]);
            n = (int) Math.floor((n-1)/3);
            System.out.println(answer);
        }
        return answer.toString();
    }
}
