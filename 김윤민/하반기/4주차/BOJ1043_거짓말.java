package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1043_거짓말 {
    /*
        진실 or 과장
        몇몇 사람은 진실을 안다. => 이런 사람이 오면 진실
        같은 이야기를 다르게 말했을때도 거짓말쟁이.
        
        거짓말쟁이로 알려지지 않으면서 과장된 이야기를 할 수있는 파티개수의 최댓값
        그냥 친구가 아닌 사람의 수로 세는게 좋지않나


        union & find 와 까맣게 잊고 있었다 지짜로,,ㅋㅋㅋ
        union find 응용

     */
    static int N,M;
    static int[] people;//진실을 아는 사람의 수
    static List<Integer> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        if (num==0){ //진실을 아무도 모르면 모든 파티에서 다 말할수 있다.
            System.out.println(M);
            return;
        }
        //진실을 아는사람들 저장.
        people = new int[N+1];
        for(int i=1; i<N+1; i++) {
            people[i] = i;
        }
        st = new StringTokenizer(br.readLine());
        int know= Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        if(know==0) { //아는 사람 없으면 바로 출력
            System.out.println(M);
            return;
        }
        else{ //아니면 아는 사람 추가
            for(int i=0; i<know; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
        }
        // 파티 쭈루룩 생성
        List<Integer>[] partyList = new ArrayList[M];
        for(int i=0; i<M; i++) {
            partyList[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int pn = Integer.parseInt(st.nextToken());

            int x = Integer.parseInt(st.nextToken());
            partyList[i].add(x);
            for(int j=1; j<pn; j++) {
                int y = Integer.parseInt(st.nextToken());
                union(x,y); //같은 무리로 소속시킴
                partyList[i].add(y);
            }
        }

        int cnt=0;
        for(int i=0; i<M; i++) {
            boolean flag = true;
            for(int z : partyList[i]) {
                if(list.contains(find(people[z]))) { //같은 파티에 갔었으면 = 아는사람이면 제외
                    flag= false;
                    break;
                }
            }
            if(flag) {
                cnt++;
            }
        }
        System.out.println(cnt);

    }

    //find
    static int find(int x) {
        if(people[x]==x) return x;
        return find(people[x]); //부모찾긩
    }

    static void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if(list.contains(ry)) { //아는 사람이면 묶기
            int tmp = rx;
            rx = ry;
            ry =tmp;
        }

        people[ry] = rx;
    }
}
