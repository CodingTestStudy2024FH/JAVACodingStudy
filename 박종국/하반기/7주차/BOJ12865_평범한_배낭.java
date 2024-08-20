import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static int N, maxWeight, max;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        maxWeight = Integer.parseInt(st.nextToken());
        dp = new int[maxWeight+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            for(int c = maxWeight; c >= weight; c--) dp[c] = Math.max(dp[c], cost + dp[c-weight]);
        }
        System.out.println(dp[maxWeight]);

    }
}
