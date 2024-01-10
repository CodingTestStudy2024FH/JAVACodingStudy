import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* M x N
   K개의 직사각형.
   몇개의 분리된 영역
   몇개로 분리됐는지, 분리된 영역의 넓이가 얼마인지? (넓이 : 오름차순)
 */
public class BOJ2583_영역구하기 {
    /*
    input : M,N,K
     */
    static int M,N,K,count;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static int[][] map;
    /*
    1. 채운다. ( 왼쪽아래에서 오른쪽 위까지 )
       0 : 아직 넓이 계산 안됨 1 : 이미 넓이를 계산, 2 : 직사각형
    2. dfs돌면서 빈칸 있으면 bfs돌려서 넓이 구하기
     */
    public static void main(String[] args) throws Exception{
        //input start
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        //왼쪽아래 x,y 오른쪽 위 x,y
        for(int i=0;i<K;i++){ //K개만큼 반복
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken())-1;
            int y2 = Integer.parseInt(st.nextToken())-1;
            fill(x1,y1,x2,y2);
        }

        //input end & 채우기 긑남.
//        for(int i=0;i<M;i++){
//            for(int j=0;j<N;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }

        // logic
        dfs();

        // output
        StringBuilder sb = new StringBuilder();
        sb.append(count+"\n");
        while(!pq.isEmpty()){
            sb.append(pq.poll()+" ");

        }
        System.out.println(sb.toString());
    }
    // 넓이 계산하지 않은 곳을 찾아서 넓이bfs로 계산 후 pq에 offer
    public static void dfs(){
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]==0){ // 0이면 넓이 계산 안함.
                    pq.offer(bfs(i,j)); //bfs로 계산 후 넓이 추가
                    count++; //개수 적립
                }
            }
        }
    }
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    // 넓이 구함
    public static int bfs(int y,int x){
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.offer(new Point(y,x));
        map[y][x]=1;

        int ans = 1;
        while(!q.isEmpty()){
            Point now = q.poll();
            for(int i=0;i<4;i++){
                int nx = now.x+dx[i];
                int ny =now.y+dy[i];
                if(can(ny,nx)&&map[ny][nx]==0){
                    map[ny][nx]=1;
                    ans++;
                    q.offer(new Point(ny,nx));
                }
            }
        }
        return ans;
    }
    static class Point{
        int y,x;
        Point(int y,int x){
            this.y=y;
            this.x=x;
        }

    }
    // 범위 내인지?
    public static boolean can(int y,int x){
        if(y<0||x<0||x>=N||y>=M||map[y][x]!=0){
            return false;
        }
        return true;
    }
    // 직사각형 채우기
    public static void fill(int x1,int y1,int x2,int y2){
        for(int i=y1;i<=y2;i++){
            for(int j=x1;j<=x2;j++){
                map[i][j]=2;
            }
        }
    }

}