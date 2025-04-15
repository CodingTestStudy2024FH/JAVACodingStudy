import java.io.*;
import java.util.*;

public class BOJ20437_문자열게임2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            Map<Character, List<Integer>> map = new HashMap<>();

            // 각 문자에 대해 등장 인덱스 저장
            for (int i = 0; i < W.length(); i++) {
                char ch = W.charAt(i);
                map.putIfAbsent(ch, new ArrayList<>());
                map.get(ch).add(i);
            }

            int minLen = Integer.MAX_VALUE;
            int maxLen = Integer.MIN_VALUE;

            // 각 문자 리스트에 대해 K개씩 묶어서 길이 계산
            for (char key : map.keySet()) {
                List<Integer> indices = map.get(key);
                if (indices.size() < K) continue;

                for (int i = 0; i <= indices.size() - K; i++) {
                    int start = indices.get(i);
                    int end = indices.get(i + K - 1);
                    int length = end - start + 1;

                    minLen = Math.min(minLen, length);
                    maxLen = Math.max(maxLen, length);
                }
            }

            if (minLen == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(minLen + " " + maxLen);
            }
        }
    }
}
