import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int value: tangerine) map.put(value, map.getOrDefault(value, 0) + 1);
        for(int value: map.keySet()) pq.add(map.get(value));
        int sum = 0;
        while(!pq.isEmpty() && sum < k) {
            sum += pq.poll();
            answer++;
        }
        return answer;
    }
}
