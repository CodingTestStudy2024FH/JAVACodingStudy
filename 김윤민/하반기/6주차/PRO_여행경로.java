package PRO;

import java.util.*;

public class PRO_여행경로 {
    /*
        항상 ICN에서 출발.
        방문 공항 경로를 배열에 담아 return
     */
    public static void main(String[] args) {
        String[][] arr = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        System.out.println(Arrays.toString(solution(arr)));
    }
    private static boolean[] visited;
    private static List<String> result = new ArrayList<>();
    public static String[] solution(String[][] tickets) {
        result = new ArrayList<>();
        visited = new boolean[tickets.length];

        dfs(0, "ICN", "ICN", tickets);
        Collections.sort(result); //String 정렬

        String[] answer = result.get(0).split(" ");
        return answer;
    }
    public static void dfs(int idx, String start, String route, String[][] tickets) {
        if (idx == tickets.length) { //종료조건
            result.add(route);  //결과에 넣어주고
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals(start) && !visited[i]) { //같고 안갔으면
                visited[i] = true; //갔다고 체크
                //경로 route에 추가해주며 dfs한번 더 돈다.
                dfs(idx + 1, tickets[i][1], route + " " + tickets[i][1], tickets);
                visited[i] = false;
            }
        }
        return;
    }
}
