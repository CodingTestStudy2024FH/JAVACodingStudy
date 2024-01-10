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
        	if(i==size-1) { //������ operand �־�����.
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
    //�� �̰� �밡�پ� ��? �ƴҰŰ����� => �����̳�
    // + - * ������ ������ ��������.
    public static void dfs(int cnt) {
    	if(cnt==3) {
    		//���� clone
    		ArrayList<Long> operState = (ArrayList<Long>)operandList.clone();
    		ArrayList<Character> expressState = (ArrayList<Character>)expressionList.clone();
    		
    		//������� ����
    		for(int i=0;i<3;i++) {
    			calc(operState,expressState,oper[i]);
    		}
    		//������ ���� operand�� �ϼ�.
    		Long ans = Math.abs(operState.get(0));
    		//max��
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
    	//������ �ں��� ��������, �׳� i�� �ٽ� ���ҽ����ָ� �ȴ�.
    	for(int i=0;i<expressState.size();i++) {
    		
    		char now = expressState.get(i);
    		if(now==nowE) {//�ش��ϸ�
    			switch(nowE) { //������ ����.
    			//���� + ���� ���ڸ� �ǿ�����
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
    			//������ ��..
    			expressState.remove(i--);
    			
    		}
    	}
    	return;
    }
}

