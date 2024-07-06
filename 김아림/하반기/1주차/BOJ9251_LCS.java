package week_1st;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ9251_LCS {


    public static void main(String[] args) throws IOException {
        // 각 자리마다 각 자리는 무조건 앞 자리는 포함할수도, 하지 않을 수도 있는 수열 존재
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();


        int[][] count = new int[str1.length()+1][str2.length()+1];
        // 표로 만들어서 풀기 ;;


        for (int i = 0; i < str1.length(); i++) {
            char c1 = str1.charAt(i);
            for (int j = 0; j < str2.length(); j++) {
                char c2 = str2.charAt(j);
                // 현재 자리의 값이 같음 ! -> 이전 값까지 비교를 했을때의 길이 + 1
                if (c1 == c2) {
                    count[i+1][j+1] = count[i][j] + 1;
                } else {
                    // 현재 자리의 값이 다르다 -> 이전에서 가장 긴 값을 가져와야함 (첫번째 문자열에서 하나가 빠졌을때 혹은 두번째 문자열에서 하나가 빠졌을때)
                    count[i+1][j+1] = Math.max(count[i][j+1], count[i+1][j]);
                }
            }
        }

        System.out.println(count[str1.length()][str2.length()]);
    }
}
