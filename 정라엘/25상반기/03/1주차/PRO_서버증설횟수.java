import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[0] - o2[0]);
        int size=0;  // 현 서버
        int cnt=0; // 증설 서버 
        // 하루는 24시간
        for(int i=0; i<24; i++){
            // 만료 서버 종료
            while(!pq.isEmpty() && pq.peek()[0] == i){
                size -= pq.poll()[1]; // 서버 사이즈 감소
            }
            
            int need = players[i] / m;  // 필요 서버 개수
            int diff = size - need;     //현재 서버 용량과 해당 시간대에 필요한 서버 개수 차이

            // 부족하다면
            if(diff < 0){
                diff = -diff; // 증설 하자!
                size  += diff;
                cnt += diff;
                pq.add(new int []{i + k, diff});    // k 시간 후 만료되는 서버 증설
            }
        }
        return cnt;
    }
}
