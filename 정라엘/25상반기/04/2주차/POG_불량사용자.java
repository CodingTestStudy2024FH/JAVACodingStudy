import java.util.*;

//해시 쓰는 거구만
class Solution {
    HashSet<String> resultSet = new HashSet<>();
    boolean[] visited;

    public int solution(String[] user_id, String[] banned_id) {
        visited = new boolean[user_id.length];
        backtrack(user_id, banned_id, new ArrayList<>());
        return resultSet.size();
    }

    // 아 다 찾을라믄 백트래킹 필요
    public void backtrack(String[] user_id, String[] banned_id, List<String> selected) {
        if (selected.size() == banned_id.length) {
            // 중복 방지를 위해 정렬 후 문자열로 저장
            List<String> sorted = new ArrayList<>(selected);
            Collections.sort(sorted);
            resultSet.add(String.join(",", sorted));
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (visited[i]) continue;
            if (isMatch(user_id[i], banned_id[selected.size()])) {
                visited[i] = true;
                selected.add(user_id[i]);
                backtrack(user_id, banned_id, selected);
                selected.remove(selected.size() - 1);
                visited[i] = false;
            }
        }
    }

    // 와일드카드 포함 비교 함수
    public boolean isMatch(String user, String banned) {
        if (user.length() != banned.length()) return false;

        for (int i = 0; i < user.length(); i++) {
            if (banned.charAt(i) == '*') continue;
            if (user.charAt(i) != banned.charAt(i)) return false;
        }
        return true;
    }
}
