package Programmers.코딩테스트고득점kit;

public class PGM_타겟_넘버_43165 {
    public static void main(String[] args) {
        int[][] numbers = {{1, 1, 1, 1, 1}, {4, 1, 2, 1}};
        int[] target = {3, 4};
        int[] result = {5, 2};
        for (int i = 0; i < result.length; i++) {
            Solution sol = new Solution();
            System.out.println(sol.solution(numbers[i], target[i]) == result[i]);
        }
    }

    static class Solution {
        int answer = 0;

        public int solution(int[] numbers, int target) {
            solve(numbers, target, 0, 0);
            return answer;
        }

        public void solve(int[] numbers, int target, int idx, int sumResult) {
            if (idx == numbers.length) {
                if (sumResult == target)
                    answer++;
                return;
            }
            // +numbers[idx]
            solve(numbers, target, idx + 1, sumResult + numbers[idx]);
            // -numbers[idx]
            solve(numbers, target, idx + 1, sumResult - numbers[idx]);
        }
    }
}


