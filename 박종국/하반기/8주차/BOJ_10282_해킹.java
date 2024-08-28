import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int T, vertex, edge, hacked;
    static int[] dist;
    static ArrayList<ArrayList<Computer>> computers;
    static PriorityQueue<Computer> pq;
    static final int MAX = 987_654_321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            vertex = Integer.parseInt(st.nextToken());
            edge = Integer.parseInt(st.nextToken());
            hacked = Integer.parseInt(st.nextToken());
            computers = new ArrayList<>();

            for(int v = 0; v <= vertex; v++) computers.add(new ArrayList<>());

            dist = new int[vertex+1];
            Arrays.fill(dist, MAX);
            for(int e = 0; e < edge; e++) {
                st = new StringTokenizer(br.readLine());
                int nextNo = Integer.parseInt(st.nextToken());
                int no = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                computers.get(no).add(new Computer(nextNo, time));
            }
            dijkstra();
            System.out.println(getResult());
        }
    }

    static String getResult(){
        int cnt = 0, time = 0;
        for(int i = 1; i <= vertex; i++){
            int value = dist[i];
            if(value == MAX) continue;
            cnt++;
            time = Math.max(time, value);
        }
        return cnt + " " + time;
    }

    static void dijkstra(){
        pq = new PriorityQueue<>();
        pq.add(new Computer(hacked, 0));
        dist[hacked] = 0;
        while(!pq.isEmpty()){
            int current = pq.poll().no;
            for(Computer next: computers.get(current)){
                if(dist[next.no] <= dist[current] + next.time) continue;
                dist[next.no] = dist[current] + next.time;
                pq.add(new Computer(next.no, dist[next.no]));
            }
        }
    }

    static class Computer implements Comparable<Computer> {
        int no, time;

        Computer(int nextNo, int time){
            this.no = nextNo;
            this.time = time;
        }

        @Override
        public String toString() {
            return String.format(" 다음 번호: %d, 시간: %d", no, time);
        }

        @Override
        public int compareTo(Computer o) {
            return this.time - o.time;
        }
    }
}
