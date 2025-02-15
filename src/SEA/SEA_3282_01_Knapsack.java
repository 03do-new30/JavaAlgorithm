package SEA;

import java.io.*;
import java.util.*;

public class SEA_3282_01_Knapsack {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] weight = new int[N+1];
            int[] value = new int[N+1];

            for (int i = 1; i < N+1; i++) {
                st = new StringTokenizer(br.readLine());
                weight[i] = Integer.parseInt(st.nextToken());
                value[i] = Integer.parseInt(st.nextToken());
            }

            // dp[n][k] = 가방 n까지 사용하여 부피가 k인 배낭을 채울 때, 가치의 최대값 저장
            int[][] dp = new int[N+1][K+1];
            for (int n = 1; n < N+1; n++) {
                for (int k = 1; k < K+1; k++) {
                    // dp[n-1][k - wt[n]] + value[n]: n번 물건을 사용할 때
                    // dp[n-1][k]: n번 물건을 사용하지 않을 때
                    dp[n][k] = dp[n-1][k];
                    if (k - weight[n] >= 0) {
                        dp[n][k] = Integer.max(dp[n][k], dp[n-1][k-weight[n]] + value[n]);
                    }
                }
            }
            bw.write("#" + tc + " " + dp[N][K] + "\n");

        }

        br.close();
        bw.flush();
        bw.close();
    }
}
