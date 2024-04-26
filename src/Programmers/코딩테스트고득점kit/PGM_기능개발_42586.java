package Programmers.코딩테스트고득점kit;

import java.util.*;

public class PGM_기능개발_42586 {
    public static void main(String[] args) {
        int[][] progresses = {{93, 30, 55}, {95, 90, 99, 99, 80, 99}};
        int[][] speeds = {{1, 30, 5}, {1, 1, 1, 1, 1, 1}};
        int[][] results = {{2, 1}, {1, 3, 2}};
        Solution sol = new Solution();
        for (int i = 0; i < results.length; i++) {
            System.out.println("# " + i);
            System.out.println(Arrays.equals(results[i], sol.solution(progresses[i], speeds[i])));
        }
    }
    static class Solution {
        public int[] solution(int[] progresses, int[] speeds) {
            List<Integer> answer = new ArrayList<>();
            // 완료될 때까지 걸리는 기간
            int[] times = new int[progresses.length];
            for (int i = 0; i < progresses.length; i ++) {
                int tmp = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
                times[i] = tmp;
            }

            Stack<Integer> stack = new Stack<>();
            int maxTime = 0;
            for (int i = 0; i < times.length; i++ ) {
                if (i == 0) {
                    maxTime = times[i];
                    stack.push(times[i]);
                    continue;
                }
                if (times[i] <= maxTime) {
                    stack.push(times[i]);
                } else {
                    maxTime = times[i];
                    int cnt = 0;
                    while (!stack.isEmpty()) {
                        stack.pop();
                        cnt ++;
                    }
                    stack.add(times[i]);
                    answer.add(cnt);
                }
            }
            int cnt = 0;
            while (!stack.isEmpty()) {
                stack.pop();
                cnt ++;
            }
            if (cnt > 0)
                answer.add(cnt);
            return answer.stream().mapToInt(i->i).toArray();
        }
    }
}
