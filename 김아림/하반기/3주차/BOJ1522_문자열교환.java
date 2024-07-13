package week_3rd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1522_문자열_교환 {
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();


        // ㅜㅜ 아이디어 안떠오름
        // 슬라이딩 윈도우 ? <- 가 뭔데
        /**
         * 슬라이딩 윈도우
         * a의 개수를 확인 -> 인덱스를 0번부터 확인하면서, 지점부터 a의 개수만큼의 길이 문자열 체크 후 b count
         */

        // ababab -> aaabbb 같은 형태가 되어야함
        // a가 제일 많이 모여있는 부분에 a를 두면 됨
        // a의 개수를 구하고 -> a의 개수만큼 길이를 가진 문자열 중에서 a가 가장 많은 문자열을 찾아서 b가 몇개인지 체크
        // 이미 b들이 제일 많이 모여있는 부분에 b를 주면 됨.
        // b의 개수를 구한 다음에 -> b의 개수만큼 길이를 가진 연속된 문자열 중에서 b가 가장 많은 문자열을 찾아서 a가 몇개인지


        int count_a = 0;
        int count_b = 0;

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c == 'a') count_a++;
            else count_b++;
        }

        int start = 0;
        int end = count_a - 1;// 0부터 a의 개수 길이까지 체크하는 법
        int min = count_b; // b를 전부 옮기는 방법이므로 최대 이만큼 필요함이라 생각

        while(start < str.length()) {
            if(str.charAt(++end % str.length()) == 'b') count_b++; // 원형이니까
            if(str.charAt(start++) == 'b') count_b--; // b의 개수 체크하기 (이때 length를 하나씩 밀기 때문에 빼줘야함)
            // start는 미리 검사하고 b라면 -1한 다음에 start 뒤로 한칸 당기기
            // end는 미리당긴뒤에 원형으로 복귀 해주고, b라면 count++
            // b의 개수가 적다 -> a로 이미 많이 차있다 == b의 개수가 최소 교환 개수이므로
            min = Math.min(min, count_b);
        }

        System.out.println(min);

    }
}
