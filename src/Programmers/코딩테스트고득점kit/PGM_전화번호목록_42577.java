package Programmers.코딩테스트고득점kit;

import java.util.HashMap;

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

            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i < phone_book.length; i++) {
                map.put(phone_book[i], i); // 전화번호 : 인덱스
            }

            for (int i = 0; i < phone_book.length; i++) {
                // 전화번호의 길이만큼 순회
                for (int j = 0; j < phone_book[i].length(); j++) {
                    // 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false
                    // map.containsKey로 탐색하므로 시간 단축
                    if (map.containsKey(phone_book[i].substring(0, j))) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
