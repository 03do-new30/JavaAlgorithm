package Programmers.코딩테스트고득점kit;

import java.util.Arrays;
import java.util.OptionalInt;

public class PGM_HIndex_42747 {
    public static void main(String[] args) {
        int[][] citations = {{3, 0, 6, 1, 5}};
        int[] result = {3};
        Solution sol = new Solution();
        for (int i = 0; i < result.length; i ++) {
            System.out.println(sol.solution(citations[i]) == result[i]);
        }
    }
    static class Solution {
        public int solution(int[] citations) {
            int answer = 0;

            Arrays.sort(citations);

            for (int i = 0; i < citations.length; i++) {
                int h = citations.length - i; // 인용된 논문의 수
                if (citations[i] >= h) {
                    answer = h;
                    break;
                }
            }

            return answer;
        }
    }
}
