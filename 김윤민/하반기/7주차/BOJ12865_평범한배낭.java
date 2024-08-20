package BOJ;
import java.io.*;
import java.util.StringTokenizer;

public class BOJ12865_평범한배낭 {
    /*
        냅색 알고리즘.
     */
    static int N,K;
    public static void main(String[] args) throws Exception{
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] dp = new int[K+1];
        //item[i][0] : w, item[i][1] : value
        int[][] item = new int[N+1][2];
        for(int i=1;i<=N;i++) {
            st= new StringTokenizer(br.readLine());
            item[i][0] = Integer.parseInt(st.nextToken());
            item[i][1]= Integer.parseInt(st.nextToken());
        }
        //input end
        for(int i=1;i<=N;i++) {
            for(int w=K;w>=item[i][0];w--) {
                //현재 내 누적가치가 최대인지, 현재 아이템을 제외한 이전가치에서 내 가치를 더하는게 최적인지.
                dp[w]=Math.max(dp[w], dp[w-item[i][0]]+item[i][1]);
            }

        }
        System.out.println(dp[K]);
    }
}
