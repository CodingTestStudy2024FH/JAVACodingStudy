package simulation;

import java.util.ArrayList;
import java.util.LinkedList;

public class PRO_수식_최대화 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String expression = "100-200*300-500+20";
        System.out.println(solution.solution(expression));

    }
    static class Solution{
        static char[] justOperator = {'-', '*', '+'};
        static LinkedList<Long> numbers = new LinkedList<>();
        static LinkedList<Character> operators = new LinkedList<>();
        static char[] target = new char[3];
        static boolean[] chosen = new boolean[3];
        static long answer;
        public long solution(String expression){
//            StringTokenizer st = new StringTokenizer(expression, "+-*");
            StringBuilder sb = new StringBuilder();
            for(int s = 0; s < expression.length(); s++){
                char ch = expression.charAt(s);
                if(Character.isDigit(ch)) sb.append(ch);
                else {
                    operators.add(ch);
                    numbers.add(Long.parseLong(sb.toString()));
                    sb.setLength(0);
                }
            }
            numbers.add(Long.parseLong(sb.toString()));
            perm(0);
            return answer;
        }
        static void perm(int depth){
            if(depth == 3){
                calculate();
                return;
            }
            for(int i = 0; i < 3; i++){
                if(chosen[i]) continue;
                chosen[i] = true;
                target[depth] = justOperator[i];
                perm(depth+1);
                chosen[i] = false;
            }
        }

        static void calculate(){
            ArrayList<Character> inOperators = new ArrayList<>(operators);
            ArrayList<Long> inNumbers = new ArrayList<>(numbers);

            for(char priorityOperator: target){
                for(int index = 0; index < inOperators.size(); index++){
                    char operator = inOperators.get(index);
                    if(operator != priorityOperator) continue;
                    long v1 = inNumbers.get(index);
                    long v2 = inNumbers.get(index+1);
                    long value = getValue(v1, v2, operator);

                    inNumbers.remove(index+1);
                    inNumbers.remove(index);
                    inNumbers.add(index, value);

                    inOperators.remove(index);

                    index--;
                }
            }
            answer = Math.max(answer, Math.abs(inNumbers.get(0)));
        }

        static long getValue(long v1, long v2, char operator){
            return switch (operator) {
                case '+' -> v1 + v2;
                case '-' -> v1 - v2;
                case '*' -> v1 * v2;
                default -> 0;
            };
        }
    }
}
