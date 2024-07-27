package PRO;

import java.lang.reflect.Array;
import java.util.Arrays;

public class PRO_이모티콘_할인행사 {
    /*
        옛날에 풀었던거같은데
        이모티콘마다 할인율 다름. 10,20,30,40

     */
    static int[] RATE = {90, 80, 70, 60};
    private static int EMOTICON_PLUS = 0;
    private static int TOTAL_SALES = 0;

    public static void main(String[] args) {
        int[][] users = {{40, 10000}, {25, 10000}};
        int[] emoticons={7000, 9000};
        System.out.println(Arrays.toString(solution(users,emoticons)));
    }

    public static int[] solution(int[][] users, int[] emoticons) {
        dfs(emoticons, users, 0, new int[emoticons.length]);
        return new int[]{EMOTICON_PLUS, TOTAL_SALES};
    }
    //dfs
    static void dfs(int[] emoticons, int[][] users, int cur, int[] rates) {
        if (cur == emoticons.length) {
            updateAnswer(emoticons, users, rates);
            return;
        }

        for (int rate : RATE) {
            rates[cur] = rate;
            dfs(emoticons, users, cur + 1, rates);
        }
    }

    static void updateAnswer(int[] emoticons, int[][] users, int[] rates) {
        int ePlus = 0;
        int totalExpense = 0;

        for (int[] user : users) {
            int expense = 0;
            int rate = user[0];
            int price = user[1];
            for (int i = 0; i < rates.length; i++) {
                if (100 - rates[i] >= rate) {
                    expense += emoticons[i] * rates[i] / 100;
                }
                if (expense >= price) {
                    ePlus += 1;
                    expense = 0;
                    break;
                }
            }
            totalExpense += expense;
        }

        if (ePlus > EMOTICON_PLUS) {
            EMOTICON_PLUS = ePlus;
            TOTAL_SALES = totalExpense;
        } else if (ePlus == EMOTICON_PLUS) {
            TOTAL_SALES = Math.max(totalExpense, TOTAL_SALES);
        }
    }
}
