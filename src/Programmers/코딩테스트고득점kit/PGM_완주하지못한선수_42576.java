package Programmers.코딩테스트고득점kit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PGM_완주하지못한선수_42576 {
    public static void main(String[] args) {
        String[][] participant = {
                {"leo", "kiki", "eden"},
                {"marina", "josipa", "nikola", "vinko", "filipa"},
                {"mislav", "stanko", "mislav", "ana"}};
        String[][] completion = {{"eden", "kiki"},
                {"josipa", "filipa", "marina", "nikola"},
                {"stanko", "ana", "mislav"}};
        String[] result = {"leo", "vinko", "mislav"};

        Solution sol = new Solution();
        for (int i = 0; i < result.length; i ++) {
            System.out.println("# " + i);
            System.out.println(sol.solution(participant[i], completion[i])
                    .equals(result[i]));
        }
    }
    static class Solution {
        public String solution(String[] participant, String[] completion) {
            String answer = "";
            HashMap<String, Integer> map = new HashMap<>();
            for (String p : participant) {
                // HashMap.getOrDefault('A', 0)
                // A에 해당하는 value가 있으면 가져오고 아닐 경우 default를 0으로
                map.put(p, map.getOrDefault(p, 0) + 1);
            }

            for (String p : completion) {
                map.put(p, map.get(p) - 1);
            }

            // Iterator 사용법 숙지하기!
            Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Integer> entry = iterator.next();
                if (entry.getValue() == 1) {
                    answer = entry.getKey();
                    break;
                }
            }
            return answer;
        }
    }
}
