import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt(); // 물건의 수
        int K = sc.nextInt(); // 배낭의 최대 무게
        
        int[] dp = new int[K + 1]; // DP 테이블 초기화

        for (int i = 0; i < N; i++) {
            int weight = sc.nextInt(); // 물건의 무게
            int value = sc.nextInt();  // 물건의 가치
            
            // 뒤부터 돌아서, 동일 물건이 여러 번 더해지는 것을 방지
            for (int w = K; w >= weight; w--) {
                dp[w] = Math.max(dp[w], dp[w - weight] + value);
            }
        }
        
        // 배낭의 최대 무게 내에서 얻을 수 있는 최대 가치 출력
        System.out.println(dp[K]);
    }
}
