class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int n = citations.length;

        for(int i = n; i >= 1; i--){
            int cnt = 0;
            for(int citation: citations)
                if(citation >= i) cnt++;
            if(cnt >= i) return i;
        }

        return answer;
    }
}
