package week_4th;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ15663 {

    static int N, M;
    static int[] seq;
    static int src[];
    static boolean[] select;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<int[]> list;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        src = new int[N];
        select = new boolean[N];

        for(int i = 0; i< N; i++) {
            int e = sc.nextInt();
            src[i] = e;
        }

        Arrays.sort(src);
        // -- 입력 받기

        seq = new int[M];  // 수열 담는 곳
        list = new ArrayList<>();
        making(0);
        System.out.println(sb);
    }

    static void making(int cnt) {

        if(cnt == M) {

            boolean flag = false;
//			System.out.println("새로 만든 : "+Arrays.toString(seq));

            for(int[] l : list) {
                flag = false; // 못넣음 -> 동일한게 있다는 세팅
//				System.out.println("기존 : "+Arrays.toString(l));

                // 한 수열과 새로들어온 수열 비교하기
                for(int e = 0; e <l.length; e++) {
//					System.out.println("확인중~");
                    // 하나의 수열 기준으로 만약에 다르다 -> 넣을 수 있다 true
                    if(l[e] != seq[e]) { // 다른게 있으면
                        flag = true; // flag 넣을 수 있음을 의미
//						System.out.println(l[e] + "," + seq[e]);
                        break; // 하나라도 다르면 다른거임
                    }
                }

                if(flag) //다른 수열이었다면 또 다른 수열이랑 비교해봐야함
                    continue;
                // 하나의 수열을 돌고나서 만약에 수열이 같았다 -> false였다면
                if(!flag) { // 같았다고 하면 해당 수열을 넣어주면 안되므로 false
//					System.out.println("넣을 수 없음 ");
                    return; // 넣어주지 않음 -> 돌아가
                }
            }


            for(int i : seq) {
                sb.append(i + " ");
            }

            int[] n_arr = Arrays.copyOf(seq, seq.length);
            list.add(n_arr);
            sb.append("\n");
//			System.out.println("넣음 : " + list.size());

            return;
        }

        for(int i = 0; i< N; i++) {
            if(select[i]) continue;

            seq[cnt] = src[i];
            select[i] = true;
            making(cnt +1);
            select[i] = false;
        }
    }
}