import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        // 전처리 단계
        Map<String, List<Integer>> people = preprocess(info);

        // 쿼리 처리 단계
        List<Integer> results = queryResults(people, query);

        // 결과를 int 배열로 변환하여 반환
        return results.stream().mapToInt(i -> i).toArray();
    }

    private Map<String, List<Integer>> preprocess(String[] info) {
        Map<String, List<Integer>> people = new HashMap<>();

        for (String person : info) {
            String[] parts = person.split(" ");
            int score = Integer.parseInt(parts[4]);
            String[] keys = Arrays.copyOfRange(parts, 0, 4);

            // 4개의 항목에서 부분집합을 만들어냄
            for (int i = 0; i < (1 << 4); i++) {
                StringBuilder key = new StringBuilder();
                for (int j = 0; j < 4; j++) {
                    if ((i & (1 << j)) != 0) {
                        key.append(keys[j]);
                    } else {
                        key.append("-");
                    }
                }
                String keyStr = key.toString();
                people.computeIfAbsent(keyStr, k -> new ArrayList<>()).add(score);
            }
        }

        // 모든 점수 리스트를 정렬
        for (List<Integer> scores : people.values()) {
            Collections.sort(scores);
        }

        return people;
    }

    private List<Integer> queryResults(Map<String, List<Integer>> people, String[] query) {
        List<Integer> results = new ArrayList<>();

        for (String q : query) {
            String[] qParts = q.replace(" and ", " ").split(" ");
            int qScore = Integer.parseInt(qParts[4]);
            String qKey = String.join("", Arrays.copyOfRange(qParts, 0, 4));

            if (people.containsKey(qKey)) {
                List<Integer> scores = people.get(qKey);
                int idx = Collections.binarySearch(scores, qScore);
                if (idx < 0) {
                    idx = -idx - 1;
                } else {
                    // 점수와 같은 첫 번째 인덱스를 찾기 위해서
                    while (idx > 0 && scores.get(idx - 1) == qScore) {
                        idx--;
                    }
                }
                results.add(scores.size() - idx);
            } else {
                results.add(0);
            }
        }

        return results;
    }
}
