package bfsdfs;

import java.util.ArrayDeque;
import java.util.Deque;

public class PRO_게임_맵_최단거리 {
    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        Solution solution = new Solution();
        System.out.println(solution.solution(maps));
    }
    static class Solution {
        static int[] dy = {0, 0, -1, 1}, dx = {-1, 1, 0, 0};
        static int row, col;
        static boolean[][] visited;
        public int solution(int[][] maps) {
            row = maps.length;
            col = maps[0].length;
            visited = new boolean[row][col];
            return findShortestPath(maps);
        }

        int findShortestPath(int[][] maps){
            Deque<Node> dq = new ArrayDeque<>();
            dq.addLast(new Node(0, 0, 1));
            visited[0][0] = true;
            while(!dq.isEmpty()){
                Node node = dq.pollFirst();
                for(int d = 0; d < 4; d++){
                    int ny = node.y + dy[d];
                    int nx = node.x + dx[d];
                    if(ny == row-1 && nx == col-1) return node.time+1;
                    if(!isOk(ny, nx, maps)) continue;
                    dq.addLast(new Node(ny, nx, node.time+1));
                    visited[ny][nx] = true;
                }
            }
            return -1;
        }

        boolean isOk(int ny, int nx, int[][] maps){
            return ny < row && nx < col && ny >= 0 && nx >= 0 && !visited[ny][nx] && maps[ny][nx] == 1;
        }

        static class Node{
            int y, x, time;

            public Node(int y, int x, int time) {
                this.y = y;
                this.x = x;
                this.time = time;
            }

            @Override
            public String toString() {
                return String.format("(%d, %d, %d)", y, x, time);
            }
        }
    }
}
