package PGM.코딩테스트고득점kit;

import java.util.*;

public class PGM_소수찾기_42839 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        String[] numbers = {"17", "011"};
        int[] results = {3, 2};
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("#" + i);
            System.out.println(results[i] == sol.solution(numbers[i]));
        }
    }

    static class Solution {

        // 만들어진 숫자를 저장
        HashSet<Integer> set;

        public int solution(String numbers) {
            String[] cards = numbers.split("");
            set = new HashSet<Integer>();

            for (int i = 1; i <= cards.length; i++) {
                makeNumber(cards, new boolean[cards.length], i, "");
            }

            // set의 최대값까지 에라토스테네스의체를 이용해 소수 여부를 판별한다.
            int maxNumber = Collections.max(set);
            boolean[] isPrime = new boolean[maxNumber + 1];
            // 초기화
            Arrays.fill(isPrime, true);
            for (int i = 0; i < maxNumber + 1; i++) {
                if (i == 0 || i == 1) {
                    isPrime[i] = false;
                    continue;
                }
                if (isPrime[i]) {
                    for (int j = 2; j * i < maxNumber + 1; j++) {
                        isPrime[i*j] = false;
                    }
                }
            }

            int answer = 0;
            for (int n : set) {
                if (isPrime[n]) answer ++;
            }
            return answer;
        }

        // 카드 n개를 사용해 숫자를 만든다.
        private void makeNumber(String[] cards, boolean[] visited, int n, String tmp) {
            if ( n == 0 ) {
                set.add(Integer.parseInt(tmp));
            } else {
                for (int i = 0; i < cards.length; i++) {
                    if (!visited[i]) {
                        visited[i] = true;
                        makeNumber(cards, visited, n-1, tmp + cards[i]);
                        visited[i] = false;
                    }
                }
            }
        }
    }
}
