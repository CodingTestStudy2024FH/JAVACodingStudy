package programmers;

public class PRO_타겟넘버 {
    /*
        순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버

     */
    public static void main(String[] args) {
        int[] numbers = {4,1,2,1};
        int target= 4;
        System.out.println(solution(numbers,target));
    }
    static int length;
    static int output;
    public static int solution(int[] numbers, int target) {
        length= numbers.length;
        dfs(0, target, 0, numbers);

        return output;
    }
    public static void dfs(int idx,int target,int sum,int[] numbers){
        if(idx==length){
            if(target==sum){
                output++;
            }
            return;
        }
        //더하면
        dfs(idx+1,target,sum+numbers[idx],numbers);
        //빼면
        dfs(idx+1,target,sum-numbers[idx],numbers);
    }

}
