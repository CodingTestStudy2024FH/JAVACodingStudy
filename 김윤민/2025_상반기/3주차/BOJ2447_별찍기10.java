package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2447_별찍기10 {
    /*
        N은 3의 거듭제곱
        가운데 공백
        i/N == 1인 경우 모두 공백처리
     */
    static int N;
    static char[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        dfs(N, 0, 0);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++){
            sb = new StringBuilder();
            for(int j=0;j<N;j++){
                sb.append(map[i][j]);
            }
            System.out.println(sb.toString());
        }

    }
    public static void dfs(int now,int x,int y){
        if(now<3) return;
        int pivot = now/3;
        for(int i=x;i<x+now;i=i+pivot){
            for(int j=y;j<y+now;j=j+pivot){
                if(i>=pivot+x&&i<x+2*pivot&&j>=y+pivot&&j<y+2*pivot) {
                    for(int nx=pivot+x;nx<x+2*pivot;nx++){
                        for(int ny=pivot+y;ny<y+2*pivot;ny++){
                            map[nx][ny]=' ';
                        }
                    }
                    continue;
                }
                if(now/3>=3){
                    dfs(now / 3, i,j);
                }
                map[i][j]='*';
            }
        }
    }


}
