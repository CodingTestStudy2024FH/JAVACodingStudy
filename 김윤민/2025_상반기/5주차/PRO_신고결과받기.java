import java.util.*;

public class PRO_신고결과받기 {
    /*
        각 유저는 한번에 한명의 유저를 신고.
        횟수는 제한 없음
        한유저 여러번 신고가능 -> 하지만 신고 횟수는 1회처리

        k번이상 신고되면 정지. 신고한 유저 모두에게 메일발송
        => 마지막에 한꺼번에 메일발송

        각 유저별로 처리 결과 메일을 받은 횟수
        그냥 구현인듯?
     */
    public static void main(String[] args) {
        String[] id_list = {"con", "ryan"};
        String[] report ={"ryan con", "ryan con", "ryan con", "ryan con"};
        System.out.println(Arrays.toString(solution(id_list,report,2)));
    }
    //Map<신고당한사람, 신고한사람 list>
    //결과 : id_list 순서대로 -> 이것도 Map으로 관리 Map<String,Integer> result
    static Map<String, Set<String>> list;
    static Map<String, Integer> result;//결과 Map<사람,신고메일받은 횟수>
    public static int[] solution(String[] id_list, String[] report, int k) {
        list = new HashMap<>();
        result = new HashMap<>();
        for(String A:id_list){
            list.put(A,new HashSet<>());//초기화
            result.put(A,0);
        }
        for(String s:report){
            StringTokenizer st = new StringTokenizer(s);
            String A = st.nextToken();
            String target = st.nextToken();
            list.get(target).add(A); //신고 적립
        }

        for(String A:id_list){
            if(list.get(A).size()>=k){//신고가 k번 이상이면
                for(String getEmail:list.get(A)){
                    result.put(getEmail,result.getOrDefault(getEmail,0)+1); //메일 받을사람 count+1
                }
            }
        }
        int[] ans = new int[id_list.length]; //변환
        for(int i=0;i<ans.length;i++){
            ans[i]= result.get(id_list[i]);
        }
        return ans;
    }
}
