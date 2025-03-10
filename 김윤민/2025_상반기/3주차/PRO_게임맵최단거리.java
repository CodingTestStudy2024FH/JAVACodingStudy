import java.util.*;

public class PRO_게임맵최단거리 {
    /*
        상대 팀 진영에 빨리 도착하는게 유리
        1,1,  5,5

        동,서,남,북으로 한칸씩 이동 가능

        캐릭터가 상대 팀 진영에 도착하기 위해 지나가야 하는 칸의 개수의 최소값.
        도착 못할때는 -1
        => bfs
     */

    public static void main(String[] args) {
        int[][] map = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
        System.out.println(solution(map));
    }

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int[][] count;
    static int N,M;
    public static int solution(int[][] maps) {

        N = maps.length;
        M = maps[0].length;
        count = new int[N][M];

        return logic(maps);
    }
    static int logic(int[][] maps){
        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0, 0));
        count[0][0]=1;
        //갈 수 있는 경로를 모두 선택

        //bfs 시작
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            for(int i=0;i<4;i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(!can(nx,ny)) continue;//유효하지 않으면 넘기고
                if(count[nx][ny]!=0||maps[nx][ny]==0) continue; //왔던거면 넘기고 (이미 최소값이 있음), 0이면 벽 넘김
                //안왔던거라면
                if(ny==M-1&&nx==N-1) return count[now.x][now.y]+1; //끝이면 리턴
                count[nx][ny]= count[now.x][now.y]+1;//숫자하나 더해주고
                queue.add(new Point(nx, ny)); //큐에 넣기
            }
        }


        return -1;
    }

    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    static boolean can(int x,int y){
        if(x<0||y<0||y>=M||x>=N) return false;
        return true;
    }


}
