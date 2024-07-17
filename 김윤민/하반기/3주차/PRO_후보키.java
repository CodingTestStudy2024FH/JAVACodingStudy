package PRO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PRO_후보키 {
    /*
        모든 인적사항은 DB에
        - 유일성, 최소성

        릴레이션의 모든 튜플을 유일하게 식별하는데 필요한 속성들로만.

        후보키의 최대 개수는?
        ----
        모르게씅ㅁ ㅜ
     */
    static int answer;
    static int n,m;
    static List<HashSet<Integer>> candidateKey;
    static String[][] relationCopy;
    public static void main(String[] args) {
        String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},
                {"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},
                {"600","apeach","music","2"}};
        System.out.println(solution(relation));
    }
    public static int solution(String[][] relation) {
        relationCopy = relation;

        answer = 0;

        // 중복 조합을 고려하여 HashSet 사용
        candidateKey = new ArrayList<>();
        n = relationCopy.length;
        m = relationCopy[0].length;

        // 1부터 m까지 사이즈만큼 조합 생성하기
        for (int i = 1; i < m + 1; i++) {
            comb(0, i, 0, new HashSet<>());
        }

        return answer;
    }
    // 일일히 다 돌면서 조합 조회
    static void comb(int idx, int size, int depth, HashSet<Integer> set) {
        // 조합이 만들어지면
        if(depth == size) {
            // 유일성 검사
            if(!unique(set)) {
                return;
            }
            // 최소성 검사
            for (HashSet<Integer> key : candidateKey) {
                if(set.containsAll(key)) {
                    return;
                }
            }
            // 조합을 추가하고 answer 증가
            candidateKey.add(set);
            answer++;
            return;
        }
        // 조합 만들기
        for (int i = idx; i < m; i++) {
            HashSet<Integer> newSet = new HashSet<>(set);
            newSet.add(i);
            idx++;
            comb(idx, size, depth + 1, newSet);
        }
    }
    // 유일성 검사 메서드
    static boolean unique(HashSet<Integer> set) {
        List<String> list = new ArrayList<>();
        // 만들어진 조합으로 중복되는지 검사
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int idx : set) {
                sb.append(relationCopy[i][idx]);
            }
            if(!list.contains(sb.toString())) {
                list.add(sb.toString());
            } else {
                return false;
            }
        }
        return true;
    }
}
