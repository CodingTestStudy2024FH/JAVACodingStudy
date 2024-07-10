package PRO;

import java.util.ArrayDeque;
import java.util.Deque;

/*
    추출하고 넣고.
    각 큐 합이 같게 만들도록 한다.

    최소 몇번 작업해야지 같게 나오나?

 */
public class PRO_두_큐_합_같게만들기 {
    public static void main(String[] args) {

    }
    public static int solution(int[] queue1, int[] queue2) {
        int cnt = 0;
        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();

        int all = 0;
        int sum1= 0;
        for(int i=0;i<queue1.length;i++){ //길이는 같음
            sum1 += queue1[i]; //q1합
            all += queue1[i]+queue2[i]; //전체 합
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }
        if(all%2!=0) return -1; //두큐 합 홀수라면? 못함 일치 X
        
        int md = all/2; //나눠가져야 할 목표치
        
        while(true){
            if(cnt>(queue1.length*4)) return -1; // 더 순회해서 만들 수 없는경우
            if(md == sum1) break; //같으면 멈추고
            else if(sum1>md) {//q1이 크면
                int tmp = q1.poll();//값 빼고
                sum1 -= tmp;
                q2.offer(tmp);//2에 더하고

            }else{//더 작으면
                int tmp = q2.poll();
                sum1+=tmp;
                q1.offer(tmp); //q1에 더함
            }
            cnt++;
        }
        return cnt;
    }
}
