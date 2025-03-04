import java.util.*;

class Solution {
    public static int solution(int storey) {
        int steps = 0;
        
        while (storey > 0) {
            int lastDigit = storey % 10;
            
            if (lastDigit > 5) {
                steps += (10 - lastDigit);
                storey = (storey / 10) + 1;
            } else if (lastDigit < 5) {
                steps += lastDigit;
                storey = storey / 10;
            } 
            
            else { // lastDigit == 5
                int nextDigit = (storey / 10) % 10;
                if (nextDigit >= 5) {
                    steps += (10 - lastDigit);
                    storey = (storey / 10) + 1;
                } else {
                    steps += lastDigit;
                    storey = storey / 10;
                }
            }
        }
        return steps;
    }
}

