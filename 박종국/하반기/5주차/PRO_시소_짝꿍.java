import java.util.*;
class Solution {
    int[] distances = {2,3,4};
    public long solution(int[] weights) {
        long answer = 0;
        int length = weights.length;
        HashMap<Double, Integer> map = new HashMap<>();
        Arrays.sort(weights);
         for(int weight : weights) {
    		double a = weight*1.0;
    		double b = (weight*2.0)/3.0;
    		double c = (weight*1.0)/2.0;
    		double d = (weight*3.0)/4.0;
             
    		if(map.containsKey(a)) answer += map.get(a);
    		if(map.containsKey(b)) answer += map.get(b);
    		if(map.containsKey(c)) answer += map.get(c);
    		if(map.containsKey(d)) answer += map.get(d);
    		map.put(a, map.getOrDefault(a, 0)+1);
        }
        
        return answer;
    }
}
