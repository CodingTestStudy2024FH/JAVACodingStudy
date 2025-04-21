package simulation;

import java.util.ArrayList;
import java.util.HashSet;

public class PRO_불량_사용자 {
    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
        System.out.println(new Solution().solution(user_id, banned_id));
    }
    static class Solution {
        int answer;
        HashSet<HashSet<String>> result = new HashSet<>();
        ArrayList<HashSet<String>> availableList = new ArrayList<>();
        public int solution(String[] user_id, String[] banned_id) {

            for(int i = 0; i < banned_id.length; i++) availableList.add(new HashSet<>());
            for(int i = 0; i < banned_id.length; i++) {
                for(String id : user_id){
                    if(!isBannedId(id, banned_id[i])) continue;
                    availableList.get(i).add(id);
                }
            }

            countUniqueSet(new HashSet<>(), 0);
            return result.size();
        }

        void countUniqueSet(HashSet<String> set, int depth){
            if(depth == availableList.size()){
                result.add(new HashSet<>(set));
                return;
            }

            for(String str : availableList.get(depth)){
                if(set.contains(str)) continue;
                set.add(str);
                countUniqueSet(set, depth + 1);
                set.remove(str);
            }
        }

        boolean isBannedId(String target, String banned){
            if(target.length() != banned.length()) return false;
            for(int index = 0; index < banned.length(); index++){
                if(banned.charAt(index) == '*') continue;
                if(target.charAt(index) != banned.charAt(index)) return false;
            }
            return true;
        }
    }
}
