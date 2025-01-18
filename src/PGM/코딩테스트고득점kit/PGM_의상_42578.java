package PGM.코딩테스트고득점kit;

import java.util.*;
public class PGM_의상_42578 {
    public static void main(String[] args) {
        String[][][] clothes= {
            {   {"yellow_hat", "headgear"},
                {"blue_sunglasses", "eyewear"},
                {"green_turban", "headgear"}
            },
            {
                {"crow_mask", "face"},
                {"blue_sunglasses", "face"},
                {"smoky_makeup", "face"}
            }
        };
        int[] result = {5, 3};
        Solution sol = new Solution();
        for (int i = 0; i < result.length; i++) {
            System.out.println("# " + i);
            System.out.println(sol.solution(clothes[i]) == result[i]);
        }
    }
    static class Solution {
        public int solution(String[][] clothes) {
            int answer = 1;
            // 옷 종류 : 개수
            HashMap<String, Integer> map = new HashMap<>();
            for (String[] cloth : clothes) {
                String type = cloth[1];
                map.put(type, map.getOrDefault(type, 0) + 1);
            }

            for (String key : map.keySet()) {
                answer *= (map.get(key) + 1); // 조합 -> 안입는 경우도 고려하기
            }

            // 모두 안 입는 경우는 없으니까 - 1
            answer -= 1;

            return answer;
        }
    }
}
