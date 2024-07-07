import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9251_LCS {
    /*
       모든 부분 수열이 되는 수열 중 가장 긴 것
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        //1. 쭉 비교
        //2. 같으면 이전 값에서 +1
        //3. 다르면 내 이전값이나, 내 위의값 중 큰값

        int[][] dp = new int[s1.length()+1][s2.length()+1];

        //dp시작
        for(int i=1;i<=s1.length();i++){
            for(int j=1;j<=s2.length();j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){ //같으면 -1-1값에서 +1
                    //이전 위치에서부터 가장 큰 값
                    dp[i][j]=dp[i-1][j-1]+1;
                }else {
                    //같지 않다면 본인까지의 최대 공통 부분수열 큰거 고름
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        //가장 마지막 값이 가장 큰 값
        System.out.println(dp[s1.length()][s2.length()]);
    }
}
