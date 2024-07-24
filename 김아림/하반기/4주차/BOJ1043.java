package week_4th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 거짓말
public class BOJ1043 {

    static int N, M;
    static int[] true_know;
    static List<Integer>[] person;

    // 사람을 기준으로 어디 파티에 소속되어있는지 확인
    // 이후에 진실을 아는 사람의 파티의 수는 빼고 나머지 카운트 해주면 될듯.. ?

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 전체 사람의 수
        M = Integer.parseInt(st.nextToken()); // 파티 수
        person = new List[N + 1]; // 파티
        Queue<Integer> q = new ArrayDeque<>(); // 진실을 들은 사람들
        boolean[] unavail = new boolean[N+1]; // 거짓을 못하는 파티

        // 진실을 아는 사람의 수 먼저
        st = new StringTokenizer(br.readLine());
        int number_know = Integer.parseInt(st.nextToken());
        true_know = new int[number_know];

        // 수만큼 돌면서 넣기 -> 0명이면 돌지 않음 !
        for (int i = 0; i < number_know; i++) {
            true_know[i] = Integer.parseInt(st.nextToken());
            q.offer(true_know[i]);
            unavail[true_know[i]] = true;
        }

        for (int i = 0; i <= N; i++) {
            person[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            // 오는 사람의 수만큼 돌면서 추가해주기
            for (int j = 0; j < number; j++) {
                int person_idx = Integer.parseInt(st.nextToken());
                person[person_idx].add(i); // 인덱스
            }
        }


        // 사람을 기준으로 어디 파티에 소속되어있는지 확인

        Set<Integer> party_set = new HashSet<>();

        while(!q.isEmpty()) {
            int n = q.poll(); // 진실을 아는 사람

            // 1이 나옴
            for (Integer ii : person[n]) {
//                System.out.println(ii + " 파티에서는 진실을 말할 수 없습니다. ");
                party_set.add(ii); // 진실을 아는 사람이 속하는 파티

                // ii 를 가지는 사람들을 q에 넣으면서 돌아야함
                for(int j = 1; j <= N; j++){
                    if(person[j].contains(ii)) {
                        // 이미 체크한 사람이면 제외해야함
//                        System.out.println(" 진실을 아는 " +j);
                        if(unavail[j]) continue;
//                        System.out.println(" 큐에 넣기 " + j);
                        q.offer(j);
                        unavail[j] = true;
                    }
                }
            }
        }


        // 진실을 아는 사람이 속하는 파티에 속하는 사람들이 있는 파티를 계속해서 걸러야함 ㅜㅁㅜ

        System.out.println(M - party_set.size());
    }
}
