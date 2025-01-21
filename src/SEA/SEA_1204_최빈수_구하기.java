package SEA;

import java.util.*;
import java.io.*;

public class SEA_1204_최빈수_구하기 {

    private static final int N = 1000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int tc = Integer.parseInt(br.readLine());

            int[] scores = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                scores[i] = Integer.parseInt(st.nextToken());
            }

            int[] count = new int[101];
            for (int score : scores) {
                count[score]++;
            }

            int freqScore = 0;
            int freqCnt = 0;
            for (int i = 0; i <= 100; i++) {
                if (freqCnt <= count[i]) {
                    freqScore = i;
                    freqCnt = count[i];
                }
            }

            bw.write("#" + tc + " " + freqScore + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}