import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA1868_파핑파핑_지뢰찾기 {

    static int N;
    static int[] dy = {1, 1, -1, -1, 0, 0, 1, -1};
    static int[] dx = {-1, 1, -1, 1, -1, 1, 0, 0};
    static char[][] map; // 기존 입력 배열
    static int[][] numberMap; // 숫자 계산 
    static int count; // c로 만들어야하는 칸의 개수 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");

            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            numberMap = new int[N][N];

            count = N * N; // 총 칸의 개수 
            int answer = 0; // 정답 

            // 입력받기
            for (int r = 0; r < N; r++) {
                String str = br.readLine();
                for (int c = 0; c < N; c++) {
                    map[r][c] = str.charAt(c);
                    if (map[r][c] == '*') count--; // 칸의 개수에서 지뢰의 개수는 빼줘야함 -> c로 만들어야하는 것의 개수이므로 
                }
            }
            // 숫자 정보 구하기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    numberMap[r][c] = getNumber(r, c);
                }
            }

            // 작은애들 순서로 터트려야한다.. 아니가 -> 모서리에 위치해서 양옆이 날아가는애들 보다 가운데 위치하는 0을 터트리는게 더 먼저여야함
            // 내부의 애들을 먼저하고 모서리는 나중에 하기
            int min = 0;

            while (count > 0) {
                for (int i = 1; i < N - 1; i++) {
                    for (int j = 1; j < N - 1; j++) {
                        if (map[i][j] == '.' && numberMap[i][j] == min) {
                            //System.out.println("inside answer : " + i + ", " + j + " -> "+ min);
                            answer++;
                            // 만약에 터트린 애가 0이라면 주위 애들은 자동으로 터짐
                            map[i][j] = 'c';
                            if (numberMap[i][j] == 0) {
                                spreadZero(i,j);

                            }
                        }
                    }
                }

                // 모서리 관찰 (왼쪽)
                for (int i = 0; i < N; i++) {
                    if (map[i][0] == '.' && numberMap[i][0] == min) {
                        //System.out.println("answer : " + i + ", " + 0 + " -> "+ min);
                        answer++;
                        // 만약에 터트린 애가 0이라면 주위 애들은 자동으로 터짐
                        map[i][0] = 'c';
                        if (numberMap[i][0] == 0) {
                            spreadZero(i, 0);

                        }
                    }
                }
                // 모서리 관찰 (위쪽)
                for (int i = 0; i < N; i++) {
                    if (map[0][i] == '.' && numberMap[0][i] == min) {
                        //System.out.println("answer : " + 0 + ", " + (i) + " -> "+ min);

                        answer++;
                        // 만약에 터트린 애가 0이라면 주위 애들은 자동으로 터짐
                        map[0][i] = 'c';
                        if (numberMap[0][i] == 0) {
                            spreadZero(0, i);

                        }
                    }
                }

                // 모서리 관찰 (오른쪽)
                for (int i = 0; i < N; i++) {
                    if (map[i][N - 1] == '.' && numberMap[i][N - 1] == min) {
                        //System.out.println("answer : " + i + ", " + (N - 1) + " -> "+ min);

                        answer++;
                        // 만약에 터트린 애가 0이라면 주위 애들은 자동으로 터짐
                        map[i][N - 1] = 'c';
                        if (numberMap[i][N - 1] == 0) {
                            spreadZero(i, N-1);

                        }
                    }
                }

                // 모서리관찰 아래)
                for (int i = 0; i < N; i++) {
                    if (map[N - 1][i] == '.' && numberMap[N - 1][i] == min) {
                        //System.out.println("answer : " + (N - 1) + ", " + i + " -> "+ min);

                        answer++;
                        // 만약에 터트린 애가 0이라면 주위 애들은 자동으로 터짐
                        map[N - 1][i] = 'c';
                        if (numberMap[N - 1][i] == 0) {
                            spreadZero(N-1, i);
                        }
                    }
                }

                min++;
                if (min == 9) {
                    break;
                }
            }

//            // 출력하기
//            for (int r = 0; r < N; r++) {
//                System.out.print(Arrays.toString(numberMap[r]));
//                System.out.println();
//            }
//
//            // 출력하기
//            for (int r = 0; r < N; r++) {
//                System.out.print(Arrays.toString(map[r]));
//                System.out.println();
//            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    // (y,x) 의 숫자 구하기
    static int getNumber(int y, int x) {
        int answer = 0;

        for (int i = 0; i < 8; i++) {
            // 방향잡기
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
            if (map[ny][nx] == '*') answer++;
        }
        return answer;
    }

    // 이걸재귀로 구현해야했음 -> 뒤늦게 생각.. 
    // 0이면 주위 8칸이 자동으로 퍼짐 -> 그 8칸 중에 0이 있다면 ? 또 자동으로 퍼져야함 
    static void spreadZero(int y, int x){

        for (int i = 0; i < 8; i++) {
            // 방향잡기
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
            if (map[ny][nx] == '*' || map[ny][nx] == 'c') continue;
            map[ny][nx] = 'c';
            if(numberMap[ny][nx] == 0) { // 재귀 해주는 부분 
                spreadZero(ny,nx);
            }
        }
    }
}
