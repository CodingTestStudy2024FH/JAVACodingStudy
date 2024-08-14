import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        // 1. 그래프 구성
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        // 2. 간선 제거 후 탐색
        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];

            // 간선 제거
            graph[v1].remove((Integer) v2);
            graph[v2].remove((Integer) v1);

            // 두 서브트리의 크기 계산
            int subtreeSize = bfs(v1, graph, n);

            // 전체 노드 수 - 서브트리 크기 = 다른 서브트리의 크기
            int otherSize = n - subtreeSize;

            // 두 서브트리의 크기 차이의 최솟값 갱신
            answer = Math.min(answer, Math.abs(subtreeSize - otherSize));

            // 간선 복구
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        return answer;
    }

    private int bfs(int start, List<Integer>[] graph, int n) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            count++;

            for (int neighbor : graph[node]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return count;
    }
}
