package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2240_자두나무 {

    static int T, W;
    static int[] time;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        input();
        dp = new int[T+1][W+1];
        // dp[i][j] = i초에 j번 움직였을 때, 얻을 수 있는 최대 자두 개수
        // i가 홀수일 때는 2번 나무 아래에 위치하고, 짝수일 때는 1번 나무 아래에 위차한다.
        for (int i = 1; i <= T; i++) {
            for (int j = 0; j <= W; j++) {

                if (j == 0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]);
                }

                if (time[i] == 1) { // 1번 나무에서 떨어질 차례
                    if (j%2 == 0) { // 1번 나무 아래에 있음
                        dp[i][j] += 1;
                    }
                } else { //  2번 나무에서 떨어질 차례
                    if (j%2 == 1) { // 2번 나무 아래에 있음
                        dp[i][j] += 1;
                    }
                }
            }
        }

        // T초에 받을 수 있는 자두의 최대 개수
        int max = 0;
        for (int val : dp[T]) {
            if (max < val) {  max = val; }
        }
        System.out.println(max);
    }

    private static void input() throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        time = new int[T+1];
        for (int i = 1; i <= T; i++) {
            time[i] = Integer.parseInt(br.readLine());
        }
        br.close();
    }
}
