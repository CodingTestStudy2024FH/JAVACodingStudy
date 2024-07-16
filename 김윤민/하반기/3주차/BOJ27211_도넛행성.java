package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
    N x M 행성의 각 칸은 숲으로 막혀있거나 지나갈 수 이씀

    본인 집 위치 : 0,0
    상하좌우 가능
    오른쪽 : (0,1)
    아래 : (1,0)
    A에서 시작해서 숲에 막히지 않고 비어있는 칸 B에 도달할 수 있으면 같은구역.
    탐험할 수 있는 빈 구역의 개수는?

    구냥 BFS구먼,,
    너무 쉬운걸 가져왔네 ㅜ
 */
public class BOJ27211_도넛행성 {
    static int N,M;
    static int[][] map;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i=0;i<N;i++){
            st= new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[N][M];
        //input end

        System.out.println(CountSection());

    }
    public static int CountSection(){
        int cnt =0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]==0){
                    cnt++; //증가
                    bfs(i,j);
                }
            }
        }
        return cnt;
    }
    public static void bfs(int y,int x){
        Deque<Point> q = new ArrayDeque<>();
        q.offer(new Point(y,x)); 
        visited[y][x]=true;
        while (!q.isEmpty()){
            Point now = q.poll();
            map[now.y][now.x]=1; //방문한 곳 1 처리
            for(int i=0;i<4;i++){
                int nx = now.x+dx[i];
                int ny =now.y+dy[i];
                nx = checkNx(nx); //도넛 체크
                ny = checkNy(ny); //도넛 체크
                if(map[ny][nx]==1) continue;//만약 1이면 못가니까 패스
                if(visited[ny][nx]) continue; //이미 왔으면 넘기고
                //안왔으면
                q.offer(new Point(ny,nx));
                visited[ny][nx]=true;
            }
        }
    }
    static int checkNx(int nx){//M
        if(nx>=0&&nx<M){
            return nx;
        }else if(nx<0){
            return M-1;
        }else {
            return 0;
        }
    }
    static int checkNy(int ny){//N
        if(ny>=0&&ny<N){
            return ny;
        }else if(ny<0){
            return N-1;
        }else {
            return 0;
        }
    }



    static class Point{
        int y,x;

        public Point(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }
}
