import java.util.*;

public class BOJ1111_IQTest {

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        if (N == 1) {
            System.out.println("A");
        } else if (N == 2) {
            if (A[0] == A[1]) {
                System.out.println(A[0]);
            } else {
                System.out.println("A");
            }
        } else {
            // a, b 구하기 : A[n] = A[n-1] * a + b
            boolean valid = true;
            int a, b;

            // 방정식: A1 * a + b = A2, A2 * a + b = A3
            // a = (A2 - A3) / (A1 - A2)  => 분모 0 주의
            int bunmo = A[1] - A[0];
            int num = A[2] - A[1];

            if (bunmo == 0) {
                a = 0;
                b = A[1];
            } else {
                if (num % bunmo != 0) {
                    System.out.println("B");
                    return;
                }
                a = num / bunmo;
                b = A[1] - A[0] * a;
            }

            for (int i = 1; i < N; i++) {
                if (A[i] != A[i - 1] * a + b) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                System.out.println(A[N - 1] * a + b);
            } else {
                System.out.println("B");
            }
        }
    }
}

//도저히 감이 안 잡혀서 인터넷 참고함...
