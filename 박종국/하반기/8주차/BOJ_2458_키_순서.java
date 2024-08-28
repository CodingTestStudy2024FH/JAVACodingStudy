import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] floyd;
    static final int INF = 100_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        floyd = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                floyd[i][j] = INF;
            }
        }

        for(int m = 1; m <= M; m++){
            st = new StringTokenizer(br.readLine());
            int small = Integer.parseInt(st.nextToken());
            int big = Integer.parseInt(st.nextToken());
            floyd[small][big] = 1;
        }


        // floyd start
        for(int mid = 1; mid <= N; mid++){
            for(int start = 1; start <= N; start++){
                for(int end = 1; end <= N; end++){
                    floyd[start][end] = Math.min(floyd[start][mid] + floyd[mid][end], floyd[start][end]);
                }
            }
        }
//        for(int[] f: floyd) System.out.println(Arrays.toString(f));

        System.out.println(getResult());
    }
    static int getResult(){
        // floyd warshall로 연결되어 있는 부분만 찾고 끝이 아님
        // 정방향과 역방향을 살피면서 비교가 가능한지 검증을 해야됌
        int result = 0;
        int[] floydPath = new int[N];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(i == j) continue;
                if(floyd[i][j] != INF || floyd[j][i] != INF) floydPath[i-1]++;
            }
        }
        for(int path : floydPath) if(path == N-1) result++;
        return result;
    }
}
