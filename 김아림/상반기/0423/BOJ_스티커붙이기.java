import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_스티커붙이기 {
    // 스티커의 좌표를 가지고 똑같이 잘라서 채워보기.. ?

    static int N, M;
    static int K;
    static List<int[][]> stickers ;
    static int[][] laptop;
    static int[] count;
    static boolean[] attached;
    static int answer;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        laptop = new int[N][M]; // 스티커를 붙인 부분은 1 붙이지 않았다면 0
        stickers = new ArrayList<>();
        attached = new boolean[K]; // 붙이기 성공하면 true
        count = new int[K]; // 각 스티커마다 칸의 개수

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[R][C];

            for(int r = 0; r < R; r++){
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < C; c++){
                    int input = Integer.parseInt(st.nextToken());;
                    sticker[r][c] = input;
                    if(input == 1) count[i]++;
                }
            }
            stickers.add(sticker);
        }


        // sticker를 순서대로 돌면서 붙일 수 있는 위치 찾기
        PriorityQueue<int[]> q;

        // 가장 위, 가장 왼쪽이어야 함
        for(int d = 0; d < stickers.size(); d++){
            boolean flag = false;
            int[][] sticker = stickers.get(d);

            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(!flag){
                        if(isPossible(sticker, i, j)){
                            attached[d] = true;
                            attach(sticker, i,j);
                            flag = true;
                            break;
                        }
                    }
                }
            }

            int count = 0;
            while(!flag){
                if(count == 4) {
                    break;
                }
                count++;
                sticker = rotate(sticker);

//                // 출력
//                for(int i = 0; i < sticker.length; i++){
//                    for(int j = 0; j < sticker[0].length; j++){
//                        System.out.print(sticker[i][j] + " ");
//                    }
//                    System.out.println();
//                }

                for(int i = 0; i < N; i++){
                    for(int j = 0; j < M; j++){
                        if(!flag && isPossible(sticker, i, j)){
                            attached[d] = true;
                            attach(sticker,i,j);
                            flag = true;
                            break;
                        }
                    }
                }
            }
        }

        for(int i = 0; i < K; i++){
            if(attached[i]) {
                answer += count[i];
            }
        }

        System.out.println(answer);
    }

    // y랑 x가 시작점임
    static boolean isPossible(int[][] sticker, int y, int x){ // y,x가 0,0이라고 시작해야함

        // 스티커 크기
        int R = sticker.length;
        int C = sticker[0].length;

        if(R + y > N || C + x > M) {
            return false; // 크기를 넘어서면 이미 안됨
        }

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                // 1인데 1이라면 -> 스티커를 붙일 수 없음
                // 0 인데 1인경우 ㄱㅊ
                // 1인데 0이라면 좋아
                // 0인데 0이라면 노상관
                if(sticker[i][j] == 1 && laptop[y + i][x + j] == 1){
                    return false;
                }
            }
        }

        return true;
    }

    // 스터키 붙이기
    static void attach(int[][] sticker, int y, int x){

        int R = sticker.length;
        int C = sticker[0].length;

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(sticker[i][j] == 1){
                    laptop[y+i][x+j] = 1;
                }
            }
        }
    }

    static int[][] rotate(int[][] sticker){
        // 시계방향 90도 회전하기
        int R = sticker.length;
        int C = sticker[0].length;

        int[][] copyed = new int[C][R];

        for(int i =0; i< R; i++){
            for(int j =0; j <C; j++){
                copyed[j][R-i-1] = sticker[i][j];
            }
        }

        return copyed;
    }
}
