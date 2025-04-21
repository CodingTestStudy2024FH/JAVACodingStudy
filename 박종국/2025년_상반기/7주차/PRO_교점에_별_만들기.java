package math;

import java.util.ArrayList;
import java.util.Arrays;

public class PRO_교점에_별_만들기 {
    public static void main(String[] args) {
        int[][] line = {{2,-1,4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}};
        System.out.println(Arrays.deepToString(new Solution().solution(line)));
    }
    static class Solution {
        // 최대 범위는 1,000 * 1,000 범위 이내 표현됌
        static long maxY = Long.MIN_VALUE, maxX = Long.MIN_VALUE, minY = Long.MAX_VALUE, minX = Long.MAX_VALUE;
        public String[] solution(int[][] line) {
            String[] answer = {};
            ArrayList<Node> nodes = new ArrayList<>();

            for(int i = 0; i < line.length; i++) {
                for(int j = i+1; j < line.length; j++) {
                    Node node = setPoint(line[i], line[j]);
                    if(node == null) continue;
                    nodes.add(node);
                    setInitialValue(node);
                }
            }
            int lenY = (int) (maxY - minY + 1);
            int lenX = (int) (maxX - minX + 1);

            char[][] chars = new char[lenY][lenX];
            for(char[] ch : chars) Arrays.fill(ch, '.');

            return drawGraph(chars, nodes);
        }

        public void setInitialValue(Node node){
            maxY = Math.max(node.y, maxY);
            maxX = Math.max(node.x, maxX);
            minY = Math.min(node.y, minY);
            minX = Math.min(node.x, minX);
        }

        public String[] drawGraph(char[][] graph, ArrayList<Node> nodes) {
            for(Node node : nodes){
                int y = (int) (maxY - node.y);
                int x = (int) (node.x - minX);

                graph[y][x] = '*';
            }
            return Arrays.stream(graph)
                    .map(String::new)
                    .toArray(String[]::new);
        }

        public Node setPoint(int[] curr, int[] next){
            long a = curr[0], c = next[0];
            long b = curr[1], d = next[1];
            long e = curr[2], f = next[2];

            double y = (double) (e * c - a * f) / (a * d - b * c);
            double x = (double) (b * f - e * d) / (a * d - b * c);
            if(y % 1 != 0 || x % 1 != 0) return null;

            return new Node((long) y, (long) x);
        }

        static class Node{
            long y, x;

            public Node(long y, long x) {
                this.y = y;
                this.x = x;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "y=" + y +
                        ", x=" + x +
                        '}';
            }
        }
    }
}


