package week_2nd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

// BOJ2231_분해합
public class BOJ2231_분해합 {
    public static void main(String[] args) throws IOException {
        // 245의 분해합은 245 + 2 + 4 + 5 = 256
        // 245는 256의 생성자
        // N이 주어질때 N의 가장 작은 생성자.... 그니까 분해합을 구해서 N이되는 숫자를 구하라는 것임
        // N이 100만 -> 반복문은 한번만 ....* 7 -> 700만 이니까 ㄱㅊ
        // 분해합이 N이 되려면 N보다 작아야함

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 어떤수가 i라고 한다면
        // N - i - i의자리수 = 0 이어야함
        int i;
        for(i = 0; i < N; i++){ // 최대 7번 도는 경우임
            int now = i;
            int sum = i;
            while(now != 0) {
                int cur = now % 10 ;// 10으로 나눈 나머지수 -> 뒷자리부터 더함
                now /= 10;
                sum += cur;
            }

            if(N == sum) break;
        }
        if(i == N) System.out.println(0); // 끝까지 돌고 + 1 된 상태니까 없는 거임
        else System.out.println(i); // 있는 경우
    }
}
