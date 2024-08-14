package PRO;

import java.util.Arrays;

public class PRO_순위 {
    /*
        실력이 좋은사람이 항상 이김.
        n, result
        정확하게 순위를 매길 수 있는 선수의 수.
        A,B = A가 B를 이겼다.

        전혀.. 감이 안오는데용?

        =====

        플로이드-와샬 알고리즘 = 모든 정점에서 다른 정점으로 최단거리 구함.
        모든 정점이 핵심
     */
    public int solution(int n, int[][] results) {
        int answer = n;
        int INF = n * n; //Integer.MAX는 연산에 문제생김. overflow
        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0; //초기값 세팅
        }

        for (int[] result : results) {
            graph[result[0] - 1][result[1] - 1] = 1;
        }

        for (int k = 0; k < n; k++) {  //i->j로 갈때 k를 거쳐서 갈 수 있는지 여부 확인.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    //i,j가 연결이 없더라도 i->k->j가 연결되있다면 순위를 알 수 있는 경웽 해당.
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                if (graph[i][j] == INF && graph[j][i] == INF) { //연결 되는지 확인하며 return
                    answer--;
                    break;
                }
            }
        }
        return answer;
    }
}
