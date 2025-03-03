import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1759_암호만들기 {
    /*
        최소 한개의 모음(a,e,i,o,u)과 두개의 자음.
        알파벳이 암호에서 증가하는 순서로 있을것이다.
        abc ( 가능 ) bac (불가능)

        암호로 사용했을 법한 문자의 종류는 C가지.
        암호 : 서로다른 L개의 소문자.
        가능성 있는 암호를 모두 출력하시오. _ 사전순.
     */
    static int L, C;
    static char[] chars;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        chars = new char[C];
        for(int i=0;i<C;i++){
            chars[i] = st.nextToken().charAt(0);
        }
        //input end

        //logic start
        Arrays.sort(chars); //일단 사전순 정렬

        //DFS 돌리기.
        dfs(0,0,0,0,"");



    }
    public static void dfs(int idx, int vCnt,int cCnt,int num,String password){
        if(num==L&&vCnt>=1&&cCnt>=2){ //암호길이 만족, 모음 1개, 자음 2개 이상시 출력
            System.out.println(password);
            return;
        }
        if(idx>=C) return;//C개보다 많으면 idx오버

        char now = chars[idx];

        //1. now를 포함하는 경우
        //모음이면,
        if (now == 'a' || now == 'e' || now == 'i' || now == 'o' || now == 'u') {
            dfs(idx + 1, vCnt + 1, cCnt, num + 1, password+now);
        }else{//자음이면
            dfs(idx + 1, vCnt, cCnt + 1, num + 1, password+now);
        }
        //2. now를 포함하지 않는 경우
        dfs(idx + 1, vCnt, cCnt, num, password);


    }

}
