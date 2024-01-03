import java.io.*;
import java.util.*;

public class BOJ2583_영역구하기 {

    static int M, N, K; //5,7,3
    static boolean[][] isVisited; //BFS를 위한 방문 표시
    static boolean[][] map; // true면 색칠, false면 색칠X
    static int[] dy = {1,-1,0,0};
    static int[] dx = {0,0,1,-1};
    static List<Integer> answer_list = new ArrayList<>();
    static int answer_count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new boolean[M][N];
        isVisited = new boolean[M][N];

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // 좌표 조정하기
            /** test case 예시
             * (0,2) (4,4)
             * => 0,3 / 4,1
             * */
            y1 = M - y1;
            y2 = M - y2;

            // 칸 색칠하기 (왼오방향으로 내려오기)
            int startX = x1;
            int startY = y2;

            for(int y = startY; y < y1; y++){
                for(int x = startX; x < x2; x++){
                    map[y][x] = true; // 색칠된 곳은 true 아닌 곳은 false로 세팅되도록 함
                }
            }
        }


        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(!isVisited[i][j]) {
                    // 방문 하지 않은 곳을방문하면서 영역 사이즈 재기
                    int size = BFS(i, j, map[i][j]);
                    // 이때 색칠되지 않은 곳만 영역으로 따지므로 -> map의 해당 값이 false여야함
                    if(!map[i][j]){
                        answer_list.add(size);
                        answer_count++;
                    }
                }
            }
        }

        Collections.sort(answer_list); // 오름차순 정렬
        System.out.println(answer_count);
        for(int i : answer_list){
            System.out.print(i + " ");
        }
    }

    static int BFS(int y, int x, boolean state){

        Queue<int[]> q = new ArrayDeque<>();
        int size = 0;
        q.offer(new int[] {y,x});
        isVisited[y][x] = true;

        while(!q.isEmpty()){
            size++;
            int[] cur = q.poll();

            for(int i = 0; i < 4; i++){

                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                // 범위 체크, 방문 체크
                if(ny >= M || ny < 0 || nx >= N || nx < 0) continue;
                if(isVisited[ny][nx]) continue;
                if(map[ny][nx] != state) continue;

                q.offer(new int[] {ny, nx});
                isVisited[ny][nx] = true;
            }

        }

        return size;
    }
}
