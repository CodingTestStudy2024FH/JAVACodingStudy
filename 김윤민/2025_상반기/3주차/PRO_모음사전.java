import java.util.*;
public class PRO_모음사전 {
    /*
    'A','E','I','O','U'
    5길이 이하 모든 단어 수록

    word가 몇번째 단어인지 return 하도록

    각각 하나씩 총 5개의 경우.

    */
    public static void main(String[] args) {
        System.out.println(solution("AAAAE"));
    }
    static String target;
    static int ans,cnt;
    static char[] alpha = {'A','E','I','O','U'};
    public static int solution(String word) {
        target = word;
        dfs("");
        return ans;
    }
    public static void dfs(String s){//중복 순열?
        if(ans!=0) return;
        if(s.length()>5) return;

        for(int i=0;i<5;i++){
            String tmp = s+alpha[i];
            if(tmp.length()>5) continue;
            if(tmp.equals(target)){
                ans = cnt+1;
                return;
            }else{
                cnt++;
                dfs(s + alpha[i]);
            }

        }
    }

}
