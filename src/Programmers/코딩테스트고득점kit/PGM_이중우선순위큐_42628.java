package Programmers.코딩테스트고득점kit;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PGM_이중우선순위큐_42628 {
    public static void main(String[] args) {
        String[][] operations = {{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"},
                {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"}};
        int[][] result = {{0, 0}, {333, -45}};

        Solution sol = new Solution();
        for (int i = 0; i < operations.length; i++) {
            System.out.println("#" + i);
            System.out.println(Arrays.equals(sol.solution(operations[i]), result[i]));
        }
    }

    static class Solution {
        public int[] solution(String[] operations) {
            int[] answer = {0, 0};

            // 최소 우선순위 큐
            PriorityQueue<Integer> minQ = new PriorityQueue<>();
            // 최대 우선순위 큐
            PriorityQueue<Integer> maxQ = new PriorityQueue<>((o1, o2) -> o2 - o1);

            for (String operation : operations) {
                String[] cmds = operation.split(" ");

                if (cmds[0].equals("I")) {
                    int n = Integer.parseInt(cmds[1]);
                    minQ.offer(n);
                    maxQ.offer(n);
                } else {
                    // 빈 큐일 경우 무시
                    if (minQ.isEmpty() || maxQ.isEmpty()) continue;

                    // 최대값 삭제
                    if (cmds[1].equals("1")) {
                        int polled = maxQ.poll();
                        minQ.remove(polled);
                    }
                    // 최소값 삭제
                    else {
                        maxQ.remove(minQ.poll());
                    }
                }
            }

            if (!maxQ.isEmpty()) {
                int maximum = maxQ.poll();
                minQ.remove(maximum);
                answer[0] = maximum;
                answer[1] = maximum;
            }

            if (!minQ.isEmpty()) {
                int minimum = minQ.poll();
                answer[1] = minimum;
            }

            return answer;
        }
    }
}
