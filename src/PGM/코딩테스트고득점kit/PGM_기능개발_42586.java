package PGM.코딩테스트고득점kit;

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
            ArrayList<Integer> answer = new ArrayList<>();

            Stack<Integer> jobs = new Stack<>();
            for (int i = progresses.length - 1; i >= 0; i--) {
                jobs.push(i);
            }

            int day = 0;
            while (!jobs.isEmpty()) {
                for (int i = 0; i < progresses.length; i++) {
                    progresses[i] += speeds[i];
                }
                int done = 0;
                while (!jobs.isEmpty() && progresses[jobs.peek()] >= 100) {
                    jobs.pop();
                    done++;
                }

                if (done > 0) {
                    answer.add(done);
                }
                day++;
            }
            return answer.stream().mapToInt(i -> i).toArray();
        }
    }
}
