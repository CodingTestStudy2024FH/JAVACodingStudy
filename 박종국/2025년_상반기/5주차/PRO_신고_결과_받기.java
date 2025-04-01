package simulation;

import java.util.*;

public class PRO_신고_결과_받기 {
    public static void main(String[] args){
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k = 2;
        System.out.println(Arrays.toString(new Solution().solution(id_list, report, k)));
    }
    static class Solution {
        public int[] solution(String[] id_list, String[] report, int k) {
            int[] answer = new int[id_list.length];
            // 순서가 유지되는 Set
            HashSet<String> set = new HashSet<>(List.of(report));
            HashMap<String, Integer> map = new HashMap<>();

            for(int i = 0; i < id_list.length; i++) map.put(id_list[i], i);
            StringTokenizer st;

            HashMap<String, Integer> reportCounter = new HashMap<>();

            for(String str : set){
                st = new StringTokenizer(str);
                st.nextToken();
                String rep = st.nextToken();
                reportCounter.put(rep, reportCounter.getOrDefault(rep, 0) + 1);
            }

            for(String rep : set){
                st = new StringTokenizer(rep);
                String reporter = st.nextToken();
                String reported = st.nextToken();

                if(reportCounter.getOrDefault(reported, 0) < k) continue;
                answer[map.get(reporter)]++;
            }

            return answer;
        }
    }
}
