package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2458_키순서 {
    /*
        자신의 키가 몇번째인지 알 수 있는 학생이 몇명인지?
        비슷한 유형 풀었던 문젠디

        플로이드워셜
        = 거쳐가는 정점을 기준으로 최단거리를 구함.
     */
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int max = n * (n * (n - 1) / 2); //최대값

        int[][] student = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                student[i][j] = max;
            }
        }

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            student[n1 - 1][n2 - 1] = 1; //누가 더 큰지 기록
        }

        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(student[i][j] > student[i][k] + student[k][j]) {
                        student[i][j] = student[i][k] + student[k][j];
                    }
                }
            }
        }

        int ans = 0;
        for(int i = 0; i < n; i++) {
            int count = 0;
            for(int j = 0; j < n; j++) {
                if(student[i][j] < max || student[j][i] < max) count++;
            }
            if(count == n - 1) ans++;
        }

        System.out.println(ans);
    }

}
