package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ9663_NQueen {
    /*
        N x N 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는
        방법의 수

       1. 동일한 행에 위차하는가?
       2. 대각선상에 위치하는가?

       index : 열
       값 : 행
     */
    static int ans,N;
    static int[] list;
    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new int[N];
        dfs(0);

        System.out.println(ans);
    }
    public static void dfs(int count){
        if(count ==N){ //N까지 다 채웠다면 count 증가
            ans++;
            return;
        }
        for(int i=0;i<N;i++){
            list[count]=i;
            if(can(count)){ //해당 조건을 만족한다면
                dfs(count+1);//재귀 한번 더
            }
        }
    }
    public static boolean can(int col){ //col : 인덱스 = 행
        //퀸의 위치 될 수 없는 조건
        //1. 위 아래로 있을때 = 수직
        //2. 현재 퀸을 두려는 열에서 이미 퀸을 둔 자리의 열을 뺀 값이 서로 행 뺀 값과 같을 때 == 대각선 (공식)
        for(int i=0;i<col;i++){
            if(list[col]==list[i]){//같은 행 안됨 ( 값이 행 )
                    return false;
            }
            // 대각선 안됨 (행, 열의 절댓값이 같으면 대각선)
            if(Math.abs(col-i)==Math.abs(list[col]-list[i])){
                return false;
            }
        }
        return true;
    }
    
    
}
