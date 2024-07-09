package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11650_좌표_정렬하기 {
    /*
       좌표를 x좌표가 증가하는 순
       x좌표가 같으면 y좌표가 증가하는 순으로 정렬

       ?
       pq사용
     */
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Point> pq = new PriorityQueue<>();
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y= Integer.parseInt(st.nextToken());
            pq.offer(new Point(x,y));
        }
        for(int i=0;i<N;i++){
            System.out.println(pq.poll());
        }
        return;
    }
    static class Point implements Comparable<Point> {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if(this.x==o.x) return this.y-o.y;
            return this.x-o.x;
        }

        @Override
        public String toString() {
            return this.x+" "+this.y;
        }
    }
}
