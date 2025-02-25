package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class BOJ15486_퇴사2 {
    /*
        시작하는 날까지 포함해서 시간 걸림.

        해당 날까지의 최대 누적합.
        그럼 어디에 최대값을 포함해야하는가?

        일단 배열은 N+1까지 있어야한다. 그날이 마지막이니까.
        그렇다면 이전날까지 완료한 금액이 해당 자리에 들어가는게 맞겠다.

        DP무조건 DP네~
     */
    static int N;
    static int[] T,P,dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        //0일차부터 시작.
        T = new int[N+1];
        P = new int[N+1];
        dp = new int[N + 2];

        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        //input end

        //logic start
        for(int i=1;i<=N;i++){
            //1. 일단 전날이랑 비교해서 더 큰 DP값으로 교체. (알쏭달쏭)
            dp[i] = Math.max(dp[i], dp[i - 1]);

            //2. 완료 날짜가 N+1보다 크면 continue
            // 완료되는 날 입금되게 해야함.
            if(i+T[i]>N+1){
                continue;
            }
            //3. 완료된 날에 이미 저장된 값과 전날 내값+일값 비교해서 큰거로 넣기
            dp[i + T[i]-1] = Math.max((dp[i - 1] + P[i]), dp[i + T[i] - 1]);
        }

//        for(int i=1;i<N+1;i++){
//            System.out.println(dp[i]);
//        }
        System.out.println(dp[N]);
    }
}
