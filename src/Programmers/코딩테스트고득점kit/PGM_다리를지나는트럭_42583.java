package Programmers.코딩테스트고득점kit;

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
            int answer = 0;

            // 길이가 bridge_length인 큐를 생성한다
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < bridge_length; i++) {
                q.add(0);
            }
            // 다리 길이가 1이거나 트럭의 개수가 1인 경우
            if (bridge_length == 1) return truck_weights.length + 1;
            if (truck_weights.length == 1) return bridge_length + 1;

            int idx = 0;
            // 현재 다리에 있는 트럭의 무게
            int curWeight = 0;
            // 트럭의 개수만큼 반복
            while (idx < truck_weights.length) {
                // 현재 다리에 존재하는 맨 앞 트럭의 무게를 뺀다
                curWeight -= q.poll();
                answer++; // 새로운 트럭을 넣는다

                // 현재 다리에 있는 트럭 무게와 들어올 트럭 무게 합과 weight 비교
                if (curWeight + truck_weights[idx] <= weight) {
                    q.offer(truck_weights[idx]);
                    curWeight += truck_weights[idx++];
                } else {
                    q.offer(0); // 견딜 수 있는 무게보다 커지면 0을 넣음
                }
            }
            // 마지막 트럭까지 다리를 건너는 값을 구하기 위해 + bridge_length
            return answer + bridge_length;
        }
    }
}
