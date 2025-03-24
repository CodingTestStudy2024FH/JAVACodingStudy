import java.io.*;
import java.util.*;

public class BOJ2170_선긋기 {
    /*
        그리디같음.
        그려진 선들의 총 길이
        PQ 내림차순 정렬 ( y기준으로 )

        1. 이어진 경우
        min 시작점, max 끝점 기억해뒀다가
        * next의 끝점이 min시작점보다 크거나 같으면
        - next의 시작점도 min시작점과 비교
        만약 next가 더 작으면, min갱신
        next가 더 크면 진행.

        2. 만약에 이어지지 않은 경우라면,
        이전값은 길이 계산
        다시 min 시작점, max끝점 갱신
     */
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Point> pq = new PriorityQueue<>();
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.offer(new Point(x,y));
        }
        //input end

        int sum = 0;
        int minSt = Integer.MAX_VALUE;//최대 수
        int maxEd = Integer.MIN_VALUE;
        while(!pq.isEmpty()){
            Point now = pq.poll();
            //이어지지 않은 경우
            if(now.y<minSt){
                //처음이 아닐때 길이 계산
                if(minSt!=Integer.MAX_VALUE){
                    sum += (maxEd-minSt);
                }
                //계산 후 값 변경
                minSt = now.x;
                maxEd = now.y;
            }else{ //이어진 경우
                //minSt와 비교
                //현재 값이 더 작으면
                if(minSt>now.x){
                    minSt = now.x;//갱신하고 진행
                }
            }
        }
        sum += (maxEd - minSt);
        System.out.println(sum);


    }
    static class Point implements Comparable<Point>{
        int y,x;
        Point(int x,int y){
            this.y=y;
            this.x=x;
        }

        @Override
        public int compareTo(Point o) {
            return o.y-this.y;
        }
    }

}
