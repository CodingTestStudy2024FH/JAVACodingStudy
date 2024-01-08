import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2240_자두나무 {

    static int T, W;
    // T초동안 떨어지고, 최대 W번 이동함
    static int[] plumInfo;
    // 자두가 떨어지는 것에 대한 정보

    // 1000초 -> 시간 단축 필요함
    static int[][][] dp;
    // T 번째 자두
    // 위치
    // 이동횟수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 자두 떨어질때 까지 기다려서 자두 먹기
        // 자두가 허공에 있을때 잡아야

        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        dp = new int[T + 1][3][W + 1];

        // 제일 처음 값
        int n = Integer.parseInt(br.readLine());

        // 1에 위치한다면
        if (n == 1) {
            dp[1][1][0] = 1; // 1초일때 1에 있으면 1개를 얻음
            dp[1][2][1] = 0; // 1초일때 2에 있음(1->2) 이동
        }

        else if (n == 2) {
            dp[1][1][0] = 0; // 1초일때 1에 있으면 아무것도 얻지 못함
            dp[1][2][1] = 1; // 1초일때 2에있음(1->2) 이동해서 1개 얻음
        }

        for (int t = 2; t <= T; t++) {
            // 다음 것들 하나씩 잡아가기
            n = Integer.parseInt(br.readLine());

            if (n == 1) {
                // 움직임 없음 경우
                // t초일때 1에 있음 -> t-1초일때 1에 있었다면 +1
                dp[t][1][0] = dp[t - 1][1][0] + 1;
                // t초일때 2에 있음 -> t-1초일때 2에 있었다면 0
                dp[t][2][0] = dp[t - 1][2][0];

                // 움직임 있는 경우
                for (int w = 1; w <= W; w++) {
                    // t초일때 1에 있음 (W번 움직인 상태) -> 이전에서 움직인 경우 혹은 안움직인 경우 + 1
                    dp[t][1][w] = Math.max(dp[t - 1][1][w], dp[t - 1][2][w - 1]) + 1;
                    // t초일때 2에 있음 (W번 움직인 상태) -> 이전에서 움직인 경우 혹은 안움직인 경우 뭐가 됐든 현재 자두 1이므로 못얻음
                    dp[t][2][w] = Math.max(dp[t - 1][1][w - 1], dp[t - 1][2][w]);
                }
            }

            // 위와 같은 맥락으로 현재를 기준으로 자두나무가 2일때를 생각한다.
            else if (n == 2) {

                // 움직임이 없는 경우
                dp[t][1][0] = dp[t - 1][1][0];
                dp[t][2][0] = dp[t - 1][2][0] + 1;

                // 움직임이 있는 경우
                for (int w = 1; w <= W; w++) {
                    dp[t][1][w] = Math.max(dp[t - 1][1][w], dp[t - 1][2][w - 1]);
                    dp[t][2][w] = Math.max(dp[t - 1][1][w - 1], dp[t - 1][2][w]) + 1;
                }
            }
        }

        int max = 0;
        for (int w = 0; w <= W; w++) {
            max = Math.max(max, Math.max(dp[T][1][w], dp[T][2][w]));
        }

        System.out.println(max);
    }



//    static int getPlum(int t, int loc, int least){
//
//        if(t == T ){
//            return 0;
//        }
//
//        int result = dp[t][loc][least];
//        if(result != -1)
//            return result;
//
//
//        if(plumInfo[t] == loc){ // 현재위치에서 자두를 받는 경우
//
//            // 자리를 옮길수 있는 경우
//            if(least > 0) {
//                int nextLoc = (loc == 1) ? 2 : 1;
//                // 자리를 안옮긴 경우, 자리를 옮긴 경우 -> 다음 경우가 맞는지 안맞는지 체크해서 더 큰걸 가져옴
//                result =
//                        // 다음초에서, 위치를 바꾸지 않고 자두 얻기
//                        Math.max(getPlum(t + 1, loc, least) + 1,
//                                getPlum(t + 1, nextLoc, least - 1));
//
//                // 자리를 옮길수 없는 경우
//            }else{
//                result = getPlum(t+1, loc, least) + 1;
//            }
//
//        }else{ // 현재위치가 아니라서 자두를 얻지 못하는 경우
//
//            // 자리를 옮길수 있는 경우
//            if(least > 0) {
//                int nextLoc = (loc == 1) ? 2 : 1;
//                // 자리를 안옮긴 경우, 자리를 옮긴 경우
//                // -> 다음 경우가 맞는지 안맞는지 체크해서 더 큰걸 가져옴
//                result =
//                        Math.max(getPlum(t + 1, loc, least),
//                                getPlum(t + 1, nextLoc, least - 1) + 1);
//
//                // 자리를 옮길수 없는 경우
//            }else{
//                result = getPlum(t+1, loc, least);
//            }
//        }
//
//        dp[loc][least][t] = result;
//        return result;
//
//    }
}
