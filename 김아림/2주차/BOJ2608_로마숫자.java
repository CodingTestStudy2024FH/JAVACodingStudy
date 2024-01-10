import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ2608_로마숫자 {

    static HashMap<String, Integer> info = new HashMap<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        // map 정리 -> 그냥 다 정리 하기 char(1개 짜리)만 하지 말고
        info.put("I", 1);
        info.put("IV", 4);
        info.put("V", 5);
        info.put("IX", 9);
        info.put("X", 10);
        info.put("XL", 40);
        info.put("L", 50);
        info.put("XC", 90);
        info.put("C", 100);
        info.put("CD", 400);
        info.put("D", 500);
        info.put("CM", 900);
        info.put("M", 1000);

        int targetNum = strToNum(str1, str2);
        System.out.println(targetNum);

        // targetNum을 만들기 위해서 숫자 만들기 -> 글자수가 작아야 하니까 큰 순서대로 빼와
        System.out.println(numToStr(targetNum));
    }

    static String numToStr(int num) {

        StringBuilder answer = new StringBuilder();

        while (num > 0) {

            if(num >= 1000) {
                answer.append("M");
                num -= 1000;
            }else if(num >= 900) {
                answer.append("CM");
                num -= 900;
            }else if(num >=500) {
                answer.append("D");
                num -= 500;
            }else if(num >= 400){
                answer.append("CD");
                num -= 400;
            }else if(num >=100){
                answer.append("C");
                num -= 100;
            }else if(num >=90){
                answer.append("XC");
                num -= 90;
            }else if(num >=50){
                answer.append("L");
                num -= 50;
            }else if(num >=40){
                answer.append("XL");
                num -= 40;
            }else if(num >= 10){
                answer.append("X");
                num -= 10;
            }else if(num >= 9){
                answer.append("IX");
                num -= 9;
            }else if(num >=5){
                answer.append("V");
                num -= 5;
            }else if(num >=4){
                answer.append("IV");
                num -= 4;
            }else {
                answer.append("I");
                num -= 1;
            }
        }

        return answer.toString();
    }


    static int strToNum(String str1, String str2){

        int sum = 0;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < str1.length(); i++){
            // 한글자 읽어서
            char c = str1.charAt(i);
            // 아래의 경우는 두글자가 될 수 있음
            if((c == 'I' || c == 'X' || c == 'C') && i < str1.length() -1) {
                sb.append(c).append(str1.charAt(i+1));
                // 다음 글자 넣어보기
                String key = sb.toString();
                if(info.containsKey(key)){
                    // 만약에 있는 문자이면
                    sum += info.get(key); // 값 더해줘
                    i++; // 다다음 읽어야하니까 두번 점프를 위해서

                    // 아닌 경우엔 그냥 한자리 로마자에 해당하는 숫자만 더해주기
                }else {
                    sum += info.get(Character.toString(c));
                }
                sb = new StringBuilder();
            }else {
                sum += info.get(Character.toString(c));
            }
        }

        for(int i = 0; i < str2.length(); i++){
            char c = str2.charAt(i);
            // 아래의 경우는 두글자가 될 수 있음
            if((c == 'I' || c == 'X' || c == 'C') && i < str2.length() -1) {
                sb.append(c).append(str2.charAt(i+1));
                String key = sb.toString();
                if(info.containsKey(key)){
                    sum += info.get(key);
                    i++;
                }else {
                    sum += info.get(Character.toString(c));
                }
                sb = new StringBuilder();
            }else {
                sum += info.get(Character.toString(c));
            }
        }


        return sum;
    }
}
