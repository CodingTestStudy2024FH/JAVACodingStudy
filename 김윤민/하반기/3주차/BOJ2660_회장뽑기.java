package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
    가까운 정도에 따라 점수 받음
    1. 다른 모든회원과 친구 : 1점
    2. 2점 : 다른 모든회원이 친구 or 친구의 친구
    3. 3점 : 다른 모든 회원이 친구 or 친구의 친구 or 친구 친구 친구
    가까운걸 더 우선시 한다.

    회장은 회원들 중 가장 점수가 작은사람.
    점수와 회장이 될 사람은?
 */
public class BOJ2660_회장뽑기 {
    static int N,min;
    static ArrayList<ArrayList<Integer>> list;
    static ArrayList<Integer> candidate;
    public static void main(String[] args)  throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for(int i=0;i<=N;i++){
            list.add(new ArrayList<>()); //0은 텅 비어있음.
        }
        min = Integer.MAX_VALUE;
        candidate = new ArrayList<>();
        while(true){
            StringTokenizer st =new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if(A==-1) break;
            list.get(A).add(B);//서로 칭구
            list.get(B).add(A);
        }
        for(int i=1;i<=N;i++){
            int score = checkPoint(i);
            if(min>score){ //score가 더 작으면
                candidate = new ArrayList<>(); //초기화 하고
                candidate.add(i);//후보자 넣고
                min = score;//min수정
            }else if(min==score){//같으면
                candidate.add(i);//후보자 추가
            }
        }
        Collections.sort(candidate); //오름차순

        //출력
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(min).append(" ").append(candidate.size())
                .append("\n");
        for(Integer who:candidate){
            stringBuilder.append(who).append(" ");
        }
        System.out.println(stringBuilder);

    }
    public static int checkPoint(int who){
        //1. 일단 친구들 다 넣어.
        boolean[] visited = new boolean[N+1];
        visited[0]=true; //0은 의미없는 숫자
        visited[who]=true;//who는 나
        int score = Integer.MIN_VALUE;
        ArrayDeque<Fr> q = new ArrayDeque<>();
        for(Integer friend:list.get(who)){
            q.offer(new Fr(1,friend)); //친구들 일단 넣어
            visited[friend]=true;//그리고 이미 왔다고 체크
        }
        while(!q.isEmpty()){
            Fr now = q.poll();
            if(now.point>score){ //점수 갱신
                score= now.point;
            }
            for(Integer friend:list.get(now.num)){//친구들 또 불러옴
                if(visited[friend]) continue;//이미 친구면 넘기고
                //아니면 q에 넣음
                visited[friend]=true;
                q.offer(new Fr(now.point+1,friend));
            }
        }
        return score;
    }
    static class Fr{
        int point,num;

        public Fr(int point, int num) {
            this.point = point;
            this.num = num;
        }
    }

}
