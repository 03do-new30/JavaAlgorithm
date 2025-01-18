package PGM.코딩테스트고득점kit;

import java.util.*;

public class PGM_모음사전_84512 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] words = {"AAAAE", "AAAE", "I", "EIO"};
        int[] result = {6, 10, 1563, 1189};
        for (int i = 0; i < words.length; i++) {
            System.out.println("#" + i);
            System.out.println(sol.solution(words[i]) == result[i]);
        }
    }

    static class Solution {
        private final static String[] vowels = {"A", "E", "I", "O", "U"};
        private static List<String> dictionary = new ArrayList<>();

        public int solution(String word) {
            makeDictionary("", 0);
            int answer = dictionary.indexOf(word);
            return answer;
        }

        private void makeDictionary(String str, int length) {
            dictionary.add(str);
            if (length == 5) return;

            for (String v : vowels) {
                makeDictionary(str + v, length + 1);
            }

        }
    }
}
