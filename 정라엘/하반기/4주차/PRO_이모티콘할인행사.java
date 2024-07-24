class Solution {  
  int sign=0; // 최대 사용자 수
  int earn=0; // 최대 수익
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int [2];
        
        int[] emo = new int[emoticons.length];
        
        comb(emo,0,users,emoticons);
        
        answer[0]=sign;
        answer[1]=earn;
        
        return answer;
    }
    
    public void comb(int[] emo,int start,int[][] users,int[] emoticons){
        
        if(start==emo.length){
            calculate(emo,users,emoticons);
            return;
        }
        
        for(int i=10;i<=40;i+=10){
            emo[start]=i;
            comb(emo,start+1,users,emoticons);
        }
        
    }
    
    public void calculate(int[] emo,int[][] users,int[] emoticons){ 
        int count=0;
        int earn_t=0;  // 총 수익

        // 사용자 구매 여부 판단
        for(int[] user : users){
            int discount= user[0];
            int price =user[1];
            int sum=0;
            
            for(int i=0;i<emo.length;i++){
                if(emo[i]>=discount){
                    sum+=(emoticons[i]/100)*(100-emo[i]);
                }
            }
            
            if(sum>=price){
                count++;
            }
            else{
                earn_t+=sum;
            }  
        }
        
        // 최대 사용자 수 갱신
        if(count>sign){
            sign=count;
            earn=earn_t;
            return;
        }    
        else if(count==sign){
            if(earn<earn_t){
                earn=earn_t;
            }
        }
    }
    
}
