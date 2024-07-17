package week_1st;
import java.util.*;

public class PRO_롤케이크자르기 {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new int[] {1, 2, 1, 3, 1, 4, 1, 2});
    }
}


class Solution {
    public int solution(int[] topping) {
        // 동생것에는 토핑 전부 넣고 시작
        // 철수것에는 토핑 0으로 시작
        int answer = 0;

        // for문으로 하나씩 돌면서 철수에는 추가하고 동생에는 빼기 -> 공평해지면 +1
        HashMap<Integer, Integer> chuls = new HashMap<>();
        HashMap<Integer, Integer> sis = new HashMap<>();
        int SSize = 0;

        for(int i = 0; i < topping.length; i++){
            if(sis.containsKey(topping[i])){
                sis.replace(topping[i], sis.get(topping[i]) + 1);
            }else{
                sis.put(topping[i],1);
            }
        }

        SSize = sis.size();

        for(int i = 0; i < topping.length; i++){
            // 철수에게 추가하기
            if(chuls.containsKey(topping[i])){
                chuls.replace(topping[i], chuls.get(topping[i]) + 1);
            }else{
                chuls.put(topping[i],1);
            }
            // 동생에게서 빼기
            if(sis.get(topping[i]) > 1){
                sis.replace(topping[i], sis.get(topping[i]) - 1);
            }else if(sis.get(topping[i]) == 1){
                SSize--;
            }

            if(chuls.size() == SSize){
                answer ++;
            }
        }

        System.out.println(answer);
        return answer;

    }
}