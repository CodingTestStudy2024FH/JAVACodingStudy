import java.io.IOException;
import java.util.Scanner;

public class BOJ2448_별찍기11 {

    static char[][] map;
    // 별만들기

    /**
     * 큰 삼각형을 기준으로 총 3개의 작은 삼각형 -> 위에 하나 아래 두개
     * 작은 삼각형은 그 전 상위 큰 삼각형과 같게 생김
     * 높이는 n, 가로는 2n-1 이다. -> 배열로 표현하기
     */

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        map = new char[N][2 * N - 1];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < 2 * N -1; j++){
                map[i][j] = ' '; // 공백
            }
        }

        makeStar(0, N-1, N);

        // 정답 출력
        for(int i = 0; i < N; i++){
            for(int j = 0; j < 2 * N -1; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

    }



    static void makeStar(int y, int x, int n) {
        // 꼬리잡기 가장 최하단 가장 작은 삼각형
        if (n == 3) {
            map[y][x] = '*';
            map[y + 1][x - 1] = map[y + 1][x + 1] = '*';
            map[y + 2][x - 2] = map[y + 2][x - 1] = map[y + 2][x + 1] = map[y + 2][x + 2] = '*';
            return;
        }

        // 작은 삼각형 그리기
        makeStar(y, x, n / 2); // 가운데 윗줄 삼각형
        makeStar(y + n / 2, x - n / 2, n / 2); // 왼쪽 삼각형
        makeStar(y + n / 2, x + n / 2, n / 2); // 오른쪽 삼각형
    }
}
