package PGM.코딩테스트고득점kit;

import java.util.HashSet;

public class PGM_폰켓몬_1845 {
    public static void main(String[] args) {
        int[][] nums = {{3, 1, 2, 3}, {3, 3, 3, 2, 2, 4}, {3, 3, 3, 2, 2, 2,}};
        int[] result = {2, 3, 2};
        Solution sol = new Solution();
        for (int i = 0; i < result.length; i++) {
            System.out.println("# " + i);
            System.out.println(sol.solution(nums[i]) == result[i]);
        }
    }

    static class Solution {
        public int solution(int[] nums) {
            int pick = nums.length / 2;
            HashSet<Integer> hashSet = new HashSet<>();
            for(int num : nums) {
                hashSet.add(num);
            }
            if (pick >= hashSet.size()) return hashSet.size();
            return pick;
        }
    }
}
