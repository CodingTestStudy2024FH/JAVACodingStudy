package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1806_부분합 {
    static int col, row, T, map[][];
    static int[] dx = {-1, 1, 0 ,0}, dy = {0, 0, -1, 1};
    static StringTokenizer st;
    // 오름차 순으로 출력하기 위해 우선순위 큐를 사용
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[col][row];
        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            // 왼쪽 아래의 좌표와 오른쪽 위의 좌표를 입력받고 2차원 배열에 매핑시킴
            mapping(y1, x1, y2, x2);
        }
        for(int c = 0; c < col; c++){
            for(int r = 0; r < row; r++){
                // 색칠되지 않은 범위의 넓이를 구하기 위해서 BFS 돌려서 넓이를 우선순위 큐에 삽입
                if(map[c][r] == 0) pq.add(search(c, r));
            }
        }
        // pq의 크기가 색칠되지 않은 범위의 값
        System.out.println(pq.size());
        // 색칠되지 않은 범위의 넓이를 오름차순으로 출력
        while(!pq.isEmpty()) System.out.printf("%d ", pq.poll());
    }

    // 2차원 배열에 입력된 곳을 확인하기 위한 함수
    static void mapping(int y1, int x1, int y2, int x2){
        // 문제를 보니까 왼쪽 아래의 좌표 순서대로 값을 준다고 했는데
        int maxY = Math.max(y1, y2);
        int minY = Math.min(y1, y2);
        int maxX = Math.max(x1, x2);
        int minX = Math.min(x1, x2);
        // 문제를 제대로 읽지 않아서 쓸모없는 값 비교를 넣었다... ㅠㅠ
        for(int y = minY; y < maxY; y++){
            for(int x = minX; x < maxX; x++){
                // 좌표 값이 넘어올때 해당 위치에 배열에 1로 표시
                if(map[y][x] == 0) map[y][x] = 1;
            }
        }
    }
    static int search(int y, int x){
        Queue<Node> queue = new ArrayDeque<>();
        // 이 함수에 접근하기 위한 접근이 색칠되지 않은 부분을 만났을 때 접근하는 것이기 때문에
        // result를 처음부터 1을 넣어주고 map에 바로 1로 매핑
        int result = 1;
        queue.add(new Node(y, x));
        map[y][x] = 1;
        while(!queue.isEmpty()){
            Node node = queue.poll();
            int mx = node.x;
            int my = node.y;
            for(int d = 0; d < 4; d++){
                int ny = my + dy[d];
                int nx = mx + dx[d];
                // map의 범위를 넘어가지 않았으며 map의 좌표가 1로 표시되어 있다면 색칠이 되어있단 뜻이므로
                // BFS를 돌지않고 continue
                // 주석을 달면서 생각하니까 굳이 int 배열로 만들 필요가 없이 boolean 배열로 하는게 
                // 좋았겠다라고 생각되네요...
                if(nx < 0 || ny < 0 || ny >= col || nx >= row || map[ny][nx] == 1) continue;
                map[ny][nx] = 1;
                queue.add(new Node(ny, nx));
                // BFS가 진행되었다면 색칠되지 않은 곳을 탐지 했다는 뜻이므로 넓이를 의미하는 result에 +1
                result++;
            }
        }
        return result;
    }
    static class Node{
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
