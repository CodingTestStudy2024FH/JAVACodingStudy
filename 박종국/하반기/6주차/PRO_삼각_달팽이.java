import java.util.*;

class Solution {
    public int[] solution(int n) {
        
        if(n == 1) return new int[]{1};
        int x = 0, y = 0, cnt = 0;
        int limit = 0;
        for(int i = 1; i <= n; i++) limit += i;
        int[] answer = new int[limit];
        int[][] map = new int[n+1][n+1];
        while(cnt++ < limit){
            int checkRange = getRange(cnt, n);
            if(checkRange % 3 == 0){
                x--;
                y--;                
            }
            else if(checkRange % 3 == 2) y++;
            else if(checkRange % 3 == 1) x++;
            // System.out.println(getRange(cnt, n));
            map[x][y] = cnt;
            // System.out.println(x + " " + y);
        }
        int index = 0;
        for(int[] m: map){
            for(int k: m){
                if(k == 0) continue;
                answer[index] = k;
                index++;
            }
        }
        return answer;
    }
    private int getRange(int v, int n){
        int range = 0;
        int cnt = 0;
        // System.out.println("v= " + v);
        for(int i = n; i > 0; i--){
            range += i;
            cnt++;
            if(range >= v) break;
        }
        return cnt;
    }
}
