import java.io.*;
import java.util.*;

public class BOJ17836_공주님을구해라 {
    /*
        N,M크기 성
        입구는 1,1

        T시간 이내로 용사를 만나지 못하면 돌로 바뀜
        한칸 이동하는데 1시간 걸리는 용사.

        칼을 얻은 경우는 벽을 다 부수고 공주에게로 갈 수 있다.
        얼마나 빨리 갈 수 있는가?
     */
    static int N,M,T;
    static int[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map= new int[N][M];
        visited=new boolean[2][N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        //input end

        //logic start
        bfs(0,0,0,false);
        if(min==Integer.MAX_VALUE||min>T){
            System.out.println("Fail");
        }else{
            System.out.println(min);
        }

    }

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int min = Integer.MAX_VALUE;
    static int bfs(int y,int x,int cnt,boolean isSword){
        Deque<Point> q = new ArrayDeque<>();
        q.offer(new Point(y,x,cnt));
        if(isSword){ // 소드 가졌을때 아닐때 visited 따로 연산
            visited[1][y][x]=true;
        }else{
            visited[0][y][x]=true;
        }

        while(!q.isEmpty()){
            Point now = q.poll();
            for(int i=0;i<4;i++){
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];
                //갈수 있는 경우에서도 수가 갈림
                if(can(ny,nx)&&!visited[isSword?1:0][ny][nx]){
                    if(ny==N-1&&nx==M-1){ //공주면 바로 return
                        min = Math.min(min,now.cnt+1);
                        return min;
                    }
                    visited[isSword?1:0][ny][nx]=true; //왔다고 체크하고
                    //1. 갔는데 소드라면, bfs한번 더돌려서 sword에서 공주까지의 거리를 구한다.
                    if(map[ny][nx]==2){
                        int bfs = bfs(ny, nx, now.cnt + 1, true);
                        min = Math.min(min,bfs); //소드를 가지고 bfs 돌았을때랑, 아닐때랑 비교
                    }else{//2. 소드가 아니라면?
                        if (map[ny][nx] == 0) { //빈벽 : 소드, 일반
                            q.offer(new Point(ny,nx,now.cnt+1));
                        }else if(map[ny][nx]==1&&isSword){ //마법의벽 : 소드
                            q.offer(new Point(ny,nx,now.cnt+1));
                        }
                    }
                }
            }
        }
        return 0;
    }
    static class Point{
        int y,x,cnt;

        public Point(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }

    static boolean can(int y,int x){
        if(y<0||x<0||y>=N||x>=M) return false;
        return true;
    }
}
