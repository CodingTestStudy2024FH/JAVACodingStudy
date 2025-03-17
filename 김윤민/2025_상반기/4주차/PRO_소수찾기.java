import java.util.HashSet;
import java.util.Set;

public class PRO_소수찾기 {
    /*
        순열 조합 중복은 안되지만 순서는 뒤죽박죽이 가능한.
        3개 다 선택의 여부가 됐을때 확인
    */
    public static void main(String[] args) {
        System.out.println(solution("011"));
    }

    static int N,ans;
    static int[] arr;
    static boolean[] used;
    static Set<Integer> set;
    public static int solution(String numbers) {
        N = numbers.length();
        arr = new int[N];
        used = new boolean[N];
        set =new HashSet<>();
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(numbers.charAt(i)+"");
        }
        //input end


        //logic
        dfs(0,0);
        for(Integer now:set){ //set에서 소수 판별
            if(isPrime(now)){
                ans++;
            }
        }
        return ans;
    }
    static boolean isPrime(int now){ //소수 판별
        if(now==0||now==1) return false;
        for (int i = 2; i*i<=now; i++) {
            if (now % i == 0) {
                return false;
            }
        }
        return true;
    }
    static void dfs(int num, int depth) {
        if (depth > 0) { //하나라도 숫자가 추가된 상태면 Set에 추가.
            set.add(num);
        }
        if (depth == N) return; //N인경우는 이미 최대 N가지의 숫자를 다 조합한 후이므로 return

        for (int i = 0; i < N; i++) {
            if (used[i]) continue; //왔으면 continue

            used[i] = true;
            dfs(num * 10 + arr[i], depth + 1); //숫자 사용
            used[i] = false; //안한경우는 자동으로 다음으로 넘어감.
        }
    }
}
