import java.util.*;

class Solution_마법의_엘레베이터 {
    
    static int answer = Integer.MAX_VALUE; 
    static int[] numbers; 
    static int size = 0; 
    public int solution(int storey) {
        
        
        // 제일 끝 자리 수부터 0으로 만든다고 생각해야함 -> 
        size = (int) Math.log10(storey); // 16 -> 1 
        numbers = new int[size + 1]; 
        for (int i = size; i >= 0; i --){ 
            int number = (int) Math.pow(10, i); // 10의 3승 
            int l = (int) (storey / number);
            numbers [size - i] = l;
            storey -= number * l; 
        }
        
        // 각 자리수 다 구함 
        System.out.println(Arrays.toString(numbers)); 
        
        
        recur(0, size, numbers); 
        
        
        return answer;
    }
    
    static void recur(int cnt, int n, int[] numbers){
        
        // 555 -> 560(+5) -> 600(+4) -> 1000(4) -> 1 이렇게 되어야함 
        
        int[] cloned = numbers.clone(); 
        
        if(n == 0) {

            if(numbers[0] <= 5){
                cnt += numbers[0]; 
            }else {
                // 10으로 만드는 경우 
                cnt += 10 - numbers[0] + 1; 
            }

            System.out.println("최종 -> " + cnt); 
            answer = Math.min(cnt, answer); 
            return; 
        }
        
        int now = numbers[n];
        
        if(now < 5){ 
                // 0으로 만들어주기 
                recur(cnt + now, n - 1, numbers); 
            }else if(now > 5){
                cloned[n-1] ++;
                recur(cnt + 10 - now, n - 1, cloned); 
            }else{
               cloned[n-1] ++;
               recur(cnt + 10 - now, n - 1, cloned);
               recur(cnt + now, n - 1, numbers); 
            }
    }
}
