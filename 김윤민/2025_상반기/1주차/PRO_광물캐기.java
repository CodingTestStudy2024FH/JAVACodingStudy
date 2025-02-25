package programmers;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class PRO_광물캐기 {
    public static void main(String[] args) {
        String[] arr = {"stone", "stone", "stone", "stone", "stone", "diamond", "diamond", "diamond", "diamond", "diamond", "diamond", "diamond", "diamond", "diamond"};
        int[] picks = {0, 0, 1};

        System.out.println(solution(picks,arr));
    }
    public static int solution(int[] picks, String[] minerals) {
        //무조건 5개씩 연속으로 캐야하니까 한번 minerals를 돌때 계산.
        //우선순위큐를 사용해서 (체력,곡괭이)이렇게 묶고.
        //없으면 다음, 없으면 다음

        int all = picks[0] + picks[1] + picks[2];// 곡괭이 전체 개수
        // 5씩 묶어서 최대 시작 인덱스
        int max = minerals.length/5;
        if(minerals.length%5!=0) max++;

        PriorityQueue<Inform> list = new PriorityQueue<>();
        int cnt=0;
        int[] sumList = new int[3];//dia,iron,stone
        ArrayList<Integer> dia = new ArrayList<>();
        ArrayList<Integer> iron = new ArrayList<>();
        //all과 max의 개수를 비교.
        while (cnt != minerals.length) {
            if (minerals[cnt].equals("diamond")) {
                sumList[0] += 1;
                sumList[1] += 5;
                sumList[2] += 25;
            } else if (minerals[cnt].equals("iron")) {
                sumList[0] += 1;
                sumList[1] += 1;
                sumList[2] += 5;
            } else {
                sumList[0] += 1;
                sumList[1] += 1;
                sumList[2] += 1;
            }

            if (cnt % 5 == 4 || cnt == minerals.length - 1) {//현재 값이 5번째 값이라면 해당값 우선순위 큐에 적립
                int index = cnt/5;
                dia.add(sumList[0]);
                iron.add(sumList[1]);
                list.offer(new Inform(sumList[2],index));
                //세 값 초기화
                sumList = new int[3];
                if(all<max&&(all*5-1)==cnt){
                    break;
                }
            }
            cnt++;

        }
        int heart = 0;
        cnt=0;

        //main 로직 시작
        while (all != 0&&max!=cnt) {// 이제 여기는 stone밖에 없음.
            //stone이 가장 큰것부터 dia로 해결해야함.
            Inform poll = list.poll();

            if(picks[0]>0){//다이아 곡괭이가 남아있다면
                heart += dia.get(poll.st);//해당 인덱스의 값을 더해준다.
                picks[0]--;//곡갱이수 하나까고
            } else if (picks[1] > 0) {//다야 곡괭이 없고 iron은 있다면
                heart += iron.get(poll.st);
                picks[1]--;
            }else if( picks[2]>0){
                heart+= poll.heart;
                picks[2]--;
            }
            cnt++;
            all--;
        }

        return heart;
    }
    static class Inform implements Comparable<Inform>{
        public int heart,st;

        public Inform(int heart,int st) {
            this.heart = heart;
            this.st = st;
        }

        @Override
        public int compareTo(Inform o) {
            return o.heart-this.heart;
        }


    }


}
