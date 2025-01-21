package SEA;

import java.util.*;
import java.io.*;

public class SEA_1859_백만장자_프로젝트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            int n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] prices = new int[n];
            for (int i = 0; i < n; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            long profit = 0L; // 와.... long type 써야함에 유의한다...
            int maxPrice = 0; // 현 시점의 최고가
            for (int i = n - 1; i > -1; i--) {
                if (prices[i] >= maxPrice) {
                    maxPrice = prices[i];
                }
                profit += (maxPrice - prices[i]);
            }

            bw.write("#" + test_case + " " + profit + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}