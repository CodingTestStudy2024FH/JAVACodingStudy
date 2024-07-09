package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1189_컴백홈 {
    static int col, row, num, result, count = 1;
    static boolean[][] maps;
    static int[] dy = {-1,1,0,0}, dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        num = Integer.parseInt(st.nextToken());
        maps = new boolean[col][row];
        for(int c = 0; c < col; c++){
            String str = br.readLine();
            for(int r = 0; r < row; r++) {
                // T 일때 이동하지 못하기 때문에 boolean 배열에 true로 매핑
                if(str.charAt(r) == 'T') maps[c][r] = true;
            }
        }
        // 배열 왼쪽 아래부터 시작
        maps[col-1][0] = true;
        goHome(col-1, 0);
        System.out.println(result);
    }
    static void goHome(int y, int x){
        // DFS를 돌면서 오른쪽 위에 도착하면
        if(y == 0 && x == row-1){
            // 이동한 거리랑 num이랑 같다면 result를 +1
            if(count == num){
                result++;
                return;
            }
            return;
        }
        
        // result가 num보다 크다면 더 DFS를 돌 필요가 없음
        if(result > num) return;

        for(int d = 0; d < 4; d++){
            int ny = y + dy[d];
            int nx = x + dx[d];
            if(ny < 0 || nx < 0 || ny >= col || nx >= row || maps[ny][nx]) continue;
            // 한번 이동할때마다 +1
            count++;
            // 이동한 곳을 확인 
            // maps이 방문체크면서 2차원 배열의 역할 동시에 수행
            maps[ny][nx] = true;
            
            goHome(ny, nx);
            // 재귀를 벗어났기 때문에 방문체크와 count를 원상복구
            maps[ny][nx] = false;
            count--;

        }
    }
    static class Node{
        int y, x;
        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
