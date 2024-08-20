package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ1987_알파벳 {
    // RxC
    // 각칸에는 알파벳, 1행1열에는 말

    // 네칸중 한칸 위아래왼오
    // 같은 알파벳 적힌 칸 두번 못감
    // 말이 최대한 몇칸을 지날 수 있나?
    // 첫 칸도 count
    static int[] dy = { 1, -1, 0, 0 }; // 상하좌우 이동
    static int[] dx = { 0, 0, 1, -1 };
    static int r, c, cnt, max = Integer.MIN_VALUE;
    static char[][] map;
    static boolean[][] visit; // 위치는 왔는지 저장
    static Set<Character> already; // 이미 왔던 친구들 저장
    static boolean[] alphabet = new boolean[26]; //dfs2
    public static void main(String[] args) throws Exception {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        visit = new boolean[r][c];
        map = new char[r][c];
        already = new HashSet<>();
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        // input end

        // logic
        // dfs로 추적하면서, set에 넣었다 뺐다 진행...,.,.
        dfs2(0, 0, 1); // 첫 요소도 포함이니까 cnt=1로 시작
        System.out.println(max);

    }
//    // 정답은 되나 다른 사람에 비해 시간이 오래걸림
//    // 이유를 찾아봤더니, 아마 Set을 이용한 연산에서 시간이 오래걸리는듯?
//    // 따라서 다음 dfs2에서는 Set을 사용하지 않고, boolean배열 사용
//    // 알파벳 총 26개 -> 26개의 boolean배열.
//    public static void dfs(int y, int x, int cnt) {
//        visit[y][x] = true; // 왔으면 true
//        already.add(map[y][x]); // 여기에 들어왔다는건 중복이 되지 않았다는 의미. -> set에 추가.
//        max = Math.max(cnt, max); // max 값 갱신
//        for (int i = 0; i < 4; i++) {
//            int ny = y + dy[i]; //방항이동 하면서
//            int nx = x + dx[i];
//            if (can(ny, nx)) { //범위 내이고, 방문하지 않았고
//                if (!already.contains(map[ny][nx])) { //그리고 이미 접근했던 알파벳이 아니라면
//                    dfs(ny, nx, cnt + 1); // cnt+1증가한채로 재귀
//                    visit[ny][nx] = false; // 빠져나왔을 때는 다른 경로로도 dfs돌려야 하므로 왔다는거 체크 해제.
//                    already.remove(map[ny][nx]); // set에서도 제거.
//                }
//            }
//        }
//    }
    public static void dfs2(int y, int x, int cnt) {
        visit[y][x] = true; // 왔으면 true
        alphabet[map[y][x]-'A'] =true;
        max = Math.max(cnt, max); // max 값 갱신
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i]; //방항이동 하면서
            int nx = x + dx[i];
            if (can(ny, nx)) { //범위 내이고, 방문하지 않았고
                if (!alphabet[map[ny][nx]-'A']) { //그리고 이미 접근했던 알파벳이 아니라면
                    dfs2(ny, nx, cnt + 1); // cnt+1증가한채로 재귀
                    visit[ny][nx] = false; // 빠져나왔을 때는 다른 경로로도 dfs돌려야 하므로 왔다는거 체크 해제.
                    alphabet[map[ny][nx]-'A'] =false;
                }
            }
        }
    }
    public static boolean can(int y, int x) {
        if (y >= 0 && x >= 0 && y < r && x < c && visit[y][x] == false) {
            return true;
        }
        return false;
    }
}
