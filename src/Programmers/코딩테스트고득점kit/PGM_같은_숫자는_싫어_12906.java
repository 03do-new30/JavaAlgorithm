package Programmers.코딩테스트고득점kit;

import java.util.*;

public class PGM_같은_숫자는_싫어_12906 {

    public static void main(String[] args) {
        int[][] arr = {{1, 1, 3, 3, 0, 1, 1,}, {4, 4, 4, 3, 3}};
        int[][] answer = {{1, 3, 0, 1}, {4, 3}};
        Solution sol = new Solution();
        for (int i = 0; i < answer.length; i ++) {
            System.out.println("# " + i + 1);
            System.out.println(Arrays.equals(sol.solution(arr[i]), answer[i]));
        }
    }


    static class Solution {
        public int[] solution(int []arr) {
            int[] answer;

            Stack<Integer> stack = new Stack<>();
            for (int i : arr) {
                if (!stack.isEmpty() && (stack.peek() == i))
                    continue;
                stack.push(i);
            }
            answer = new int[stack.size()];
            for (int i = stack.size() - 1; i >= 0; i--) {
                answer[i] = stack.pop();
            }
            return answer;
        }
    }
}
