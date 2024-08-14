package PRO;

import java.util.Arrays;

public class Pro_HIndex {
    /*
        n편중 h번 이상 인용된 논문이 h편이상이고, 나머지 h번 이하  인용시
        h의 최댓값이 이 과학자의 H-Index
        긍까,,각 숫자별로 돌아야하나?1000* 1000? ㄴㄴ
        정렬해서 반갈해서 도나?
        이분탐색 같은뎀;;

     */
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);//정렬
        //이분탐색
        int st = 0;
        int ed= 10000; // 인용 최댓수 10000회
        int mid = 0;
        while(st<=ed){
            mid = (st+ed)/2;//중간 설정
            if(can(mid,citations)){ //조건 체크
                answer = mid; //만족하면 답 갱신
                st = mid+1;
            }else{
                ed = mid-1;
            }
        }
        return answer;
    }
    public static boolean can(int h,int[] citations){
        int used = 0; //h번 이상 인용
        int unused = 0; //h번 이하 인용
        for(int i=0;i<citations.length;i++){
            if(h<=citations[i]){
                used++;
            }else if(h>=citations[i]){
                unused++;
            }
        }
        return (used>=h) && (unused<=h);
    }
}
