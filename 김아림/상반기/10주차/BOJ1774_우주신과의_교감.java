import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1774_우주신과의_교감 {

    /**
     * 새로운 우주신들은 그 우주신들을 거쳐서 황선자 씨와 교감
     * 통로들의 길이는 2차원 좌표계상의 거리
     * 합이 최소가 되게 통로를 만들어
     */

    static int N, M;
    static Point[] gods; // 신들의 위치 배열
    static List<Edge> edgeList = new ArrayList<>();

    static class Point{
        int y, x;
        Point(int y, int x){
            this.y =y;
            this.x =x;
        }
    }

    static class Edge{
        int v1, v2;
        double weigth;
        Edge(int v1, int v2, double weigth){
            this.v1 = v1;
            this.v2 = v2;
            this.weigth = weigth;
        }

    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        gods = new Point[N + 1];
        parent = new int[N + 1];

        int edgeCnt = 0; // N -1 개가 되면 됨
        double answer = 0;
        init();


        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            gods[i] = new Point(y, x);
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            if(union(v1, v2)) edgeCnt++;
        }

        // 신끼리 이은 간선 모두 계산해보기
        for(int i = 1; i < N; i++){
            for (int j = i + 1 ; j <= N; j++){
                Point v1 = gods[i];
                Point v2 = gods[j];
                double edgeWeigth = getDistance(v1, v2);
                edgeList.add(new Edge(i, j, edgeWeigth));
            }
        }

        Collections.sort(edgeList, (o1, o2) -> Double.compare(o1.weigth, o2.weigth));

        for(Edge e : edgeList){
            //System.out.println(e.v1 + " ->" + e.v2);
            //System.out.println(e.weigth);

            if(union(e.v1, e.v2)) {
                edgeCnt ++;
                answer += e.weigth;
            }

        }

        System.out.printf("%.2f",answer);

    }

    static void init() {
        for(int i = 1; i < N + 1; i++){
            parent[i] =i;
        }
    }

    static boolean union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);
        if(root1 == root2) return false;

        parent[root1] = root2;
        return true;
    }

    static int find(int v1){
        if(parent[v1] == v1) return v1;
        return parent[v1] = find(parent[v1]);
    }
    static double getDistance(Point p1, Point p2){
        
        double distance = 0;

        double v1 = Math.pow(p1.x - p2.x, 2);
        double v2 = Math.pow(p1.y - p2.y, 2);
        distance = Math.sqrt(v1 + v2);

        return distance;
    }


}
