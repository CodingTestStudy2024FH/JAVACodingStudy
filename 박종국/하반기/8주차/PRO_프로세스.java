import java.util.Collections;
import java.util.PriorityQueue;
class Solution{
    PriorityQueue<Integer> ascendingQueue;
    public int solution(int[] priorities, int location) {
        int answer = 0;
        ascendingQueue = new PriorityQueue<>(Collections.reverseOrder());
        int size = priorities.length;
        for(int p: priorities) ascendingQueue.add(p);
        while(!ascendingQueue.isEmpty()){
            for(int i = 0; i < size; i++){
                int priority = priorities[i];
                if(priority == ascendingQueue.peek()){
                    ascendingQueue.poll();
                    answer++;
                    if(location == i) return answer;
                }
            }
        }
        return answer;
    }
}
