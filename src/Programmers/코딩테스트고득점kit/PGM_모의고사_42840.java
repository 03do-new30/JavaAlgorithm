package Programmers.코딩테스트고득점kit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PGM_모의고사_42840 {
    public static void main(String[] args) {
        int[][] answers = {{1, 2, 3, 4, 5}, {1, 3, 2, 4, 2}};
        int[][] results = {{1}, {1, 2, 3}};

        Solution sol = new Solution();
        for (int i = 0; i < results.length; i++) {
            System.out.println("#" + i);
            System.out.println(Arrays.equals(sol.solution(answers[i]), results[i]));
        }
    }
    static class Solution {
        public int[] solution(int[] answers) {
            List<Integer> answer = new ArrayList<>();

            // 1번 수포자가 맞은 개수
            int[] student1 = {1, 2, 3, 4, 5};
            int cnt1 = 0;
            int[] student2 = {2, 1, 2, 3, 2, 4, 2, 5};
            int cnt2 = 0;
            int[] student3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
            int cnt3 = 0;
            int idx = 0;
            while (idx < answers.length) {
                if (student1[idx%5] == answers[idx]) {
                    cnt1++;
                }
                if (student2[idx%student2.length] == answers[idx]) {
                    cnt2++;
                }
                if (student3[idx % student3.length] == answers[idx]) {
                    cnt3++;
                }
                idx++;
            }

            int maxScore = Math.max(cnt1, Math.max(cnt2, cnt3));
            if (cnt1 == maxScore) answer.add(1);
            if (cnt2 == maxScore) answer.add(2);
            if (cnt3 == maxScore) answer.add(3);

            return answer.stream().mapToInt(i -> i).toArray();
        }
    }
}
