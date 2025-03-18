package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_1174_줄어드는_수 {
    static int N;
    static ArrayList<Long> decreaseNumbers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if(N > 1023) {
            System.out.println(-1);
            return;
        }
        makeDecreaseNumbers();
        Collections.sort(decreaseNumbers);

        System.out.println(decreaseNumbers.get(N-1));

    }
    static void makeDecreaseNumbers(){
        for(int i = 0; i <= 9; i++) generate(i, i);
    }
    static void generate(long currentNumber, int lastDigit){
        decreaseNumbers.add(currentNumber);
        for(int next = 0; next < lastDigit; next++) generate(currentNumber * 10 + next, next);
    }
}
