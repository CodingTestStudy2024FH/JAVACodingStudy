import java.util.*;
 
public class Main {    
    
    static int l, r, c;
    static char[][][] building;
    static boolean[][][] visited;
    static int[] dl = {0, 0, 0, 0, 1, -1}; //순서대로 오 왼 위 아래 위층 아래층
    static int[] dr = {0, 0, -1, 1, 0, 0};
    static int[] dc = {1, -1, 0, 0, 0, 0};
    static Node s;
    static Node e;
 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        while(true) {
            l = scan.nextInt();
            r = scan.nextInt();
            c = scan.nextInt();
            scan.nextLine();
            
            if(l == 0 && r == 0 && c == 0) break;
            
            //빌딩 정보를 입력받는다.
            building = new char[l][r][c];
            for(int i = 0; i < l; i++) {
                for(int j = 0; j < r; j++) {
                    String temp = scan.nextLine();
                    for(int k = 0; k < c; k++) {
                        char c = temp.charAt(k);
                        building[i][j][k] = c;
                        if(c == 'S') { //시작 지점 입력
                            s = new Node(i, j, k, 0);
                        } else if(c == 'E') { //도착 지점 입력
                            e = new Node(i, j, k, 0);
                        }
                    }
                }
                scan.nextLine();
            }
            
            visited = new boolean[l][r][c];
            int result = bfs();
            if(result == -1) System.out.println("Trapped!");
            else System.out.println("Escaped in " + result + " minute(s).");
        }
    }    
    
    public static int bfs() {
        Queue<Node> q = new LinkedList<>();
        visited[s.l][s.r][s.c] = false;
        q.offer(new Node(s.l, s.r, s.c, 0));
        
        while(!q.isEmpty()) {
            Node current = q.poll();
            if(current.l == e.l && current.r == e.r && current.c == e.c) return current.count; 
            
            for(int i = 0; i < 6; i++) {
                int nl = current.l + dl[i];
                int nr = current.r + dr[i];
                int nc = current.c + dc[i];
                
                if(nl >= 0 && nl < l && nr >= 0 && nr < r && nc >= 0 && nc < c) {
                    if(visited[nl][nr][nc] == false && building[nl][nr][nc] != '#') {
                        visited[nl][nr][nc] = true;
                        q.offer(new Node(nl, nr, nc, current.count + 1));
                    }
                }
            }
        }
        return -1;
    }
    
    public static class Node {
        int l;
        int r;
        int c;
        int count;
        
        public Node(int l, int r, int c, int count) {
            this.l = l;
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }
}
