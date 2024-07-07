import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ6593_상범빌딩 {
    /*
        탈출하는 가장 빠른 길
        - 각 변의 길이가 1인 정육면체
        - 지나갈 수 있거나, 업석나
        - 6개의 칸으로 1분 시간을 들여 이동 가능 = 3차원 
        - 출구를 통해서만 탈출할 수 있다.
        
        L : 층
        R : 행
        C : 열
        
       # : 지나갈 수 없음
       . : 가능
       S : 시작
       E : 탈출구

       끝은 0 0 0
     */
    static int L,R,C,min;
    static char[][][] map;

    static int[] dc = {1,-1,0,0,0,0};
    static int[] dr = {0,0,1,-1,0,0};
    static int[] dl = {0, 0, 0, 0, 1, -1};
    static Point start,end;
    static boolean[][][] isVisited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            StringTokenizer st =new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L==0&&R==0&&C==0) break;
            map = new char[L][R][C];
            isVisited = new boolean[L][R][C];
            for(int i=0;i<L;i++){
                for(int j=0;j<R;j++){
                    String s = br.readLine();
                    for(int z=0;z<C;z++){
                        map[i][j][z]=s.charAt(z);
                        if(map[i][j][z]=='S') start = new Point(i, j, z);
                        if(map[i][j][z]=='E') end = new Point(i, j, z);
                    }
                }
                br.readLine();//빈공간 빼기
            }
            System.out.println(bfs());
        }

    }
    static String bfs(){
        //시작부분 visited처리
        isVisited[start.l][start.r][start.c] = true;
        Deque<Point> q = new ArrayDeque<>();
        q.offer(new Point(start.l, start.r, start.c,0));
        while(!q.isEmpty()){//빌때까지 계속
            Point now = q.poll();
            for(int i=0;i<6;i++){
                int nl = now.l + dl[i];
                int nr = now.r + dr[i];
                int nc = now.c+dc[i];
                //갈 수 있는 위치고, .이고, 방문하지 않았다면 간다.(시간+)
                if(can(nl,nr,nc)&&!isVisited[nl][nr][nc]){
                    if(map[nl][nr][nc]=='.'){
                        q.offer(new Point(nl,nr,nc,now.time+1));
                        isVisited[nl][nr][nc]=true;
                    }else if(map[nl][nr][nc]=='E'){
                        return "Escaped in "+(now.time+1)+" minute(s).";
                    }
                }
            }
        }
        return "Trapped!";
    }
    public static boolean can(int l,int r,int c){
        return l < L && r < R && c < C && c >= 0 && r >= 0 && l >= 0;
    }
    static class Point{
        int l,r,c,time;

        public Point(int l, int r, int c) {
            this.l = l;
            this.r = r;
            this.c = c;
        }

        public Point(int l, int r, int c, int time) {
            this.l = l;
            this.r = r;
            this.c = c;
            this.time = time;
        }

    }
}
