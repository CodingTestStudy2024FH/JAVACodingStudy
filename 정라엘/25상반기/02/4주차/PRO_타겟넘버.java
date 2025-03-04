class Solution {
    static int total, sum, t, answer=0;
    static int number[];
    public int solution(int[] numbers, int target) {
        total = numbers.length;
        
        number = numbers;
        t = target;
        
        Comb(0, 0);
        
        return answer;
    }
    
    static void Comb(int sum, int depth){
        if(depth == total){
            //숫자 비교
            if(sum == t)
                answer+=1;
            
            return;
        }
        
        //+ 선택
        Comb(sum+number[depth], depth+1);
        
        //미선택 (- 선택)
        Comb(sum-number[depth], depth+1);
    }
}
