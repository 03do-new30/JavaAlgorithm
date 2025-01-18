package PGM.코딩테스트고득점kit;

import java.util.Arrays;

public class PGM_입국심사_43238 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(6, new int[]{7, 10}) == 28);
    }

    static class Solution {
        public long solution(int n, int[] times) {
            long answer = 0;
            // times 오름차순 정렬
            Arrays.sort(times);
            // 모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 이분탐색해본다.
            long left = 0;
            long right = times[times.length - 1] * (long)n;
            long mid;
            while (left <= right) {
                mid = (left + right) / 2;
                // 주어진 mid 시간동안 몇 명을 검사할 수 있는가?
                long people = 0;
                for (int time : times) {
                    people += mid / time;
                }
                if (people < n) {
                    // 시간이 부족하다.
                    left = mid + 1;
                } else {
                    // 시간이 넉넉하다.
                    answer = mid; // 현재 mid를 answer에 저장해두고
                    right = mid - 1; // 범위를 더 줄여본다
                }
            }
            return answer;
        }
    }
}
