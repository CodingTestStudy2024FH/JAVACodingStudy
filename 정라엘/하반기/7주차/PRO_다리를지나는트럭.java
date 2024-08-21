import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // 큐를 사용하여 다리를 건너고 있는 트럭들을 관리
        Queue<Integer> bridge = new LinkedList<>();
        int time = 0; // 경과 시간
        int bridgeWeight = 0; // 다리 위의 트럭들의 무게 합
        
        for (int truck : truck_weights) {
            while (true) {
                // 1. 다리 위에 아무 트럭도 없을 경우
                if (bridge.isEmpty()) {
                    bridge.add(truck);
                    bridgeWeight += truck;
                    time++;
                    break;
                } 
                // 2. 다리 위에 트럭이 있을 경우
                else if (bridge.size() == bridge_length) {
                    bridgeWeight -= bridge.poll(); // 다리의 끝에 도달한 트럭을 제거
                } 
                // 3. 현재 트럭을 다리에 올릴 수 있는 경우
                else {
                    if (bridgeWeight + truck <= weight) {
                        bridge.add(truck);
                        bridgeWeight += truck;
                        time++;
                        break;
                    } 
                    // 4. 현재 트럭을 다리에 올릴 수 없는 경우, 시간을 증가시키며 대기
                    else {
                        bridge.add(0); // 트럭 없이 시간을 증가시키기 위해 0을 추가
                        time++;
                    }
                }
            }
        }
        
        // 마지막 트럭이 다리를 완전히 건너는 데 걸리는 시간을 추가
        return time + bridge_length;
    }
}
