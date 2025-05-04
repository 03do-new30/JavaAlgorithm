package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_20437_문자열_게임_2 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, K;
    static String W;
    static int[] counts;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            input();
            countWord();
            solve();
        }
        br.close();
    }

    private static void solve() {
        int minLength = Integer.MAX_VALUE;
        int maxLength = Integer.MIN_VALUE;

        for (int i = 0; i < W.length(); i++) {
            if (counts[W.charAt(i) - 'a'] < K) continue; // 문자열의 개수가 K개 미만이면 스킵
            int cnt = 0;
            for (int j = i; j < W.length(); j++) {
                if (W.charAt(i) == W.charAt(j)) {
                    if (++cnt == K) {
                        int length = j - i + 1;
                        minLength = Math.min(minLength, length);
                        maxLength = Math.max(maxLength, length);
                        break;
                    }
                }
            }

        }
        if (minLength == Integer.MAX_VALUE && maxLength == Integer.MIN_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minLength + " " + maxLength);
        }
    }

    private static void countWord() {
        counts = new int[26];
        for (int i = 0; i < W.length(); i++) {
            counts[W.charAt(i) - 'a']++;
        }
    }

    private static void input() throws IOException {
        W = br.readLine();
        K = Integer.parseInt(br.readLine());
    }
}
