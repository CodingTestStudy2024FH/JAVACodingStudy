import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class BOJ18869_멀티버스2 {

    static int M, N;
    static int[][] list;
    static int count = 0;
    static List<Integer>[] starList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        list = new int[M][N];
        starList = new ArrayList[M];

        // 값이 같은 애들은 하나로 쳐야함
        // set 집합으로 두고 정렬하고 리스트에 넣기
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            Set<Integer> set = new HashSet<>();
            for(int j =0; j < N; j++){
                int s = Integer.parseInt(st.nextToken());
                list[i][j] = s;
                set.add(s);
            }
            starList[i] = new ArrayList<>(set);
            Collections.sort(starList[i]);
        }

        // 좌표 압축하기
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                list[i][j] = Collections.binarySearch(starList[i], list[i][j]);
            }
        }

        int count = 0;
        for(int i = 0; i < M; i++){
            for(int j = i+1; j < M; j++){
                if(Arrays.equals(list[i], list[j]))
                    count++;
            }
        }
        System.out.println(count);

    }
}
