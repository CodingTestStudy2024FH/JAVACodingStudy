import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16928_뱀과사다리게임 {
    /*
        뱀과 사다리 게임
        보드판은 10x10
        i번칸에 있고 4가 나오면 i+4번칸에 있어야한다.
        => 보드는 1~100까지

        1. 사다리면 위로 올라간다. / 원래 칸보다 큼 x->y로 이동
        2. 뱀이 있으면 내려간다. / 원래 칸보다 작다.
        100번칸에 도착하기 위해 주사위 최소 횟수값.

        냅색의 향이 강하게 나는데 아닌가
     */
    static int N,M;
    static Map<Integer,Integer> ladder,snake;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ladder = new HashMap<>();
        snake = new HashMap<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map= new int[10][10];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ladder.put(x,y);
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            snake.put(x,y);
        }

        //input end

        //logic
        System.out.println(bfs());

    }
    static boolean[][] visited; //bfs를 위한 visited
    //bfs
    public static int bfs(){
        visited = new boolean[10][10];
        visited[0][0]=true; //시작위치 true
        Deque<Point> q = new ArrayDeque<>();
        q.offer(new Point(0,0,0));
        while(true){
            Point now = q.poll();

            //주사위는 1부터 6까지 가능.
            int nowNum = (now.x)*10 + now.y+1; //단순 숫자는 1 더함 ( 0,0은 숫자 1 ~ 9,9는 100이니까 ) 
            for(int i=1;i<=6;i++){
                if(nowNum+i>100) continue; //100보다 큰경우 X
                int nextX = (nowNum+i-1)/10; //x,y좌표 계산
                int nextY = (nowNum+i-1)%10;
                if(visited[nextX][nextY])continue;//왔던경우 
                if(nowNum+i==100) { //단순히 이동한 경우
                    return now.cnt+1;
                }
                //이동했는데 사다리칸이면
                if(ladder.containsKey(nowNum+i)){
                    visited[nextX][nextY]=true;// 두 칸 이동 true로 하고
                    int move = ladder.get(nowNum + i)-1; // 좌표구하기 위해서 -1
                    visited[move/10][move%10]=true;
                    //사다리로 이동한곳에서 다시 시작
                    q.offer(new Point(move/10, move%10, now.cnt + 1));
                    continue;
                }
                //뱀이 있으면
                if(snake.containsKey(nowNum+i)){
                    visited[nextX][nextY]=true;//이동 true로 하고
                    int move = snake.get(nowNum + i)-1;
                    visited[move/10][move%10]=true;
                    //뱀을 타고 내려간곳에서 다시 시작
                    q.offer(new Point(move/10, move%10, now.cnt + 1));
                    continue;
                }
                //아무것도 없으면 그냥 이동
                visited[nextX][nextY]=true;
                q.offer(new Point(nextX, nextY, now.cnt + 1));
            }
        }

    }
    

    static class Point{
        int x,y,cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}

