import java.util.*;

public class BOJ9935_문자열폭발 {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();  // 전체 문자열
        String bomb = sc.nextLine(); // 폭발 문자열

        StringBuilder sb = new StringBuilder();
        int bombLen = bomb.length();
        
        for (char c : str.toCharArray()) {
            sb.append(c);

            // 폭발 문자열과 일치하는 부분이 있는지 확인
            if (sb.length() >= bombLen && sb.substring(sb.length() - bombLen).equals(bomb)) {
                sb.delete(sb.length() - bombLen, sb.length()); // 폭발 문자열 제거 (뒤에서부터 없애야 하네~ 조건 만족을 위해!!)
            }
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }
}
