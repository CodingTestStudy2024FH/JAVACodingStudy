package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2231_분해합 {
    /*
    어떤 자연수 N, 분해합 = N과 N을 이루는 각 자리수의 합
    어떤 자연수 M의 분해합이 N인 경우
    M을 N의 생성자라고 한다.

    가장 작은 생성자는?
    ..?
    걍 다 돌아?
     */
    public static void main(String[] args) throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int sum=0;
        int num=0;
        for(int i=0;i<N;i++){
            num=i; //초기화
            sum=num;

            while(num!=0){ //각 자리수 더해주기
                sum+= num%10;
                num/=10;
            }
            if(sum==N) {
                System.out.println(i);
                break;
            }
        }

    }
}
