package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11650_좌표_정렬하기 {
    static StringTokenizer st;
    static int N;
    static int[][] array;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][2];
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            array[n][0] = Integer.parseInt(st.nextToken());
            array[n][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(array, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        for(int[] arr : array) {
            for(int num : arr) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
