class Solution {
    boolean[][] visited;
    int[] dx = {-1, 1, 0, 0}; // 상하좌우 방향 벡터
    int[] dy = {0, 0, -1, 1};
    int M, N;

    public int[] solution(int m, int n, int[][] picture) {
        M = m;
        N = n;
        visited = new boolean[m][n];

        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] > 0 && !visited[i][j]) { // 아직 방문하지 않은 색칠된 영역 발견
                    numberOfArea++;
                    int sizeOfArea = dfs(i, j, picture, picture[i][j]);
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, sizeOfArea);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private int dfs(int x, int y, int[][] picture, int color) {
        visited[x][y] = true;
        int areaSize = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                if (!visited[nx][ny] && picture[nx][ny] == color) {
                    areaSize += dfs(nx, ny, picture, color);
                }
            }
        }

        return areaSize;
    }
}
