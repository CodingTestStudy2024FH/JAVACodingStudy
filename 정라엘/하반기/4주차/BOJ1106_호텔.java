// DP 아이디어는 인터넷에서 참고...

import java.io.*;
import java.util.*;

public class Main {
  static int C;
  static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		// 각 도시에서 비용으로 얻을 수 있는 고객수는 100명 이하
		int dp[] = new int[C+100]; 
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int customer = Integer.parseInt(st.nextToken());
			
			for(int j=customer; j<C+100; j++) {
				// cost + dp[j-customer] 로 j명의 고객 늘리기
        // 갱신 안 된 값을 만나면, 갱신
				if (dp[j-customer] != Integer.MAX_VALUE)
					dp[j] = Math.min(dp[j], cost+dp[j-customer]);
			}
		}
		
		int answer = Integer.MAX_VALUE;
    // 최소 c명을 확보
		for(int i=C; i<C+100; i++) {
			answer = Math.min(answer, dp[i]);
		}
		System.out.println(answer);
		
	}

}
