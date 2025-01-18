package PGM.코딩테스트고득점kit;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PGM_K번째수_42748 {
    public static void main(String[] args) {
        int[][] array = {{1, 5, 2, 6, 3, 7, 4}};
        int[][][] commands = {{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}}};
        int[][] result = {{5, 6, 3}};

        Solution sol = new Solution();

        for (int i = 0; i < result.length; i ++) {
            System.out.println(
                    Arrays.equals(
                        sol.solution(array[i], commands[i]), result[i])
            );
        }

    }

    static class Solution {
        public int[] solution(int[] array, int[][] commands) {
            int[] answer = new int[commands.length];
            for (int idx = 0; idx < commands.length; idx++) {
                int[] command = commands[idx];
                int i = command[0];
                int j = command[1];
                int k = command[2];
                int[] newArray = Arrays.copyOfRange(array, i-1, j);
                Arrays.sort(newArray);
                answer[idx] = newArray[k-1];
            }
            return answer;
        }
    }
}
