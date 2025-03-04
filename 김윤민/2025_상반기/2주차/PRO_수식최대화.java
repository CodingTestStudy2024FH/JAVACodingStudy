import java.security.PublicKey;
import java.util.*;

public class PRO_수식최대화 {
    /*
        3가지 숫자와 연산문자 +,-,*
        같은 순위의 연산자는 없다.
        우선순위를 정할 수 있음. 가장 큰 숫자를 제출.
        가장 큰 값을 정하시오.
        결과값은 절대값.
     */
    public static void main(String[] args) {
        System.out.println(solution("50*6-3*2"));
    }
    public static Long max;
    public static List<String> express;
    public static boolean[] visited;
    public static long solution(String expression) {
        //분리작업 시작 -> List화 시켜서 인덱스를 이용한 연산 실행
        int length = 0;
        StringBuilder sb = new StringBuilder();
        express = new ArrayList<>();
        while(length!=expression.length()){
            char now = expression.charAt(length);
            if (length == expression.length()-1) { //마지막 요소라면
                sb.append(now);
                express.add(sb.toString());
                break;
            }
            if(now=='*'||now=='+'||now=='-'){
                express.add(sb.toString());//이전까지 누적된 숫자를 넣고,
                express.add(now+""); // 기호도 함께 넣음.
                sb = new StringBuilder();
            }else{
                //기호가 아니라면 추가
                sb.append(now);
            }
            length++;
        }
        //분리 종료
        //logic start
        visited = new boolean[3];
        max = 0L;
        dfs("");
        return max;
    }

    //priority = * + -
    public static void dfs(String s){
        if(s.length()==3){
            long ans = Math.abs(logic(s));
            max = Math.max(max, ans);

            return;
        }
        for(int i=0;i<3;i++){
            if(visited[i]){//우선순위가 정해져 있으면 넘기고
             continue;
            }
            //1. 우선순위 정하기
            visited[i]=true;
            switch (i){
                case 0:
                    dfs(s + '*');
                    break;
                case 1:
                    dfs(s + '+');
                    break;
                case 2:
                    dfs(s + '-');
                    break;
            }
            //2. 우선수위 안정하기
            visited[i]=false;

        }


    }
    // 로직
    public static long logic(String s){
        List<String> ex2 = new ArrayList<>(List.copyOf(express)); //복제
        for(int i=0;i<3;i++){
            char now = s.charAt(i);
            int j=1;
            while(ex2.contains(now+"")){ //현재 연산자 있으면 계속 연산
                if (ex2.get(j).equals(now+"")) { //해당하는 연산자가 지금이면 연산하기
                    long prev = Long.parseLong(ex2.get(j - 1));
                    long next = Long.parseLong(ex2.get(j + 1));
                    //연산자까지 총 3개 삭제
                    ex2.remove(j - 1);
                    ex2.remove(j - 1);
                    ex2.remove(j - 1);
                    Long result = cal(prev, next, now); //계산
                    //해당 자리에 연산 결과 삽입
                    ex2.add(j - 1, result.toString());
                    //다 끝난자리에 새로운 연산자가 오기 때문에 그자리 한번 더 연산
                }else { // 아니라면 다음 연산자로 이동
                    j += 2;
                }

            }
        }
        return Long.parseLong(ex2.get(0));
    }
    public static Long cal(long prev,long next,char expression){
        return switch (expression) {
            case '+' -> prev + next;
            case '-' -> prev - next;
            case '*' -> prev * next;
            default -> 0L;
        };
    }


}
