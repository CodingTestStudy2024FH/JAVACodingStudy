import java.util.*;
class Solution {
    HashMap<Integer, int[]> inAndOut = new HashMap<>();
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        int start = 0;
        int donut = 1;
        int stick = 2;
        int eight = 3;
        for(int[] edge: edges){
            int in = edge[0];
            int out = edge[1];
            if(!inAndOut.containsKey(in)) inAndOut.put(in, new int[]{0, 0});
            if(!inAndOut.containsKey(out)) inAndOut.put(out, new int[]{0, 0});
            
            inAndOut.get(in)[1]++;
            inAndOut.get(out)[0]++;
        }
        for(int key: inAndOut.keySet()){
            int[] edge = inAndOut.get(key);
            int in = edge[0];
            int out = edge[1];
            if(in == 0 && out > 1) answer[start] = key;
            else if(in >= 2 && out >= 2) answer[eight]++;
            else if(in >= 1 && out == 0) answer[stick]++;
        }
        answer[donut] = inAndOut.get(answer[start])[1] - answer[eight] - answer[stick];
        return answer;
    }
}
