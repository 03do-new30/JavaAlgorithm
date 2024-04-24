package Programmers.코딩테스트고득점kit;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class PGM_가장큰수_42746 {
    public static void main(String[] args) {
        int[][] numbers = {{6, 10, 2}, {3, 30, 34, 5, 9}};
        String[] result = {"6210", "9534330"};
        Solution sol = new Solution();
        for (int i = 0; i < result.length; i ++) {
            System.out.println(sol.solution(numbers[i]).equals(result[i]));
        }
    }

    static class Solution {
        public String solution(int[] numbers) {
            String answer = "";
            // 숫자들의 앞자리 숫자가 큰 수가 먼저 와야 가장 큰 수가 만들어짐
            String[] strings = new String[numbers.length];
            for ( int i = 0; i < numbers.length; i ++ ) {
                strings[i] = String.valueOf(numbers[i]);
            }

            // 두 수를 합친 값이 큰 순서대로 (내림차순)
            // o1: 10, o2: 2라면
            // 210과 102중 뭐가 더 큰지 비교한다
            Arrays.sort(strings, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

            // 가장 큰 숫자가 0이면 뭘 해도 0이 나옴
            if (strings[0].equals("0")) {
                return "0";
            }
            return String.join("", strings);
        }
    }
}
