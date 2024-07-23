package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1106_호텔 {
    static int destPeople, hotelCount, cnt;
    static final int MAX_SIZE = 101;
    // Integer.MAX_VALUE로 선언하면 dp의 값 비교할때 cost를 더하는 과정이 있는데
    // 이때 오버플로우가 나기 때문에 적당히 큰 값으로 넣어줘야함
    static final int INITIAL_MAX = 100_000_000;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        destPeople = Integer.parseInt(st.nextToken());
        hotelCount = Integer.parseInt(st.nextToken());

        dp = new int[destPeople + MAX_SIZE];

        Arrays.fill(dp, INITIAL_MAX);
        dp[0] = 0;

        for(int i = 0; i < hotelCount; i++){
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            // 이전 people의 값 + 현재 cost와 현재 dp[i]를 비교해서 최소값 기억
            for(int j = people; j < destPeople + MAX_SIZE; j++) dp[j] = Math.min(dp[j], dp[j-people]+cost);
        }

        int answer = Integer.MAX_VALUE;
        // 적어도 모든 destPeople 수만큼 들려야 되기 때문에 dp의 모든 수를 봐야함
        // destPeople 이전의 값은 목표한만큼의 수를 만족못했기 때문에 순회할 필요 없음
        for(int i = destPeople; i < destPeople + MAX_SIZE; i++) answer = Math.min(answer, dp[i]);
        System.out.println(answer);

    }
}
