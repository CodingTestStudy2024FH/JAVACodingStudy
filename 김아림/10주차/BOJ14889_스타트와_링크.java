import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14889_스타트와_링크 {

    static int N;
    static int[][] map;
    static int[] teamArr;
    static int[] teamBrr;
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        teamArr = new int[N/2];
        teamBrr = new int[N/2];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2/N 으로 나눠야함
        // 합을 구해야함
        // N C 2/N을 하고 나머지를 구해야함
        com(0, 0);

        System.out.println(MIN);

    }

    static void com(int cnt, int idx){

        if(cnt == N / 2){

            int indexA = 0;
            int indexB = 0;
            for(int i = 0; i < N; i++){
                if(indexA < N/ 2 && teamArr[indexA] == i) {
                    indexA++;
                }else {
                    teamBrr[indexB++] = i;
                }
            }

            getDis();
            return;
        }
        for(int i = idx; i < N; i++){
            teamArr[cnt] = i;
            com(cnt+1, i + 1);
        }
    }

    static void getDis(){

        int teamA = 0;
        int teamB = 0;

        for(int i = 0; i < N/2 - 1; i++){
            for(int j = i+1; j < N/2; j++){
                teamA += map[teamArr[i]][teamArr[j]] + map[teamArr[j]][teamArr[i]];
                teamB += map[teamBrr[i]][teamBrr[j]] + map[teamBrr[j]][teamBrr[i]];
            }
        }

        int answer = Math.abs(teamA - teamB);
        MIN = Math.min(MIN, answer);

    }
}
