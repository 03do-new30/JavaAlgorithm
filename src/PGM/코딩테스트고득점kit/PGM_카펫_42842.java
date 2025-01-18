package PGM.코딩테스트고득점kit;

import java.util.Arrays;

public class PGM_카펫_42842 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] brown = {10, 8, 24};
        int[] yellow = {2, 1, 24};
        int[][] results = {{4, 3}, {3, 3}, {8, 6}};
        for (int i = 0; i < results.length; i++ ) {
            System.out.println("#" + i);
            System.out.println(Arrays.equals(results[i], sol.solution(brown[i], yellow[i])));
        }
    }

    static class Solution {
        public int[] solution(int brown, int yellow) {
            int[] answer = new int[2];

            // 총 카펫의 넓이 = brown + yellow
            int carpet = brown + yellow;

            // 전체 카펫의 가로가 a, 세로가 b라면
            // 노란 구역의 가로는 (a-2), 세로는 (b-2)
            for (int totalGaro = 1; totalGaro <= carpet; totalGaro++) {
                if (carpet % totalGaro != 0) {
                    continue;
                }

                int totalSero = carpet / totalGaro;
                if (totalGaro < totalSero) continue;

                int yellowGaro = totalGaro - 2;
                int yellowSero = totalSero - 2;
                if (yellowGaro * yellowSero == yellow) {
                    answer[0] = totalGaro;
                    answer[1] = totalSero;
                    break;
                }
            }
            return answer;
        }
    }
}
