import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine(); // 입력 문자열

        int total = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a') total++;
        }

        int max = 0; //최댓값
        for (int i = 0; i < str.length(); i++) {
            int cnt = 0; // 부분 문자열에서 개수 카운트

                for (int j = 0; j < total; j++) {
                // 범위 벗어났을 시
                int index = (i + j < str.length() ? i + j : i + j - str.length()); 

                if (str.charAt(index) == 'a') cnt++;

                if (cnt > max) max = cnt; // 최댓값과 비교
            }
        }

        System.out.println(total - max);
    }
}
