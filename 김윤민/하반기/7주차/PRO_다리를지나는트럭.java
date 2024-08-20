package PRO;

import java.util.ArrayDeque;
import java.util.Deque;

public class PRO_다리를지나는트럭 {

    public static void main(String[] args) {
        System.out.println(solution(2,10,new int[]{7,4,5,6}));;
    }
    /*
        모든 트럭이 다리를 건너려면 최소 몇초?
        다리는 weight 이하까지 가능

        큐를 이용한 단순 구현?
     */
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        Deque<Integer> q = new ArrayDeque<>();
        
        int nowWeight = 0; //다리 위의 무게
        for(int truck: truck_weights){ //하나씩 차례대로 건너감 - 순서 유지 필요
            while(true){
                if(q.isEmpty()){ //건너고 있는 차 없으면
                    q.add(truck); //현재 꺼 넣고
                    nowWeight += truck; //무게와 시간 추가
                    time++;
                    break;
                }else if(q.size()==bridge_length){//만약 다리가 다 찼으면
                    nowWeight -= q.poll();//계속 내림
                }else {//태울 수 있는 상태인데
                    if(nowWeight+truck<=weight){//무게가 되면  
                        q.add(truck); //추가
                        nowWeight+=truck;
                        time++;
                        break;
                    }else{//무게 안되면
                        q.add(0);//이번 차는 패스하고 시간 건너고 다음 while문 반복
                        time++;
                    }

                }
            }
        }
        return time;
    }
}
