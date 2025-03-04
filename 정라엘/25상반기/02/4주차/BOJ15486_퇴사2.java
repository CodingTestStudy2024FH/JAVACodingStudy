import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, max_profit;
    static int[] T, P, dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = new int[N+1];
        P = new int[N+1];
        dp = new int[N+2]; //N일까지 일하므로

        dp[0] = 0; //초기값은 0 
        max_profit = 0;
        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());

            dp[i] = Math.max(max_profit, dp[i]);    //상담을 안하면 이전의 최댓값 적용
            if(i+T[i]<=N+1) //수익은 N+1에 적용될 수 O
                dp[i+T[i]] = Math.max(dp[i]+P[i], dp[i+T[i]]);  //상담을 결정 했다면 해당 시간에 금액 더해줌 (그리고 기존 값이랑 비교해 최댓값 넣어주기)
            max_profit = Math.max(max_profit, dp[i]); // 최댓값 업데이트
        }

        System.out.println(Math.max(max_profit, dp[N+1])); // 최종 최댓값 출력
    }
}

// DP에도 2가지 종류가 있군아...
// 1. 과거의 값이 현재의 값에 영향 O
// ex) 최대 이익 스케쥴, 배낭 문제, 최대 점수 등
// => 이 경우 max_profit으로 언제나 최댓값을 보장해야 함
// --------------------------------------
// 2. 과거의 값이 현재의 값에 영향 X
// ex) 최소 연산 횟수, 계단 오르기, 피보나치 수열
// => 이 경우 최댓값 보장필요 X (독립 시행에 해당하므로)
