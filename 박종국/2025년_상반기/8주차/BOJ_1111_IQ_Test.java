package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1111_IQ_Test {
    static int N;
    static int[] numbers;
    static char A = 'A', B = 'B';
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++) numbers[n] = Integer.parseInt(st.nextToken());

        if(N == 1 || (N == 2 && numbers[0] != numbers[1])){
            System.out.println(A);
            return;
        }
        if(N == 2){
            System.out.println(numbers[0]);
            return;
        }
        int a = 0, b = 0;
        if(numbers[0] == numbers[1]){
            a = 1;
            b = 0;
        }
        else{
            a = (numbers[2] - numbers[1]) / (numbers[1] - numbers[0]);
            b = numbers[1] -(numbers[0] * a);
        }

        for(int i = 1; i < N; i++){
            if(numbers[i] == numbers[i-1] * a + b) continue;
            System.out.println(B);
            return;
        }
        System.out.println(numbers[N-1] * a + b);
    }
}
