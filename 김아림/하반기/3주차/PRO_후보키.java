package week_3rd;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PRO_후보키 {

    // 사실 정답률 88퍼... ㅎ
    // 애들 거 보고 참고해서 고쳐야지예

}

class Solution {
    static List<String> answer_list;
    static int answer = 0;

    public int solution(String[][] relation) {
        // 후보키 유일성, 최소성 을 의미
        // 후보키의 최대 개수 구하기

        // 서로 겹치는게 없어야함..
        // 칼럼 개수가 8개 뿐이라서 다 해봐도 될거 같음 -> 부분 집합에 대한 모든 경우
        int[] set = new int[relation[0].length]; // 키의 개수만큼
        boolean[] visit = new boolean[relation[0].length];
        answer_list = new ArrayList<>();
        sub(visit, 0, set.length, relation);
        return answer;
    }

    // 순서를 반대로 해야
    static void sub(boolean[] visit, int idx, int n, String[][] relation){
        if(idx == n){
            // 다 거침
            // 여기서 유일성, 최소성 확안해야...
            ArrayList<Integer> list = new ArrayList<>();
            StringBuilder ans = new StringBuilder();
            for(int i = 0; i < visit.length; i++){
                if(visit[i]){
                    list.add(i);
                    ans.append(i);
                }
            }
            boolean flag = true;

            for (String answer_key : answer_list) {
                if (ans.toString().contains(answer_key)) {
                    flag = false;
                    break;
                }
            }

            char[] keys_e = ans.toString().toCharArray();
            // 현재 정답으로 염두에 두는 index집합 -> answerList의 하나의 집합에 포함되면 안됨
            // 현재 넣으려고 하는거 ans 이미 정답인거 answr_list내 answer_key
            // 현재 넣으려고 하는 ans의 원소들이 answr_list내에 들어있는지 체크하기
            // ans안에 있는걸 하나씩 꺼내서 answer_key에 있는지 체크하는데 그때
            for (String answer_key : answer_list) {
                // answer_key 가 02일 경우
                // 현재 넣으려는 답은 012
                // 0, 0 -> index_key = 0, keys_e[i] = 0
                // 2 1 다름 -> index_key = 1(2), keys_e[i] = 1 cnt-> 2-1 => 1
                // 2 2 같음  -> index_key = 1(2), keys_e[i] = 2 cnt -> 1-1 = 0;
                int index_key = 0;
                int cnt = answer_key.length();  // 2
                // 하나의 키를 가지고 돌면서
                // keys_e가 더 길다
                for(int i = 0; i < keys_e.length; i++) {

                    if(index_key > cnt) {

                        break;
                    }
                    // ====================================
                    // i는 2가되어야함
                    // 1, 1 안맞음
                    if(keys_e[i] == answer_key.charAt(index_key)) {
                        index_key++;
                        cnt--;
                    }
                }



                if(cnt == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag)
                onlyCheck(list, relation);
            return;
        }
        visit[idx] = false;
        sub(visit, idx+1, n, relation);
        visit[idx] = true;
        sub(visit, idx+1, n, relation);

    }

    static void onlyCheck(ArrayList<Integer> arr, String[][] relation){

        HashSet<String> set = new HashSet<>();
        StringBuilder ans = new StringBuilder();

        for(int idx : arr){
            ans.append(idx);
        }

        for(String[] info : relation){
            StringBuilder sb = new StringBuilder();
            for(int idx : arr){
                sb.append(info[idx]);
            } // 문자열 합치기
            set.add(sb.toString());
        }
        //System.out.println("집합의 수 : " + set.size());

        // 집합의 크기가 학생의 크기랑 같으면
        if(set.size() == relation.length){
            //System.out.println("정답 더하기");
            answer++;
            // 해당 부분집합을 list에 넣어줘야함
            answer_list.add(ans.toString());
        }

    }
}
