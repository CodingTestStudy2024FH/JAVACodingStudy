import java.util.*; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors; // Collector 쓸때 요거 넣어주는거 잊지말귀 


// 제 풀이는 이게 아닌데여.. 다른 답지 보다가 Canlendar나 Date같은 잘 안쓰는 타입을 쓴 예시가 있길래 좋아서 훔쳐옴 
class PRO_개인정보수집유효기간2 {
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        Calendar cal_today = Calendar.getInstance();
        cal_today.setTime(stringToDate(today));
        Map<String, Integer> termsMap = Arrays.stream(terms)
                .map(i -> i.split(" "))
                .collect(Collectors.toMap(s -> s[0], s-> Integer.parseInt(s[1])));

        List<Integer> list = new ArrayList<>();
        for(int i=0; i<privacies.length; i++) {
            String[] privacie = privacies[i].split(" ");
            Calendar cal_privacie = Calendar.getInstance();
            cal_privacie.setTime(stringToDate(privacie[0]));
            cal_privacie.add(Calendar.MONTH, termsMap.get(privacie[1]));

            if(cal_today.compareTo(cal_privacie) >= 0) {
                list.add(i+1);
            }
        }

        return list.stream().mapToInt(i -> i).toArray();
    }

    public Date stringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return d;
    }
}
