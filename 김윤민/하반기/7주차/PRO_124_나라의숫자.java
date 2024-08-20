package PRO;

public class PRO_124_나라의숫자 {
    /*
        모든 수를 표현할 때 124만 사용.
        n을 124나라에서
        1 1
        2 2
        3 4

        3으로 나눈것의 나머지로 결정
        1%3 = 1
        2%3 = 2
        3%3 = 0 => 4
        단순히 124 반복

        1
        2
        4

        1x
        11
        12
        14

        2x
        21
        22
        24

        3x
        31
        32
        34
     */
    public static void main(String[] args) {

    }
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            int remainder = n%3;//나머지 구하기
            if(remainder == 0) { //나머지가 0이면 4
                sb.append(4);
            } else if(remainder == 1) { //1이면 1
                sb.append(1);
            } else { //2 면 2
                sb.append(2);
            }

            if(remainder == 0)  { //나머지를 구하는 과정에서 나머지가 0일때 나눠지는수에서 -1해야함.=> 6의 경우.
                n -= 1;
            }

            n /= 3;
        }

        return sb.reverse().toString();
    }
}
