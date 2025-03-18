package bfsdfs;

import java.util.ArrayDeque;
import java.util.Deque;

public class PRO_숫자_변환하기 {
    public static void main(String[] args) {
        int x = 10, y = 40, n = 30;
        Solution solution = new Solution();
        System.out.println(solution.solution(x, y, n));
    }

    static class Solution {
        static int LIMIT = 1_000_000;

        public int solution(int x, int y, int n) {
            int answer = 0;
            boolean[] visited = new boolean[LIMIT + 1];
            return getMinumumCount(x, y, n, visited);
        }

        static int getMinumumCount(int x, int y, int n, boolean[] visited) {
            Deque<Node> dq = new ArrayDeque<>();
            dq.addLast(new Node(x, 0));
            visited[x] = true;
            while (!dq.isEmpty()) {
                Node node = dq.pollFirst();
                if(node.value == y) return node.time;
                int plusN = node.value + n;
                int mult2 = node.value * 2;
                int mult3 = node.value * 3;
                if(isRange(plusN) && !visited[plusN]){
                    visited[plusN] = true;
                    dq.addLast(new Node(plusN, node.time+1));
                }
                if(isRange(mult2) && !visited[mult2]){
                    visited[mult2] = true;
                    dq.addLast(new Node(mult2, node.time+1));
                }
                if(isRange(mult3) && !visited[mult3]){
                    visited[mult3] = true;
                    dq.addLast(new Node(mult3, node.time+1));
                }
            }
            return -1;
        }
        static boolean isRange(int value){
            return value >= 0 && value <= LIMIT;
        }

        static class Node {
            int value, time;

            public Node(int value, int time) {
                this.value = value;
                this.time = time;
            }
        }
    }
}
