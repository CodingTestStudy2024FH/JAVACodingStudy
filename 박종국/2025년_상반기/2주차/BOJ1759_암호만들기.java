package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1759_암호_만들기 {
    static int passwordLength = 0, alphaCount = 0;
    static ArrayList<String> alphabetList = new ArrayList<String>();
    static List<Character> moum = List.of('a', 'e', 'i', 'o', 'u');
    static StringBuilder resultSB = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        passwordLength = Integer.parseInt(st.nextToken());
        alphaCount = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) alphabetList.add(st.nextToken());
        Collections.sort(alphabetList);

        StringBuilder sb = new StringBuilder();
        comb(0, 0, sb);
        System.out.println(resultSB);
    }
    static void comb(int start, int cnt, StringBuilder sb) {
        if(cnt == passwordLength) {
            if(isRight(sb)) resultSB.append(sb).append("\n");
            return;
        }
        for(int i = start; i < alphabetList.size(); i++) {
            sb.append(alphabetList.get(i));
            comb(i+1, cnt+1, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    static boolean isRight(StringBuilder sb) {
        int moumCount = 0;
        int jaumCount = 0;

        for(int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);
            if(moum.contains(ch)) moumCount++;
            else jaumCount++;
        }
        return moumCount >= 1 && jaumCount >= 2;
    }

}
