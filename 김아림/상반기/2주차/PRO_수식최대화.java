import java.util.*; 

class Solution {
    
    // 우선순위를 새롭게 재정의 함으로써 가장 큰 숫자를 만들어야함 
    // 음수이면 절댓값 
    
    static int[] tgt = new int[3]; 
    static boolean[] visit = new boolean[3]; 
    static String[] op = {"+", "-", "*"}; 
    static List<String> list = new ArrayList<>();
    static long answer = 0; 
    
    public long solution(String expression) {
          
        StringBuilder sb = new StringBuilder(); 
        
        for(int i = 0; i < expression.length(); i++){
            
            char now = expression.charAt(i); 
            
            if(now == '*'){
                list.add(sb.toString()); 
                sb = new StringBuilder(); // 비워주고 
                list.add(Character.toString(now)); // 연산자 넣어주기 
            }else if(now == '-'){
                list.add(sb.toString()); 
                sb = new StringBuilder(); // 비워주고 
                list.add(Character.toString(now)); // 연산자 넣어주기 
            }else if(now == '+'){
                list.add(sb.toString()); 
                sb = new StringBuilder(); // 비워주고 
                list.add(Character.toString(now)); // 연산자 넣어주기 
            }else {
                // 숫자라는 뜻
                sb.append(now); 
            }
            
        }
        
        if(sb.length() > 0) {
            list.add(sb.toString()); 
        }
        
        System.out.println(list); 
        // 일단 그냥 완탐으로 해보고 시간을 줄이자 ㅜ.ㅜ
        makePrior(0); 
    
        return answer;
    }
    
    // + - * 0, 1, 2 로 순열 만들기 
    static void makePrior(int n){
        
        if(n == 3) {
            calc(); 
            return; 
        }
    
        for(int i = 0; i < 3; i++){
            if(visit[i]) continue; 
            tgt[n] = i; 
            visit[i] = true; 
            makePrior(n+1); 
            visit[i] = false; 
        }
    }
    
    static void calc(){
        
        List<String> target_list = new ArrayList<>();
        for(String str : list){
            target_list.add(str); 
        }
        
        for(int q = 0; q < 3; q++) {
            int cur_exp = tgt[q]; 
            // System.out.println(cur_exp);
            int i = 0; 
            
            while( i < target_list.size()){
                
                if(target_list.get(i).equals(op[cur_exp])){
                    long left = Long.parseLong(target_list.get(i-1)); 
                    long right = Long.parseLong(target_list.get(i+1)); 
                    String cur_op = target_list.get(i); 
                    long result = 0; 
                    
                    if(cur_op.equals("*")){
                        result  = left * right ; 
                    }else if(cur_op.equals("+")){
                         result  = left + right ; 
                    }else if(cur_op.equals("-")){
                         result  = left - right ; 
                    }
                    
                    
                    // 연산자 자리에 덮어 씌우기 
                    target_list.set(i, Long.toString(result)); 
                    // 양 옆 제거하기 
                    target_list.remove(i+1); // 뒤에거 먼저 지워야 앞에거 타격이 없음
                    target_list.remove(i-1); 
                    // System.out.println(target_list); 
                    i--; // 만약에 0 1 2 3 4 5 -> 2 3 4 (i = 3) 였으면 다음은 5를 검사해야 
                    // 0 1 3 5 -> 3번째를 검사해야함 
                }
                i++; // 하나씩 앞으로 넘어가면서 검사하기 
            }
            
        }
        
            System.out.println(target_list); 
            
            long an = Long.parseLong(target_list.get(0)); 
            if(an < 0) an = an * (-1); 
            //System.out.println(an);
            answer = Math.max(an, answer); 
        
    }
    
    
}
