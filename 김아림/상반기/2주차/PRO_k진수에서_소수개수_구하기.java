class Solution {
    // 211020101011 
    // 211까지 넣음 
    // 0을 만나는 순간 -> 211 검사하러감 -> sb비우고 left false 
    // 2 까지 넣음 
    // 0을 만나는 순간 -> 2 검사하러감 -> sb 비우고 left false 
    // 1
    // 11 
    public int solution(int n, int k) {
        int answer = 0;
        // 양의 정수 -> k진수 
        
        // k 의 범위는 3부터 10까지.. 
        String changed = Integer.toString(n, k);
        // 변환한 숫자는 스트링 
        System.out.println(changed);
        // 앞에서 하나씩 읽기엔 숫자가 너무 크다 
        // 0을 만났을때 
        
        // 일단 완탐으로 가되 
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < changed.length(); i++){
            char s = changed.charAt(i); 
            if(s == '0'){
              // sb가 비었는데 0을 만나는 경우 
             if(sb.length() != 0) { 
                 Long target = Long.parseLong(sb.toString()); 
                 if(isP(target)){
                     System.out.println(target);
                     answer++; 
                 }
             }
             sb.setLength(0);
            }else {
                // 0이 아닌 다른수를 만난 경우 
                String str = Character.toString(s); 
                sb.append(str);
            }
        }
        
        
        // 최종적으로 돌고 나서 남은 sb를 또 검사해줘야 
        // 여기서 sb!=null || 조건을 넣어줬는데 이거때문에 런타임 에러 ㅜ.ㅜ
        if(sb.length() != 0){
            long target = Long.parseLong(sb.toString()); 
            if(isP(target)){
                  answer++; 
             }
        }
        
        // 소수인지 체크하기 필요.. 
        // 해당 숫자 /2 까지 돌면서 i를 증가시키면서 
        // 해당 숫자로 나누어 떨어지는 경우 소수가 아님 
        
        
        return answer;
    }
    
    static boolean isP(long target){
        
        if(target < 2){
            return false;
        }
        
        // 여기를 바꾸니까 1번 테케 시간초과 통과 
        // 근데 12번은 계속 틀림 ㅜ.ㅜ 
        for(int i = 2; i <= Math.sqrt(target); i++){
            if(target % i == 0) {
                return false; 
            }
        }
        return true; 
        
    }
}
