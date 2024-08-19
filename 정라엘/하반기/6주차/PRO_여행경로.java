import java.util.*;

class Solution {
    private List<String> route;
    private Map<String, PriorityQueue<String>> flightsMap;

    public String[] solution(String[][] tickets) {
        // 1. 그래프 구성
        flightsMap = new HashMap<>();
        for (String[] ticket : tickets) {
            String origin = ticket[0];
            String destination = ticket[1];
            flightsMap.putIfAbsent(origin, new PriorityQueue<>());
            flightsMap.get(origin).offer(destination);
        }

        // 2. DFS 돌기
        route = new ArrayList<>();
        dfs("ICN");

        return route.toArray(new String[0]);
    }

    private void dfs(String airport) {
        // 현재 공항에서 출발하는 모든 경로를 탐색
        while (flightsMap.containsKey(airport) && !flightsMap.get(airport).isEmpty()) {
            // 사전순으로 가장 앞서는 도착지를 선택
            String nextAirport = flightsMap.get(airport).poll();
            dfs(nextAirport);
        }
        // 더 이상 갈 곳이 없으면 경로에 추가
        route.add(0, airport);
    }
}
