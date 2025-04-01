public class PRO_퍼즐게임챌린지 {
    /*
        n개의 퍼즐을 제한시간 내에 풀어야함.
        현재 난이도 diff, 내 숙련도 level
        diff<=level이면 time_cur 시간들여서 해결.
        diff>level -> diff-level번 틀림.
        (diff-level)*(time_cur+time_prev)+time_cur

        제한시간 내에 퍼즐을 모두 해결하기 위한 숙련도의 최솟값.
        
        이분탐색인가?
        end = limit
        st = 0으로 두고.. st가 해당 로직을 만족하는가?를 기준으로 판단하면 될거같은데
        안해본지 넘 오래됐는데 이분탐색
     */
    public static void main(String[] args) {
        int[] diffs = {1, 328, 467, 209, 54};
        int[] times = {2, 7, 1, 4, 3};
        long limit = 1723;
        System.out.println(solution(diffs,times,limit));
    }
    public static int solution(int[] diffs, int[] times, long limit) {

        long st = 1; //1부터 시작. level은 양의 정수
        long ed = limit;
        long mid =(st+limit)/2;
        while(st<ed){
            mid =(st+ed)/2;

            //mid가 logic을 만족하면 ed = mid
            if(logic(diffs,times,limit,mid)){
                ed = mid;
            }else{//만약 만족못하면? st = mid
                if(mid==st) break; //같으면 더이상 연산할 곳이 없음. ed가 끝
                st = mid;
            }

        }

        //break된 조건이 st가 false인 상태 && st=mid이니까 더이상 연산 불가능 -> ed가 정답.
        return (int)ed;
    }
    // 레벨로 해당 시간내에 처리가능한지 보는 Logic
    public static boolean logic(int[] diffs,int[] times,long limit, long level){
        long time = 0;
        for(int i=0;i<diffs.length;i++){
            if(level>=diffs[i]){ //해결가능하면 그대로 걸리는 시간 더하기
                time+=times[i];
            }else{
                long time_prev = i==0?0:times[i-1]; //이전 퍼즐 시간
                time+=(diffs[i]-level)*(times[i]+time_prev)+times[i]; //해결 불가능하면 로직대로 처리후 더하기
            }
            if(time>limit) return false;
        }
        return true;
    }

}
