package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    a와 b로만 이뤄진 문자열
    a를 모두 연속으로 만들기 위해서 필요한 교환의 회수를 최소로 하는 프로그램
    처음과 끝은 인접.

    -> b를 이동시켜야 함.
 */
public class BOJ1522_문자열교환 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int min = Integer.MAX_VALUE;

        int a = 0;
        for(int i=0;i<s.length();i++){ //a 숫자 세기
            if(s.charAt(i)=='a'){
                a++;
            }
        }
        for(int i=0;i<s.length();i++){
            int b=0;
            for(int j=i;j<i+a;j++){ //슬라이딩 윈도우 0~a의 개수만큼이동
                int index = j%s.length();
                if(s.charAt(index)=='b'){ //b면 개수 증가
                    b++;
                }
            }
            min=Math.min(min,b);
        }
        System.out.println(min);

    }
}
