package PRO;

import java.util.Arrays;

public class PRO_연속된_부분_수열의_합 {
    /*
        비내림차순으로 정렬된 수열이 주어짐. = 오름차순이라는 말임?
        1. 기존 수열에서 임의의 두 인덱스의 원소와 그 사이의 원소를 모두 포함하는 부분 수열
        2. 부분수열의 합은 k
        3. 합이 k인 부분수열이 여러개인 경우 길이가 짧은것
        4. 여러개인경우 앞쪽에 나오는 수열을 찾음(인덱스가 작은)

        시작인덱스와 마지막 인덱스 배열에 담아 return

        투포인터..??
         st, ed로 있으면 다 더하고 그거보다 ed를 늘리고 크면 st를 이동
         같으면 길이 비교
     */
    public static void main(String[] args) {
        int[] sequence = {1, 2, 3, 4, 5};
        int k =7;
        System.out.println(Arrays.toString(solution(sequence,k)));

    }
    public static int[] solution(int[] sequence, int k) {
        int[] ans = new int[2];

        int st = 0; //시작
        int ed = 1; //끝

        int sum = sequence[0]; // st 포함하고 시작

        while(st<ed){
            if(sum==k){//목표와 같으면
                if(ed-st-1<ans[1]-ans[0]){ //길이 비교 ( ed 이전까지만 세고있으니까 -1 해줌)
                    ans[0]=st;
                    ans[1]=ed-1;
                }
                sum -= sequence[st]; //출발지 빼고
                st++;//왼쪽 이동
            }else if(sum>k){ //sum이 더크면 st이동
                sum -= sequence[st];
                st++;
            }else if(ed<sequence.length){ //작은데 여전히 범위 내면 ed이동
                sum+= sequence[ed];
                ed++;//오른쪽으로 이동
            }else{
                break;
            }
        }

        return ans;
    }
}
