package PGM.코딩테스트고득점kit;

import java.util.PriorityQueue;

public class PGM_더맵게_42626 {
    public static void main(String[] args) {
        int[][] scoville = {{1, 2, 3, 9, 10, 12}};
        int[] K = {7};
        int[] result = {2};

        Solution sol = new Solution();
        for(int i = 0; i < result.length; i++) {
            System.out.println("# " + i);
            System.out.println(
                    sol.solution(scoville[i], K[i])
                            == result[i]
            );
        }
    }

    static class Solution {
        public int solution(int[] scoville, int K) {
            int answer = 0;

            // 우선순위큐 사용
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int n : scoville) {
                pq.offer(n);
            }

            while (pq.peek() < K) {
                int food1 = pq.poll();
                // 모든 음식을 섞어도 스코빌지수가 K 이상이 되지 않는 경우
                if (pq.isEmpty()) { return -1;}
                int food2 = pq.poll();
                pq.offer(food1 + food2 * 2);
                answer++;
            }
            return answer;
        }
    }
}
