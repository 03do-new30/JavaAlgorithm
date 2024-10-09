package Programmers.코딩테스트고득점kit;

import java.util.Stack;
import java.util.Arrays;

public class PGM_주식가격_42584 {

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        int[] result = {4, 3, 1, 1, 0};

        Solution sol = new Solution();
        System.out.println(Arrays.equals(sol.solution(prices),result));
    }

    static class Solution {
        public int[] solution(int[] prices) {
            int[] answer = new int[prices.length];

            Stack<Integer> stack = new Stack<>();

            // stack에 인덱스를 추가해간다.
            for (int i = 0; i < prices.length; i++) {

                // stack top에 있는 인덱스의 가격이 현재 들어갈 가격보다 크다면 감소하는 경우이다.
                while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                    int poppedIdx = stack.pop();
                    // 가격이 감소하는 시간을 answer에 저장해준다.
                    answer[poppedIdx] = i - poppedIdx;
                }
                stack.push(i);

            }

            // stack에 남아있는 인덱스들을 처리해준다.
            // 이 인덱스들은 값이 감소하지 않은 인덱스들이다.
            // 해당 인덱스들의 값이 유지되는 시간은 ((전체 길이-1) - 인덱스)이다.
            while (!stack.isEmpty()) {
                int poppedIdx = stack.pop();
                answer[poppedIdx] = prices.length - 1 - poppedIdx;
            }
            return answer;
        }
    }
}
