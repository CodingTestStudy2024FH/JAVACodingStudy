import java.util.Arrays;

public class PRO_숫자변환하기 {
    /*
    1. n을 더하기
    2. 2를 곱하기
    3. 3을 곱하기

    -> DP로 풀어야함.
    0~X -> 0
    만약 지금 DP값이 연산값보다 더 작으면 그대로
    연산값이 더 작으면 연산값으로.
    */
    static int[] dp;
    public static int solution(int x, int y, int n) {
        dp = new int[y+1];
        Arrays.fill(dp,1000); //0이랑 구분되기 위한 1000초기화
        for(int i=0;i<=x;i++){ //x까지 0으로 초기화
            dp[i]=0;
        }
        int now= x;
        while(now!=y){
            //case1 : n을 더하기
            if(now!=x&&dp[now]==1000) { //불필요한 연산 skip
                now++;
                continue;
            }
            if(can(now+n,y)){
                dp[now+n]= Math.min(dp[now+n],dp[now]+1);
            }
            //case2 : 2를 곱하기
            if(can(now*2,y)){
                dp[now*2]= Math.min(dp[now*2],dp[now]+1);
            }
            //case3 : 3을 곱하기
            if(can(now*3,y)){
                dp[now*3]= Math.min(dp[now*3],dp[now]+1);
            }
            now++;
        }
        if(dp[y]==1000){
            return -1;
        }else{
            return dp[y];
        }

    }
    //위치 유효?
    public static boolean can(int num,int y){
        if(num<0||num>y) return false;
        return true;
    }
    public static void main(String[] args) {
        System.out.println(solution(10,40,5));
    }
}
