import java.io.*;
import java.util.*; 

class Solution {
    
    /**
    풀다가 도저히 안돼서 블로그 보기 
    범위가 너무 크기 때문에 단순 반복으로 해결할 수 없음 
    각 자리 Index를 봤을 때, index 숫자의 가장 큰 약수를 가짐으로 알 수 있다. (이때 소수들은 1을 가질 수 밖에 없음)
    
    2부터 자신의 제곱근까지 검사하며서 나눠 떨어지는 몫 -> 가장 큰 약수가됨 ! 
    
    */
    
    
    public static int[] solution(long begin, long end) {
        // 범위 만큼의 배열 생성 
        int[] answer = new int[(int)(end - begin) + 1];

        // 시작점 인덱스 ++ 해주면서 end까지 가도록 
        for (int i = (int)begin,idx = 0; i <= end; i++) {
            // 배열 인덱스는 당연히 0부터 시작하고
            // 검증하는 값은 begin 부터 시작함 
            answer[idx++] = getMaxDivisorExceptMe(i);
        }

        return answer;
    }

    private static int getMaxDivisorExceptMe(int x) {
        // 1이라면 0 
        if (x == 1) {
            return 0;
        }

        // 리스트 
        List<Integer> l = new ArrayList<>();

        // 2부터 제곱근까지 돌면서 -> 나눈 값의 목이 소수가 됨 (나누어 떨어지는 경우에만)
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) { // 나누어 떨어짐 
                l.add(i); // 나눈값은 일단 약수에 포함됨 -> 나누어 떨어진 몫이 아니라 나눈값을 넣어줌 
                if (x / i <= 10_000_000) { // 10_000_000 보다 작은 경우에는 
                    return x/i; // 그 값이 바로 블록의 값이됨 (가장 큰 약수)
                }
            }
        }
        
        // 만약 리턴되지 않았고 + 소수는 존재한다면 
        if (!l.isEmpty()) {
            // 현재 가장 큰 약수값을 리턴해줌 
            // 이걸 왜 이렇게 잡아주지 라고 생각했는데 -> 제곱근 까지 나누면서의 몫을 체크하면 
            // 나누어 떨어지는 경우 떨어진 몫, 나눈값 모두 해당 값의 약수가 되는거임 ! 
            return l.get(l.size() - 1);
        }

        // 아무것도 없다면? 1로 채워야지 (1은 모든 수의 약수가되잖아요)
        return 1;
    }
}
