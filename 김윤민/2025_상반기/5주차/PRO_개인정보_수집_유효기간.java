import java.sql.Array;
import java.util.*;

public class PRO_개인정보_수집_유효기간 {
    public static void main(String[] args) {
        String[] terms = {"A 13"};
        String[] p ={"2008.11.03 A"};
        System.out.println(Arrays.toString(solution("2009.12.28"	,terms,p)));
        //2008.11.03 / 13 / 24 / 2010.01.03
    }
    /*
        1~n번 분류되는 개인정보 n
        유효기간 전까지만 보관 가능
        모든 달은 28일까지만 있음.

        파기해야할 개인정보의 번호를 오름차순으로 1차원 정수배열에 담아 return
     */
    static Map<Character, Integer> term;
    public static int[] solution(String today, String[] terms, String[] privacies) {
        String[] now = today.split("\\.");
        int nowY = Integer.parseInt(now[0]);
        int nowM = Integer.parseInt(now[1]);
        int nowD = Integer.parseInt(now[2]);
        term = new HashMap<>();
        for(String s:terms){
            StringTokenizer st = new StringTokenizer(s);
            term.put(st.nextToken().charAt(0),Integer.parseInt(st.nextToken()));
        }
        ArrayList<Integer> list = new ArrayList<>();
        //모두 계산된 값으로 넣기.
        int i=1;
        for(String privacy:privacies){
            StringTokenizer st = new StringTokenizer(privacy);
            //2021.05.02 A
            String date = st.nextToken();
            String[] split = date.split("\\.");
            int y = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);
            int d = Integer.parseInt(split[2]);

            char target = st.nextToken().charAt(0);
            Integer add = term.get(target);
            m += add;
            y += (m - 1) / 12;
            m = (m - 1) % 12 + 1;
            // 년도가 지금이 더 크면 파기
            if(nowY>y){
                list.add(i);
            }else if(nowY==y&&nowM>m){ //년이 같은데 월이 크면 폐기
                list.add(i);
            }else if(nowY==y&&nowM==m&&nowD>=d){ //다 같거나 day가 더 크면 폐기
                list.add(i);
            }
            i++;
        }


        return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

}

