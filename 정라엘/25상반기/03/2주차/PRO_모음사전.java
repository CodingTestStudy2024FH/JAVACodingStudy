class Solution {
    public int solution(String word) {
        char[] vowels = {'A', 'E', 'I', 'O', 'U'};
        // 단어는 최대 5자리이며, 앞에서부터 차례대로 선택
        // 첫 번째 자리가 정해지면 뒤에 올 수 있는 단어 개수를 미리 계산
        // (5^4 + 5^3 + 5^2 + 5^1 + 1)
        int[] weights = {781, 156, 31, 6, 1}; // 각 자리수별 가중치 계산
        int index = 0;
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            for (int j = 0; j < vowels.length; j++) {
                if (vowels[j] == c) {
                    index += j * weights[i] + 1; // 해당 위치까지의 단어 개수 추가
                    break;
                }
            }
        }  
        return index;
    }
}
