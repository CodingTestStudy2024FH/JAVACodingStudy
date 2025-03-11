import java.io.*;

public class Main {
    //재귀 문제
    static char[][] star;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        star = new char[N][N];
        //공백으로 초기화
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                star[i][j] = ' ';
            }
        }

        fillStar(0,0,N);    //x, y, size

        /*for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(star[i][j]);
            }
            System.out.println();
        }*/ //-> 시간초과

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                sb.append(star[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static void fillStar(int x, int y, int size){
        //종료 조건
        if(size == 1){
            star[y][x] = '*';
            return;
        }

        //아니라면, 사이즈에 따라서 규칙 적용
        //3을 규칙으로 가짐
        //*** 
        //* *
        //***
        int newSize = size / 3;

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(i==1 && j==1) continue; //중앙 공백
                fillStar(x + (i*newSize), y + (j*newSize), newSize);
            }
        }
    }
}
