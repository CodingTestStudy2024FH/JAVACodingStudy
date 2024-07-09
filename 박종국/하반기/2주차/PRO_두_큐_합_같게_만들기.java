package structure;


import java.util.ArrayDeque;
import java.util.Deque;

// 2022 KAKAO TECH INTERNSHIP
public class PRO_두_큐_합_같게_만들기 {


    public static void main(String[] args){
        int[] queue1 = {1, 2, 1, 2};
        int[] queue2 = {1, 10, 1, 2};
        System.out.println(solution(queue1, queue2));
    }

    static int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        long dq1Sum = getSum(queue1);
        long dq2Sum = getSum(queue2);

        if((dq1Sum + dq2Sum) % 2 == 1) return -1;

        Deque<Long> dq1 = toDeque(queue1);
        Deque<Long> dq2 = toDeque(queue2);
        int limit = queue1.length * 4;
        while(!dq1.isEmpty() && !dq2.isEmpty() && limit-- > 0){
            if(dq1Sum == dq2Sum) {
                return ++answer;
            }
            else if(dq1Sum > dq2Sum) {
                long temp = dq1.pollFirst();
                dq1Sum -= temp;
                dq2Sum += temp;
                dq2.addLast(temp);
            }
            else {
                long temp = dq2.pollFirst();
                dq1Sum += temp;
                dq2Sum -= temp;
                dq1.addLast(temp);
            }
            answer++;
        }
        return -1;
    }

    static Deque<Long> toDeque(int[] queue) {
        Deque<Long> deque = new ArrayDeque<>();
        for(int v: queue) deque.addLast((long) v);
        return deque;
    }

    static long getSum(int[] queue) {
        long sum = 0;
        for(int v: queue) sum += v;
        return sum;
    }
}
