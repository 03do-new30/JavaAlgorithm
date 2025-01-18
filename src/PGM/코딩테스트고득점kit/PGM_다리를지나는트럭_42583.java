package PGM.코딩테스트고득점kit;

import java.util.*;
public class PGM_다리를지나는트럭_42583 {
    public static void main(String[] args) {
        int[] bridgeLength = {2, 100, 100};
        int[] weight = {10, 100, 100};
        int[][] truckWeights = {{7, 4, 5, 6}, {10}, {10,10,10,10,10,10,10,10,10,10}};
        int[] result = {8, 101, 110};

        Solution sol = new Solution();
        for(int i = 0; i < result.length; i++) {
            System.out.println("# " + i);
            System.out.println(
                    sol.solution(bridgeLength[i], weight[i], truckWeights[i])
                    == result[i]
            );
        }
    }

    // 참고: https://velog.io/@cyj2825/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%8B%A4%EB%A6%AC%EB%A5%BC-%EC%A7%80%EB%82%98%EB%8A%94-%ED%8A%B8%EB%9F%AD-Java%EC%9E%90%EB%B0%94
    static class Solution {
        public int solution(int bridge_length, int weight, int[] truck_weights) {

            Queue<Integer> bridge = new LinkedList<>();
            // bridge_length만큼 0을 추가해준다.
            for (int i = 0; i < bridge_length; i++) {
                bridge.offer(0);
            }
            Queue<Integer> trucks = new LinkedList<>();
            for (int truck : truck_weights) {
                trucks.offer(truck);
            }

            int arrived = 0; // 도착한 트럭 대수
            int time = 0; // 걸린 시간
            int curWeight = 0; // 다리 위에 올라가있는 현재 무게

            while (arrived < truck_weights.length) {
                // System.out.println("time:" + time);
                // System.out.println("arrived:" + arrived);
                // System.out.println("bridge:" + bridge);
                // System.out.println("trucks:" + trucks);
                // System.out.println("-----");

                // 1. 다리를 한 칸 당긴다
                int polled = bridge.poll();
                if (polled > 0) {
                    curWeight -= polled;
                    arrived ++;
                }
                // 2. 새로운 차가 다리 위에 올라갈 수 있는지 확인한다.
                if (!trucks.isEmpty() && curWeight + trucks.peek() <= weight) {
                    int nextTruck = trucks.poll();
                    bridge.offer(nextTruck);
                    curWeight += nextTruck;
                } else {
                    bridge.offer(0);
                }
                time++;
            }

            return time;
        }
    }
}
