package PGM.코딩테스트고득점kit;

import java.util.HashMap;

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

            HashMap<String, Integer> participants = new HashMap<>();
            for (String name : participant) {
                participants.put(name, participants.getOrDefault(name, 0) + 1);
            }

            for (String name : completion) {
                participants.replace(name, participants.get(name) - 1);
            }

            for (String name : participants.keySet()) {
                if (participants.get(name) > 0) {
                    answer = name;
                    break;
                }
            }
            return answer;
        }
    }
}
