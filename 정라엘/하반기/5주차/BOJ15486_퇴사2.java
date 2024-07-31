import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] T = new int[N + 1];
        int[] P = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            T[i] = scanner.nextInt();
            P[i] = scanner.nextInt();
        }

        int[] dp = new int[N + 2]; // N+1까지 사용하기 위해 N+2 크기로 선언

        for (int i = N; i > 0; i--) {
            if (i + T[i] <= N + 1) {
                dp[i] = Math.max(dp[i + 1], P[i] + dp[i + T[i]]);
            } else {
                dp[i] = dp[i + 1];
            }
        }

        System.out.println(dp[1]);

        scanner.close();
    }
}
