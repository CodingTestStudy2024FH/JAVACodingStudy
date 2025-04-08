import java.util.*;

public class BOJ2116_주사위쌓기 {

    static int[][] dice;
    static int N;
    // 마주보는 면 인덱스를 저장
    static int[] opposite = {5, 3, 4, 1, 2, 0};

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dice = new int[N][6];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 6; j++) {
                dice[i][j] = sc.nextInt();
            }
        }

        int maxSum = 0;

        // 첫 번째 주사위의 아랫면을 0~5 중 하나로 설정
        for (int i = 0; i < 6; i++) {
            maxSum = Math.max(maxSum, getMaxSum(i));
        }

        System.out.println(maxSum);
    }

    // 시작 아랫면 인덱스를 받아 전체 쌓기 시뮬레이션
    public static int getMaxSum(int sIdx) {
        int sum = 0;
        int bottom = dice[0][sIdx];
        int top = dice[0][opposite[sIdx]];

        sum += getMaxSide(0, bottom, top);

        for (int i = 1; i < N; i++) {
            // 현재 주사위에서 바닥면(top)을 찾는다
            int nIdx = 0;
            for (int j = 0; j < 6; j++) {
                if (dice[i][j] == top) {
                    nIdx = j;
                    break;
                }
            }

            bottom = dice[i][nIdx];
            top = dice[i][opposite[nIdx]];
            sum += getMaxSide(i, bottom, top);
        }

        return sum;
    }

    // 아랫면과 윗면을 제외한 4면 중 가장 큰 수 반환
    public static int getMaxSide(int idx, int bottom, int top) {
        int max = 0;
        for (int i = 0; i < 6; i++) {
            if (dice[idx][i] != bottom && dice[idx][i] != top) {
                max = Math.max(max, dice[idx][i]);
            }
        }
        return max;
    }
}
