package SEA;

import java.util.*;
import java.io.*;

public class SEA_1288_새로운_불면증_치료법 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        int goal = (1 << 10) -1;

        for (int test_case = 1; test_case <= T; test_case++) {

            int N = Integer.parseInt(br.readLine());
            int status = 0;

            int cnt = 0;
            for (cnt = 1;; cnt++) {

                char[] numChars = String.valueOf(N * cnt).toCharArray();

                for (char numChar : numChars) {
                    int num = numChar - '0';
                    status = status | (1 << num); // 등장한 숫자 기록
                }

                if (status == goal) { // 모든 숫자가 등장
                    break;
                }
            }
            bw.write("#" + test_case + " " + (N * cnt) + "\n");
        }


        br.close();
        bw.flush();
        bw.close();
    }
}
