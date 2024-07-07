import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.add(sc.nextLong());
        }

        for (int i = 0; i < m; i++) {
            long first = pq.poll();
            long second = pq.poll();
            long newCard = first + second;
            pq.add(newCard);
            pq.add(newCard);
        }

        long totalSum = 0;
        while (!pq.isEmpty()) {
            totalSum += pq.poll();
        }

        System.out.println(totalSum);
        sc.close();
    }
}
