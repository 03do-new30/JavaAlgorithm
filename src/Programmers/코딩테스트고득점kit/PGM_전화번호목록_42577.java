package Programmers.코딩테스트고득점kit;

import java.util.Arrays;
import java.util.HashSet;

public class PGM_전화번호목록_42577 {
    public static void main(String[] args) {
        String[][] phone_book = {
                {"119", "97674223", "1195524421"},
                {"123","456","789"},
                {"12","123","1235","567","88"}};
        boolean[] result = {false, true, false};

        Solution sol = new Solution();
        for(int i = 0; i < result.length; i++) {
            System.out.println("# " + i);
            System.out.println(sol.solution(phone_book[i]) == result[i]);
        }
    }
    static class Solution {
        public boolean solution(String[] phone_book) {

            // 전화번호들을 hashSet에 추가
            HashSet<String> hashSet = new HashSet<>(Arrays.asList(phone_book));

            // 시간복잡도: 1,000,000 * 20
            for (int i = 0; i < phone_book.length; i++) {

                // 번호가 한글자인 경우, 패스한다.
                if (phone_book[i].length() == 1) {
                    continue;
                }
                // 어떤 번호의 접두어가 hashSet에 존재하는지 확인한다.
                for (int j = 1; j < phone_book[i].length(); j++) {
                    String head = phone_book[i].substring(0, j);
                    if (hashSet.contains(head)) {
                        return false;
                    }
                }
                hashSet.add(phone_book[i]);
            }
            return true;
        }
    }
}
