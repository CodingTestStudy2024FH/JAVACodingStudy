package level2;

import java.util.ArrayList;
import java.util.Arrays;

public class N67257 {

	public static void main(String[] args) {
		
		System.out.println(solution("50*6-3*2"));
	}
	public static ArrayList<Character> expressionList;
    public static ArrayList<Long> operandList;
    public static long solution(String expression) {
        expressionList = new ArrayList<>();
        operandList = new ArrayList<>();
        char[] expressionChar = expression.toCharArray();
        int size = expression.length();
        int prev = 0;
        
        
        for(int i=0;i<size;i++) {
        	char now = expressionChar[i];
        	if(now=='+'||now=='-'||now=='*') {
        		operandList.add(Long.parseLong(expression.substring(prev,i)));
        		prev = i+1;
        		expressionList.add(now);
        	}
        	if(i==size-1) { //마지막 operand 넣어주자.
        		operandList.add(Long.parseLong(expression.substring(prev, size)));
        	}
        }
        
        visited = new boolean[3];
        oper = new char[3];
        max = Long.MIN_VALUE;
        dfs(0);
        return max;
    }
    static boolean[] visited;
    static char[] oper;
    static char[] template = {'-','+','*'};
    static Long max=0L;
    //엥 이거 노가다야 ㅜ? 아닐거같은데 => 순열이네
    // + - * 순서는 순열로 정해진다.
    public static void dfs(int cnt) {
    	if(cnt==3) {
    		//연산 clone
    		ArrayList<Long> operState = (ArrayList<Long>)operandList.clone();
    		ArrayList<Character> expressState = (ArrayList<Character>)expressionList.clone();
    		
    		//순서대로 연산
    		for(int i=0;i<3;i++) {
    			calc(operState,expressState,oper[i]);
    		}
    		//마지막 남은 operand가 완성.
    		Long ans = Math.abs(operState.get(0));
    		//max비교
    		max = Math.max(ans,max);
    		return;
    	}
    	for(int i=0;i<3;i++) {
    		if(visited[i]) continue;
    		visited[i] =true;
    		oper[cnt]=template[i];
    		dfs(cnt+1);
    		visited[i]=false;
    	}
    	
    	
    }
    public static void calc(ArrayList<Long> operState,ArrayList<Character> expressState,char nowE) {
    	//순서를 뒤부터 하지말고, 그냥 i를 다시 감소시켜주면 된다.
    	for(int i=0;i<expressState.size();i++) {
    		
    		char now = expressState.get(i);
    		if(now==nowE) {//해당하면
    			switch(nowE) { //계산부터 해줌.
    			//본인 + 본인 뒷자리 피연산자
    			case '*':
    				operState.add(i,operState.remove(i)*operState.remove(i));
    				break;
    			case '-':
    				operState.add(i,operState.remove(i)-operState.remove(i));
    				break;
    			case '+':
    				operState.add(i,operState.remove(i)+operState.remove(i));
    				break;
    			}
    			//연산자 빼..
    			expressState.remove(i--);
    			
    		}
    	}
    	return;
    }
}

