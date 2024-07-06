import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//BOJ15903_카드합체놀이
public class BOJ15903_카드합체놀이 {

    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long answer = 0;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.offer((long)(Integer.parseInt(st.nextToken())));
        }

        // 기본적으로 생각했을땐, 가장 작은 두 수를 찾아서 그 수를 더하고 덮어 씌우면 될 거 같은데 .. ㅎ
        // 4 2 3 1 -> 4 3 3 3 -> 4 6 6 3 -> 19
        // 가장 작은 수를 찾는 것은 배열을 돌면 안됨 priority queue를 활용해보자
        for (int i = 0; i < m; i++) {
            long n1 = pq.poll();
            long n2 = pq.poll();
            long sum = n1 + n2;
            pq.offer(sum);
            pq.offer(sum);
        }

        while (!pq.isEmpty()) {
            answer += pq.poll();
        }

        System.out.println(answer);
    }
}
