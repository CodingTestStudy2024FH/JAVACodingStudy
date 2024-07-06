package week_1st;
// BOJ9663_N-Queen
import java.io.IOException;
import java.util.Scanner;

public class BOJ9663_NQueen {
    static int N;
    static int[] map;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N];
        doChess(0);
        System.out.println(answer);
    }

    static void doChess(int queen) { // queen행임 -> 위에서부터 채워나간다 생각하면 편안
        if (queen == N) {
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            // 채우기
            map[queen] = i;
            if (isAvail(queen)) // 가능하면 이제 다음거 놓으러 가기
                doChess(queen + 1); // 다음거 검사하러 가기
        }
    }

    static boolean isAvail(int col) {

        for (int j = 0; j < col; j++) {
            // 같은 행에 놓은 게 있는 지 체크 -> 같으면 ㄴㄴ 백해라
            if (map[col] == map[j]) return false;
            // 대각선 첵흐 ~
            // 대각선에 놓인 것인지 체크하려면
            else if(Math.abs(col-j) == Math.abs(map[col] - map[j])) return false;
        }
        return true;
    }
}
