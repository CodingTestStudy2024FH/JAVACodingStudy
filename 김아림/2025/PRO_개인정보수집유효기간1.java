import java.util.*; 

class PRO_개인정보수집유효기간1 {
    
    static Map<String, String> privateInfoMap ; 
    static List<Integer> list; 
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        privateInfoMap = new HashMap<>(); 
        list = new ArrayList<>(); 
        
        for(String term : terms) {
            // A 6, B 12 , C 3 
            // yak - months  
            privateInfoMap.put(term.split(" ")[0] , term.split(" ")[1] ) ; 
        }
        
        // System.out.println(Arrays.toString(today.split("\\."))); -> 이건 첨알았네예... 
        
        
        
        int year  = Integer.parseInt(today.split("\\.")[0]);
        int month = Integer.parseInt(today.split("\\.")[1]); 
        int day   = Integer.parseInt(today.split("\\.")[2]); 
        
        for(int i = 0; i < privacies.length; i++) {
            String[] CollectDate = privacies[i].split(" ")[0].split("\\."); 
            String type = privacies[i].split(" ")[1];
            
            // 실제 만료 일자 (오늘일자에서 계산)
            int endDate = Integer.parseInt(privateInfoMap.get(type)) * 28 ; 
            int diff = (year - Integer.parseInt(CollectDate[0])) * 28 * 12 
                       + (month - Integer.parseInt(CollectDate[1])) * 28 
                       + (day - Integer.parseInt(CollectDate[2])) ; 
            
            
            if (endDate <= diff) {
                list.add(i + 1); 
            }
            
        }
        Collections.sort(list); 
        int[] answer = new int[list.size()]; 
        
        for(int i = 0; i < answer.length; i++){
            answer[i] = list.get(i); 
        }
        
        return answer; 

    }

  
}
