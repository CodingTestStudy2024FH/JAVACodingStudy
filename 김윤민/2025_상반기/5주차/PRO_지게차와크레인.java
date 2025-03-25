import java.util.*;

public class PRO_지게차와크레인 {
    public static void main(String[] args) {
        String[] arr ={"HAH", "HBH", "HHH", "HAH", "HBH"};
        String[] request={"C", "B", "B", "B", "B", "H"};
        System.out.println(solution(arr,request));
    }
    /*
      알파벳 하나 -> 외부에서 접근가능한 모든 종류
      두개-> 모든종류 (내부에 있는것도 포함)

      치즈랑 약간 비슷한거같은데
      외부에 있는 Point따로 관리
    */
    static char[][] map;
    static boolean[][] check;
    static int N,M,cnt;
    static Map<Character,List<Point>> out;
    static Map<Character,List<Point>> all;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        map = new char[N][M];
        check = new boolean[N][M];
        cnt = N*M;
        out = new HashMap<>();
        all = new HashMap<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                map[i][j]=storage[i].charAt(j);
                if(all.get(map[i][j])==null){
                    all.put(map[i][j],new ArrayList<>());
                }
                all.get(map[i][j]).add(new Point(i,j));
                if(out.get(map[i][j])==null){
                    out.put(map[i][j],new ArrayList<>());
                }
                if(i==0||j==0||j==M-1||i==N-1){//끝이면
                    check[i][j]=true; //외곽 들어있다고 체크하고
                    out.get(map[i][j]).add(new Point(i, j)); //추가하기
                }
            }
        }
        //input end

        //logic start
        for(String request:requests){
            char target = request.charAt(0);
            int removeCnt=0;
            //1. 외곽꺼부터 빼기
            List<Point> outPoints = out.get(target); //셋 빼오고
            out.put(target,new ArrayList<>());//초기화 해주기
            if(outPoints!=null){
                for(Point p:outPoints){
                    removeCnt++;
                    setOut(p); //외곽꺼 추가
                    all.get(target).remove(p);//전체에서 삭제
                }
            }


            //2. 전체빼기
            //근데 이미 외곽꺼 빼다가 추가된 외곽꺼일수도 있음. 고려해서 연산하기. check가 true로 되어있으면 out에서 그거 빼줘야함.
            if(request.length()>1){
                List<Point> points = all.get(target);
                all.put(target,new ArrayList<>());//다 빼주기
                if(points!=null){
                    for(Point p:points){
                        removeCnt++;
                        if(check[p.y][p.x]){//외곽꺼면
                            out.get(target).remove(p);//본인꺼 없애고
                            setOut(p);//한번 더 외곽꺼 더해주고
                        }
                    }
                }

            }
            cnt-=removeCnt;
        }

        return cnt;
    }

    static void setOut(Point p){
        for(int i=0;i<4;i++){
            int ny = p.y+dy[i];
            int nx = p.x+dx[i];
            if(can(ny,nx)&&!check[ny][nx]){ //아직 false면 외곽에 안들어갔다는 의미
                check[ny][nx]=true;
                out.get(map[ny][nx]).add(new Point(ny,nx));
            }
        }
    }


    static class Point{
        int y,x;
        Point(int y,int x){
            this.y=y;
            this.x=x;
        }
        @Override
        public boolean equals(Object obj) {
            Point m = (Point)obj;
            return m.y == this.y && m.x == this.x;
        }

    }
    public static boolean can(int y,int x){
        if(y>=0&&x>=0&&y<N&&x<M) return true;
        return false;
    }
}
