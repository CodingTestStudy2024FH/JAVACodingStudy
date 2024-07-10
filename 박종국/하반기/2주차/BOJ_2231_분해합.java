package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2231_분해합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i = 1; i <= N; i++) {
            String str = String.valueOf(i);
            if(N == calculate(str)){
                System.out.println(str);
                return;
            }
        }
        System.out.println(0);
    }
    static int calculate(String str) {
        int size = str.length();
        int sum = Integer.parseInt(str);
        for(int i = 0; i < size; i++) sum += str.charAt(i) - '0';
        return sum;
    }
}
