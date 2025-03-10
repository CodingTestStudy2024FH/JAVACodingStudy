import java.io.*;
import java.util.*;
public class BOJ1174_줄어드는수 {
    /*
         음이 아닌 정수
         왼쪽부터 자리가 감소할 때 줄어드는 수
         321 950
         N번째로 작은 줄어드는 수
         그런 수가 없으면 -1

     */
    static List<Long> list;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        dfs(0,"");
        Collections.sort(list);
        if (n > list.size() || n <= 0) {  // ✅ 올바른 비교 조건
            System.out.println(-1);
        } else {
            System.out.println(list.get(n - 1));
        }


    }

    // 숫자는 10개로 한정되어 있으니, 줄어드는 수 중에 가장 큰 수는 9876543210임.
    // 따라서, 길이도 10까지가 최대.
    static int[] numbers = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    public static void dfs(int idx,String s){
        if(idx>10) return;
        if(idx==10){
            if(s.equals(""))
                return;
            Long now = Long.parseLong(s.toString());

            if(!list.contains(now)) {
                list.add(now);
            }

            return;
        }
        //현재 선택
        dfs(idx+1, s+numbers[idx]);
        //선택 안함
        dfs(idx + 1, s);
    }
}
