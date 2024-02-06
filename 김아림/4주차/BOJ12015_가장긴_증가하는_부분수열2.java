import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12015_가장긴_증가하는_부분수열2 {
    static int N;
    static int[] arr;
    static int[] length;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        length = new int[N];
        Arrays.fill(length, 1); // 길이 모두 1로 초기화 하기 ~ 자기 자신은 무조건 포함하니까

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        /**
         * n * n 의 시간복잡도 ㅜ.ㅜ
         */

//        int max = 1;
//        int answer = 1;
//        for(int i = 0; i < N; i++){
//            // 현재 위치에서 자기 앞에 있는 애들을 기준으로 arr값은 자기보다 작고 length는 가장 큰 값 + 1 해주기
//            max = 0;
//            for(int j = 0; j < i; j++){
//                if(arr[i] > arr[j]){
//                    max = Math.max(length[j], max);
//                }
//            }
//            length[i] = max + 1;
//            // System.out.println(length[i]);
//            answer = Math.max(length[i], answer);
//        }


        /**
         * nlogn 의 시간복잡도
         */

        // 최장 길이
        int len = 0;

        for(int i = 0; i < N; i++){
                // length 배열에서 현재 최장 길이 수열이 담긴 length 배열의 0번 - len번째
                // -> 빈 곳말고 요소가 채워진 곳에서 새로운 요소가 들어갈만한 곳을 찾는 거임
                // arr[i]가 들어갈 수 있는 곳의 위치
                int index = Arrays.binarySearch(length, 0, len, arr[i]);

                if (index < 0) { // 음수 반환 -> 현재 없다는 뜻임
                    index = -(index + 1); // 해당 자리를 찾아서 대체함
                }

                // 해당 위치에 넣어주기
                length[index] = arr[i];

                // 만약에 제일 끝에 해당 요소가 들어갔다면 길이가 하나 늘었다는 뜻임
                if (index == len) {
                    len++;
                }
        }

        System.out.println(len);
    }
}
