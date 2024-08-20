import java.util.*;
class Solution {
        int passedCnt,numOfTruck, currentBridgeWeight, time;
        Deque<Integer> dq = new ArrayDeque<>();
        public int solution(int bridge_length, int weight, int[] truck_weights) {
            int answer = 0;
            numOfTruck = truck_weights.length;
            for(int i = 0; i < bridge_length; i++) dq.addLast(0);

            for(int truckWeight: truck_weights){
                while(true){
                    int nextTotalWeight = truckWeight + currentBridgeWeight;
                    int passedTruckWeight = dq.pollFirst();
                    nextTotalWeight -= passedTruckWeight;
                    time++;
                    if(dq.isEmpty()){
                        dq.addLast(truckWeight);
                        currentBridgeWeight = truckWeight;
                        break;
                    }
                    else if (dq.size() == numOfTruck) currentBridgeWeight -= passedTruckWeight;
                    // 값이 들어오는 상황
                    else if(nextTotalWeight <= weight){
                        dq.addLast(truckWeight);
                        currentBridgeWeight = nextTotalWeight;
                        break;
                    }
                    // 값이 들어오지 못하는 상황
                    // 시간 증가와 큐 값 제외는 위에서 진행
                    currentBridgeWeight -= passedTruckWeight;
                    dq.addLast(0);
                }
            }
            return this.time + bridge_length;
        }
    }
