package PRO;

import java.util.*;

public class PRO_시소짝궁 {
    /*
        2,3,4 지점에 좌석
        무게와 거리곱이 양쪽 다 같다면 시소짝궁

        몇쌍?

        한 쌍마다
        3x3의 경우의 수 존재. ==> 너무많은데.
        미리 구하면 되나? 중복처리
     */
    public static void main(String[] args) {

    }
    public long solution(int[] weights) {
        long answer = 0;

        Map<Double, Integer> map = new HashMap<>();
        Arrays.sort(weights); //몸무게 정렬 (^....^ㅜ)
        for(int weight : weights) {
            answer += logic(weight, map);
        }
        return answer;
    }
    public long logic(int w, Map<Double, Integer> map) {
        long answer = 0;
        double d1 = w*1.0; //각 자리의 몸무게의 경우의 수를 모두 구한다.
        double d2 = (w*2.0)/3.0; 
        double d3 = (w*1.0)/2.0;
        double d4 = (w*3.0)/4.0;
        if(map.containsKey(d1)) answer += map.get(d1); //만약 있따면 경우의 수 더하고 
        if(map.containsKey(d2)) answer += map.get(d2);
        if(map.containsKey(d3)) answer += map.get(d3);
        if(map.containsKey(d4)) answer += map.get(d4);
        map.put(w*1.0, map.getOrDefault(w*1.0, 0)+1); //없어도 일단 map에 넣기
        return answer;
    }
}
