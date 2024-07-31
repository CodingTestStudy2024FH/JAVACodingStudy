import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] dp;
    static Counseling[] counseling;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        counseling = new Counseling[N+1];
        dp = new int[N+1];
        for(int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            counseling[n] = new Counseling(time, cost);
        }

        for(int n = 1; n <= N; n++) {
            int time = counseling[n].time, cost = counseling[n].cost;
            // 퇴사하는 날을 넘어서지 않는 조건
            if(time + n -1 <= N){
                dp[time+n-1] = Math.max(dp[time+n-1], dp[n-1] + cost);
            }
            dp[n] = Math.max(dp[n], dp[n-1]);
        }
        System.out.println(dp[N]);



    }
    static class Counseling{
        int time, cost;

        public Counseling(int time, int cost) {
            this.time = time;
            this.cost = cost;
        }
    }
}
