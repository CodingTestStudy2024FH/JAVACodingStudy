import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class PRO_석유시추 {
    static int[] parents;
    static int[][] s;//면적
    static int m,n;
    public static void main(String[] args) throws Exception{
       int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0},{0, 0, 0, 0, 1, 1, 0, 0},{1, 1, 0, 0, 0, 1, 1, 0},{1, 1, 1, 0, 0, 0, 0, 0},{1, 1, 1, 0, 0, 0, 1, 1}};
        System.out.println(solution(land));
    }
    public static int solution(int[][] land) {
        int answer = 0;
        n = land.length;
        m = land[0].length;
        parents =new int[n*m];
        s = new int[n][m];
        //parents초기화
        for(int i=0;i<n*m;i++){
            parents[i]=i;
        }
        //bfs
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(land[i][j]==1 && parents[i*m+j]==(i*m+j)){ //union이 안된상태라면, bfs진행
                    s[i][j]=bfs(i,j,land);
                }
            }
        }
        //밑으로 내려가면서 union & find 체크해주면서 너비 누적으로 더해서 결론도출

        int max=0;
        for(int j=0;j<m;j++){ //아래로 쭉
            int sum=0;
            boolean[] check = new boolean[n*m];
            for(int i=0;i<n;i++){
                int root = find(i*m+j); //부모를 찾고
                if(check[root]) continue; //근데 부모가 이미 더해졌으면 넘기고
                check[root]=true; // 아니면 더한다.
                sum += s[root / m][root % m];
            }
            max = Math.max(sum,max);
        }
        return max;
    }

    static int[] dy = {0,0,1,-1};
    static int[] dx = {1, -1, 0, 0};

    public static int bfs(int y,int x,int[][] land){
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.offer(new Point(y, x));
        boolean[][] visited = new boolean[n][m];
        visited[y][x]=true;
        int ans=1;
        while (!q.isEmpty()){
            Point p = q.poll();
            for(int i=0;i<4;i++){
                int ny= p.y+dy[i];
                int nx = p.x+dx[i];
                if(can(ny,nx)&&!visited[ny][nx]&&land[ny][nx]==1){
                    ans++;
                    visited[ny][nx]=true;
                    union(new Point(y, x), new Point(ny, nx));
                    q.offer(new Point(ny,nx));
                }
            }
        }
        return ans;
    }
    public static boolean can(int y,int x){
        if(y<0||x<0||x>=m||y>=n) return false;
        return true;
    }
    public static boolean union(Point a,Point b){
        int aRoot = find(a.y*m+a.x);
        int bRoot = find(b.y*m+b.x);
        if(aRoot==bRoot){ //이미 같은 union이므로 안합쳐도 됨.
            //false의 의미: union이다.
            return false;
        }
        // union이 아니면 합쳐준다.
        int bStatus = b.y* m + b.x;
        int aStatus = a.y* m + a.x;
        parents[bStatus]=aStatus;
        return true;
    }
    public static int find(int p){
        if(parents[p]==p) return p;
        return parents[p]=find(parents[p]);
    }
    static class Point{
        int y,x;
        Point(int y,int x){
            this.y=y;
            this.x=x;
        }
    }
}
