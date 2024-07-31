package PRO;

public class PRO_큰_수_만들기 {
    /*
        k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자
        문자열 형태로 return

        sb써야겠는디'

        모르겠는데요 ㅠㅠ

        => 탐욕 알고리즘을 사용한다고 함.
     */
    public static void main(String[] args) {

    }
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();

        int idx=0;//index는 0부터 시작
        for(int i=0;i<number.length()-k;i++){//문자 길이에서 k개수 제거한 길이만큼 반복
            int max = 0;
            for(int j=idx; j<=i+k; j++){ //idx~k까지 반복하면서 max값을 찾는다.
                if(max < number.charAt(j)-'0'){//max값 갱신시 j다음부터 탐색시작
                    max = number.charAt(j)-'0';//따라서j+1로
                    idx = j+1;
                }
            }
            sb.append(max);//max값 숫자 붙이기
        }

        return sb.toString();
    }
    }
}
