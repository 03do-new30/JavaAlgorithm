package PGM.코딩테스트고득점kit;

public class PGM_단어_변환_43163 {
    public static void main(String[] args) {
        String[] begins = {"hit", "hit"};
        String[] targets = {"cog", "cog"};
        String[][] words = {{"hot", "dot", "dog", "lot", "log", "cog"},
                {"hot", "dot", "dog", "lot", "log"}
        };
        int[] result = {4, 0};
        for (int i = 0; i < result.length; i++) {
            System.out.println("i = " + i);
            System.out.println(new Solution().solution(begins[i], targets[i], words[i]) == result[i]);
        }
    }
    static class Solution {
        boolean first = true;
        int answer = 0;

        public int solution(String begin, String target, String[] words) {
            boolean[] wordsVisited = new boolean[words.length];
            solve(begin, target, words, wordsVisited, 0);
            return answer;
        }

        public void solve(String current, String target, String[] words, boolean[] wordsVisited, int cnt) {
            if (current.equals(target)){
                if (first) {
                    answer = cnt;
                    first = false;
                } else {
                    answer = Math.min(answer, cnt);
                }
                return;
            }
            for (int i = 0; i < words.length; i++) {
                if (wordsVisited[i]) continue;
                if (changeable(current, words[i])) {
                    // words[i]로 변환
                    wordsVisited[i] = true;
                    solve(words[i], target, words, wordsVisited, cnt + 1);
                    wordsVisited[i] = false; // 백트래킹
                }
            }
            return;
        }

        // stringA를 stringB로 바꾸는 데 알파벳 한개만 바꾸면 된다면 true 반환
        public boolean changeable(String strA, String strB) {
            int cnt = 0;
            for (int i = 0; i < strA.length(); i++) {
                if (strA.charAt(i) != strB.charAt(i)) {
                    cnt++;
                }
            }
            return cnt == 1;
        }
    }
}
