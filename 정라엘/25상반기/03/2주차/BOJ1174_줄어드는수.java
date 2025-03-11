import java.util.*;

public class Main {
    static ArrayList<Long> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // 줄어드는 수를 모두 생성
        for (int i = 0; i < 10; i++) {
            genNums(i, i);
        }

        // 정렬
        Collections.sort(list);

        // N번째 수 출력
        if (N > list.size()) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(N - 1));
        }
    }

    // 줄어드는 수 생성
    static void genNums(long num, int lastDigit) {
        list.add(num);

        // 다음 자리 숫자 선택 (현재 숫자보다 작은 값만)
        for (int next = 0; next < lastDigit; next++) {
            genNums(num * 10 + next, next);
        }
    }
}
