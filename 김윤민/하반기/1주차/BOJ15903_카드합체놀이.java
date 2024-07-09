package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ15903_카드합체놀이 {
    /*
        아기 석환이.
        
        자연수 n 장 
        i 번 카드에 ai
        
        1. x번 카드와 y번 카드를 골라 두장에 쓰여진 수를 더한값을 계산한다.
        2. 계산한값이 x,y번 카드의 값이 된다.
        
        총 m번하면 끝남. => 전체 더한게 놀이 점수
        점수를 가장 작게 만드는것이 목표

        카드의 순서는 중요하지 않을듯???? => 작은 거 앞에 둬서 그냥 더하기하고 뒤에 두개 넣기
        => 큐 사용

        ??이게끝?
     */
    static int N,M;
    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> q = new PriorityQueue<>(); //쯰밤..Long
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            q.offer(Long.parseLong(st.nextToken()));
        }
        for(int i=0;i<M;i++){
            Long tmp = (q.poll()+q.poll()); // 더하고
            q.offer(tmp); // 다시 넣고 ( 2번 )
            q.offer(tmp);
        }
        Long ans = 0L;
        while(!q.isEmpty()){
            ans+= q.poll(); //다 더하기
        }
        System.out.println(ans);
    }
}
