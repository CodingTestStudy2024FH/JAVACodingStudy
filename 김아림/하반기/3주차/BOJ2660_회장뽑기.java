package week_3rd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2660_회장뽑기 {

    static int N;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new boolean[N + 1][N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int i1 = Integer.parseInt(st.nextToken());
        int i2 = Integer.parseInt(st.nextToken());


        while (i1 != -1 && i2 != -1) {
            // 양방향
            map[i1][i2] = true;
            map[i2][i1] = true;

            st = new StringTokenizer(br.readLine());
            i1 = Integer.parseInt(st.nextToken());
            i2 = Integer.parseInt(st.nextToken());

        }

        int[] count = new int[N+1];


        // 친구 찾기
        for(int i = 1; i <= N; i++){

            int cur = i;

            boolean[] friends = new boolean[N+1];
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(cur);
            friends[cur] = true;
            int depth = 0;
            int friends_count = N-1;

            while (!q.isEmpty()) {
                depth++;
                int size = q.size();

                for(int l = 0; l < size; l++) {

                    int now = q.poll();

                    for(int j = 1; j <= N; j++){
                        if(now == j) continue; // 자기 자신은 노상관

                        if(!friends[j] && map[now][j]) {
                            friends[j] = true;
                            friends_count--;
                            q.offer(j);
                        }
                    }

                }
                if(friends_count == 0) break;

            }

            count[cur] = depth;

        }

        // System.out.println(Arrays.toString(count));

        int min = 51;
        for(int i = 1; i <= N; i++){
            min = Math.min(min, count[i]);
        }

        int answer_count = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            if(min == count[i]) {
                answer_count++;
                sb.append(i).append(" ");
            }
        }

        System.out.println(min + " " + answer_count);
        System.out.println(sb);

    }
}
