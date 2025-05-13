import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17609_회문 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for(int n = 0; n < N; n++) {
            String str = br.readLine();
            sb.append(calcPalindrome(str, 0,  str.length()-1, 0)).append("\n");
        }
        System.out.println(sb);
    }
    static int calcPalindrome(String str, int start, int end, int depth) {
        if(depth >= 2) return 2;
        while(start >= end){
            if(str.charAt(start) == str.charAt(end)){
                start++;
                end--;
            }
            else return Math.min(calcPalindrome(str, start+1, end, depth+1), calcPalindrome(str, start, end-1, depth+1));
        }
        return 2;
    }
}
