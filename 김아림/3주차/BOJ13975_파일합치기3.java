import java.util.*;
import java.io.*;

public class BOJ13975_파일합치기3 {

    static int TC;
    static int count;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(br.readLine());

        /**
         *  두개의 파일을 합쳐서 하나의 임시파일
         *  임시파일, 원래의 파일을 두개씩 합쳐서 파일 합치기
         *  합 -> 두개의 크기의 합
         *  최종 한개의 파일 완성하는데 필요한 비용 총합 ?
         */


        for(int t = 0; t < TC; t++){
            count = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> q = new PriorityQueue<>();
            for(int i = 0; i < count; i++){
                Long input = Long.parseLong(st.nextToken());
                q.offer(input);
            }

            Long weight = 0L;
            while(q.size() != 1){
                Long n1 = q.poll();
                Long n2 = q.poll();

                Long sum = n1 + n2;
                System.out.println(n1 + " + " + n2 + " = " + sum);
                q.offer(sum);
                weight+= sum;
            }

            System.out.println(weight);
        }
    }
}
