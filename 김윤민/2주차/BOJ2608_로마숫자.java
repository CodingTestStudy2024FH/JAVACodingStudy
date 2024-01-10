package week2;
import java.io.*;
import java.util.*;
public class BOJ2608_로마숫자 {
    /*
        큰 숫자를 왼쪽에, 작은 숫자를 오른쪽에.
        값은 모든 숫자의 값을 더한 값.

          I : 1 / V : 5 / X : 10 /
          L : 50 / C : 100 / D : 500 / M : 1000

          1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
        1. V, L, D => 1번만
        2. I, X, C, M => 연속해서 세 번
        3. 작은숫자가 큰숫자의 왼쪽에 오는경우
        => IV = 4, IX = 9, XL = 40, XC = 90, CD = 400, CM = 900
        => 한번씩만 사용 가능
        => IV, IX는 같이 못씀
        => XL, XC 같이 못씀
        => CD, CM 같이 못씀
        외에 작은 숫자가 큰 숫자 왼쪽에 나올 수 없다.

        가능한 적은 개수로 ㄹ표현.
     */
    static Map<String, Integer> map = new HashMap<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 큰숫자를 왼쪽에 두니까
        // 나 이후에 숫자가 나보다 크면 하나로 묶고
        String A = br.readLine()+"Z";
        String B = br.readLine()+"Z"; //Z는 마지막 수를 비교하기 위한 허수
        init();
        int ans = getNum(A)+getNum(B);
        System.out.println(ans);
        System.out.println(getArab(ans));


    }
    //I : 1 / V : 5 / X : 10 /
    //L : 50 / C : 100 / D : 500 / M : 1000
    //IV = 4, IX = 9, XL = 40, XC = 90, CD = 400, CM = 900
    public static String getArab(int num){
        //걍 노가다 아님?
        StringBuilder sb = new StringBuilder();
        int ans = arab(num,sb,1000,"M");
        ans = arab(ans, sb, 900, "CM");
        ans = arab(ans, sb, 500, "D");
        ans = arab(ans, sb, 400, "CD");
        ans = arab(ans, sb, 100, "C");
        ans = arab(ans, sb, 90, "XC");
        ans = arab(ans, sb, 50, "L");
        ans = arab(ans, sb, 40, "XL");
        ans = arab(ans, sb, 10, "X");
        ans = arab(ans, sb, 9, "IX");
        ans = arab(ans, sb, 5, "V");
        ans = arab(ans, sb, 4, "IV");
        ans = arab(ans, sb, 1, "I");

        return sb.toString();
    }

    private static int arab(int num, StringBuilder sb,int arab,String arabNum) {
        if( num /arab != 0){
            for(int i = 0; i< num /arab; i++){
                sb.append(arabNum);
            }
        }
        return num%arab;
    }

    public static int getNum(String s){
        int ans=0;
        //0~size-1까지 탐색하면서
        //나와 이후의 숫자 비교 
        for(int i=0;i<s.length()-1;i++){
            int now = map.get(s.charAt(i)+"");
            int next = map.get(s.charAt(i+1)+"");
            if(now<next){ //이후가 나보다 크면 합성수
                ans += (next-now); 
                i++; //다음수 넘기기
            }else{
                ans+= now;
            }
        }
        return ans;
    }
    //I : 1 / V : 5 / X : 10 /
    //L : 50 / C : 100 / D : 500 / M : 1000
    //IV = 4, IX = 9, XL = 40, XC = 90, CD = 400, CM = 900
    public static void init(){
        map.put("Z", 0);
        map.put("I",1);
        map.put("V",5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);

    }
}
