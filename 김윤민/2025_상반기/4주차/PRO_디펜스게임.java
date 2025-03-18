import java.util.Collections;
import java.util.PriorityQueue;

public class PRO_디펜스게임 {
    /*
        n명으로 연속되는 적의 공격을 순서대로 막음.
        남은 병사 수 < 현재 라운드 적의 수 => 게임 종료

        무적권 을 사용하면 한 라운드 공격 막음
        최대 K번 사용.

        최대한 많은 라운드 진행.

        => k번 무적권은 꼭 쓰돼, 나머지의 합이 최소값? -> 아니면 dfs?
     */
    public static void main(String[] args) {
        int[] arr = {4, 2, 4, 5, 3, 3, 1};
        System.out.println(solution(7,3,arr));
    }
    static int K,L,max;
    public static int solution(int n, int k, int[] enemy) {
        L = enemy.length;
        max = Integer.MIN_VALUE;
        K = k;
//        dfs(0,n,0,enemy); 시간초과남
        /*
            1. 일단 돌고
            2. 만약에 병사가 부족해지는 순간이 오면 가장 큰값을 현 병사에 더해주고 무적권 사용
            3. 첫번째부터 돌기때문에 순서상관없어짐. 아마도?
         */
        //reverseOrder 내림차순정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int nowCnt=n;
        int guard = 0;
        for(int i=0;i<L;i++){
            pq.offer(enemy[i]);//일단 넣고?
            nowCnt-=enemy[i];
            if(nowCnt<0){ //부족해지는 순간이라면 ? 무적권 사용
                if(guard<k){ //사용할 수 있다면,
                    nowCnt+=pq.poll();//하나 빼고 현재 병사수에 저장하기
                    guard++;
                }else{ //사용할 수 없다면 이전라운드가 최대
                    return i;
                }
            }

        }
        return enemy.length; //모두 끝났다면 최대길이만큼 도달가능
    }

    //idx는 라운드, army는 남은 병사, cnt는 무적권을 몇개 썼는가?
    //아니면 그냥 순열을 지정해서 순환? -> 비효율적이지 않나
    //너무 오래걸림 -> 그리디?
    static void dfs(int idx,int army,int cnt,int[] enemy){
        if(idx>=L){
            max = L; //끝까지 도달했으면 L이 최대 라운드
            return;
        }

        //1. 현 라운드에서 무적권을 쓴 경우 + 무적권이 남아있으면
        if (cnt<K){
            dfs(idx+1,army,cnt+1,enemy);
        }
        //2. 무적권을 안쓴 경우-더이상 라운드 진행불가인 경우
        if(army-enemy[idx]<0){
            max = Math.max(max,idx);
            return;
        }else{//진행 가능한 경우
            dfs(idx+1,army-enemy[idx],cnt,enemy);
        }
    }
}
