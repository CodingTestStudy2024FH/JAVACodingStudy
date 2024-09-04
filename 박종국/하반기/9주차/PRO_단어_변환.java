import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    Deque<Alphabet> dq;
    boolean[] visited;
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        return bfs(new Alphabet(begin, 0), target, words);

    }

    int bfs(Alphabet alpha, String target, String[] words){
        Deque<Alphabet> dq = new ArrayDeque<>();
        dq.addLast(alpha);
        while(!dq.isEmpty()){
            Alphabet current = dq.pollFirst();
            for(int i = 0; i < words.length; i++){
                String nextWord = words[i];
                if(!canSwitch(current.alpha, nextWord) || visited[i]) continue;
                if(nextWord.equals(target)) return current.time+1;
                visited[i] = true;
                dq.addLast(new Alphabet(nextWord, current.time+1));
            }
        }
        return 0;
    }

    boolean canSwitch(String begin, String word){
        int cnt = 0;
        for(int w = 0; w < word.length(); w++)
            if(begin.charAt(w) != word.charAt(w))
                cnt++;

        return cnt == 1;
    }

    class Alphabet{
        String alpha;
        int time;

        public Alphabet(String alpha, int time){
            this.alpha = alpha;
            this.time = time;
        }

        @Override
        public String toString(){
            return String.format("[%s, %d]", alpha, time);
        }
    }
}
