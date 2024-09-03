import java.io.*;
import java.util.*;

class Solution {
    static String word[], t;
    static boolean visit[];
    static int answer=Integer.MAX_VALUE, len=0;
    
    public int solution(String begin, String target, String[] words) {
        
        word = words;
        len = word.length;
        t = target;
        visit = new boolean[len];
        
        bfs(begin, 0);
        
        if(answer == Integer.MAX_VALUE)
            answer = 0;
        
        return answer;
    }
    
    static void bfs(String begin, int depth){
        // Word 큐 정의
        Queue<Word> q = new LinkedList<>();
        q.add(new Word(begin, depth, -1));  //idx가 없으므로 -1
        
        
        while(!q.isEmpty()){
            // 큐 꺼내서(방문 체크), 
            // 하나 다른 애들 있는지 확인 후 추가(depth+1)
            Word curr = q.poll();
            if(curr.idx != -1)
                visit[curr.idx] = true;
            
            // 종료 조건
            if(t.equals(curr.word)){
                if(curr.depth < answer){
                    answer = curr.depth;
                }
                return;
            }
            
            for(int i=0; i<len; i++){
                if(!visit[i] && isOneDiff(curr.word, word[i])){
                    q.add(new Word(word[i], curr.depth+1, i));
                }
            }   
        }
        
    }
    
    // 하나만 다른 단어인지 확인
    static boolean isOneDiff(String w1, String w2){
        int cnt = 0;
        
        for(int i=0; i<w1.length(); i++){
            // 다르면 카운트 증가
            if(w1.charAt(i) != w2.charAt(i)){
                cnt++;
            }
        }
        
        if(cnt == 1)
            return true;
        return false;
    }
    
    // 큐 클래스 정의
    public static class Word{
        String word;
        int depth;
        int idx;
        
        public Word(String word, int depth, int idx) {
            this.word = word;
            this.depth = depth;
            this.idx = idx;
        }
    }
}
