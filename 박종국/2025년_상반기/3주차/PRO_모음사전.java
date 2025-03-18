package bruteforce;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PRO_모음사전 {
    public static void main(String[] args) {
        String word = "I";
        Solution solution = new Solution();
        System.out.println(solution.solution(word));
    }
    static class Solution {
        PriorityQueue<String> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        char[] vowels = {'A', 'E', 'I', 'O', 'U'};
        List<String> list = new ArrayList<>();
        public int solution(String word) {
            makeVowelDict(0);
            pq.addAll(list);
            int cnt = 0;
            while(!pq.isEmpty()) {
                cnt++;
                String beCompared = pq.poll();
                if(!word.equals(beCompared)) continue;
                return cnt;
            }
            return cnt;
        }
        void makeVowelDict(int depth){
            if(depth == 5) return;
            for(int d = 0; d < 5; d++){
                char ch = vowels[d];
                sb.append(ch);
                list.add(sb.toString());
                makeVowelDict(depth+1);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}
