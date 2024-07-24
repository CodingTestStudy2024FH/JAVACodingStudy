package week_4th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1106 {
    static int C, N;
    static int[][] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        // 먼가 dp같은 느낌이 드네영 .... ㅇㅅㅇ ...

        /**
         * 배낭 문제 +  dp문제라고 함
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 고객 수를 C명 늘려야 한다
        // 한번의 비용으로 얻을 수 있는 고객의 수는 100 이하

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][2];

        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 홍보 비용
            arr[i][1] = Integer.parseInt(st.nextToken()); // 얻는 고객의 수
        }

        dp = new int[C + 101]; // 한번에 얻는 고객의 수는 100 이하임 -> C명 이상 + 100 까지 담아야함
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            int cost = arr[i][0]; // 홍보 비용
            int value = arr[i][1]; // 고객의 수

            for (int j = value; j < C + 101; j++) {
                // 한번도 탐색하지 않은 경우를 기준으로 해야함
                if (dp[j - value] != Integer.MAX_VALUE) {
                    //
                    dp[j] = Math.min(dp[j - value] + cost, dp[j]);
                }
            }
        }


        // C명 이상 중에 최솟값 탐색
        int ans = Integer.MAX_VALUE;
        for (int i = C; i < C + 101; i++) {
            ans = Math.min(ans, dp[i]);
        }

        System.out.println(ans);

    }
}
