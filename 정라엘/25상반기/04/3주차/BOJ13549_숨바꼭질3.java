// 0-1 BFS는 처음 해본다
// 좋은 공부였음!
// Deque 사용해서 0인 간선은 앞, 1인 간선은 뒤로!
// -> 낮은 가중치부터 먼저 검색하게 됨 

import java.io.*;
import java.util.*;

public class BOJ13549_숨바꼭질3 {
    static final int MAX = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 수빈 위치
        int K = Integer.parseInt(input[1]); // 동생 위치

        int[] time = new int[MAX]; // 방문 시간 저장
        Arrays.fill(time, -1); // 방문하지 않은 위치는 -1로 초기화

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(N);
        time[N] = 0;

        while (!deque.isEmpty()) {
            int current = deque.poll();

            if (current == K) break;

            // 1. 순간이동 (0초)
            if (current * 2 < MAX && time[current * 2] == -1) {
                time[current * 2] = time[current];
                deque.offerFirst(current * 2); // 0초니까 앞에 삽입
            }

            // 2. -1 이동 (1초)
            if (current - 1 >= 0 && time[current - 1] == -1) {
                time[current - 1] = time[current] + 1;
                deque.offerLast(current - 1);
            }

            // 3. +1 이동 (1초)
            if (current + 1 < MAX && time[current + 1] == -1) {
                time[current + 1] = time[current] + 1;
                deque.offerLast(current + 1);
            }
        }

        System.out.println(time[K]);
    }
}
