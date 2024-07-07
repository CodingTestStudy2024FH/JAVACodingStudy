package structure;

import java.io.IOException;
import java.util.HashMap;

public class PRO_롤케이크_자르기 {
    public static void main(String[] args) throws IOException {
        int[] topping = {1,2,1,3,1,4,1,2};
        System.out.println(solution(topping));

    }
    public static int solution(int[] topping){
        int answer = 0;

        HashMap<Integer,Integer> older = new HashMap<>();
        HashMap<Integer,Integer> younger = new HashMap<>();

        for(int top: topping) older.put(top,older.getOrDefault(top,0)+1);
        for(int top: topping) {
            younger.put(top,younger.getOrDefault(top,0)+1);
            if(older.containsKey(top)) {
                older.put(top, older.get(top) - 1);
                if(older.get(top) == 0) older.remove(top);
            }
            if(younger.size() == older.size()) answer++;
        }

        return answer;
    }
}
