import java.util.*;
class Solution {
    // 순열을 사용하기 위한 자료구조
    // 우선순위고 뭐고 그냥 다하면 그게 우선순위였다...
    static char[] operators = {'-', '+', '*'};
    static char[] target = new char[3];
    static boolean[] chosen = new boolean[3];
    
    // 문자열을 파싱해서 숫자와 연산자를 분리
    static ArrayList<Long> numbers = new ArrayList<>();
    static ArrayList<Character> operations = new ArrayList<>();   
    
    static long answer;
    public long solution(String expression) {
        StringBuilder sb = new StringBuilder();
        
        // 문자열 파싱하는 과정
        for(int l = 0; l < expression.length(); l++){
            char chr = expression.charAt(l);
            if(Character.isDigit(chr)) sb.append(chr);
            else{
                numbers.add(Long.parseLong(sb.toString()));
                operations.add(chr);
                sb.setLength(0);
            }
        }
        
        // 마지막에 숫자까지 리스트에 삽입
        numbers.add(Long.parseLong(sb.toString()));
        
        // 순열 수행
        perm(0);
        return answer;
    }
    
    static void perm(int depth){
        if(depth == 3){
            // 우선순위를 다 찾으면 해당 우선순위에 대해서 값을 구하고 answer에 최대값 갱신 -> calc 함수에서 구현
            // 우선순위는 target 배열에 생성
            calc();
            return;
        }
        for(int i = 0; i < 3; i++){
            if(chosen[i]) continue;
            chosen[i] = true;
            target[depth] = operators[i];
            perm(depth+1);
            chosen[i] = false;
        }
    }
    
    static void calc(){
        // 결과값을 계산하는 과정에서 삽입과 삭제가 빈번하게 일어나기 때문에 원본은 보장하고
        // 해당 우선순위에 대해서 계산을 할 자료구조 생성
        ArrayList<Long> cNumbers =  (ArrayList<Long>) numbers.clone();
        ArrayList<Character> cOperations = (ArrayList<Character>) operations.clone();
        for(int i = 0; i < 3; i++){
            char chr = target[i];
            for(int j = 0; j < cOperations.size(); j++){
                if(chr == cOperations.get(j)){
                    // 우선순위에 해당되는 값부터 우선 계산
                    long r1 = cNumbers.get(j);
                    long r2 = cNumbers.get(j+1);
                    long result = getOperResult(r1, r2, chr);
                    
                    // 계산을 한 후 j+1부터 숫자를 삭제해야 인덱스 에러가 일어나지 않음
                    cNumbers.remove(j+1);
                    cNumbers.remove(j);
                    
                    // 연산자도 삭제
                    cOperations.remove(j);
                    // j 인덱스에 계산된 값을 넣어줌
                    cNumbers.add(j, result);
                    // 인덱스 범위를 맞추기 위해서 j를 감소
                    j--;
                }
            }
        }
        // 최대값 갱신
        answer = Math.max(answer, Math.abs(cNumbers.get(0)));
    }
    
    static long getOperResult(long v1, long v2, char oper){
        long result = 0;
        switch(oper){
            case '+': result = v1 + v2;
                break;
            case '-': result = v1 - v2;
                break;
            case '*': result = v1 * v2;
                break;
        }
        return result;
        
    }
    
}


