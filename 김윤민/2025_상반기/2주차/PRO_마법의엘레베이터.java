public class PRO_마법의엘레베이터 {
    /*
        절대값이 10^c
        현재 층수 + 10^c 값 이동.
        값을 더한 결과가 0보다 작으면 엘베는 움직이지 않음.

        현재 0층에 있음.
        dp같은디 아닌가 그냥 앞자리부터 처리하는건가? 1 6 => 1 -> 10 2 -> 10 10

        기준 숫자 5
        5보다 크면 위에서 내려오는게 좋고,
        아니면 올라가는게 좋다.

        5라면 다음 숫자 고려.

        각 자리 숫자에서 층 처리 할 것? => 숫자분리
     */
    public static void main(String[] args) {
        System.out.println(solution(2554));
    }
    public static int solution(int storey) {
        int ans = 0;
        //아래부터 순차처리
        while(storey > 0) {
            int tmp = storey % 10; //남은 마지막 자리
            storey /= 10;


            //마지막 자리가 5인 경우는 앞의 숫자를 함께 따져줘야한다.
            if(tmp == 5) {
                if(storey % 10 >= 5) { //앞자리가 5이상이면
                    ans += 10 - tmp; // 자리수 0이 되도록 더해줌.
                    storey++; // 자리수 올라가고 뒷자리 0으로 만들어 줌.
                } else {
                    ans += tmp;
                }
            } else if(tmp > 5) { //5초과면 10에서 뺀만큼 이동
                ans += 10 - tmp;
                storey++;
            } else { //미만이면 그 수 만큼 더함
                ans += tmp;
            }
        }

        return ans;
    }
}
