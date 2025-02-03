package SEA;

import java.io.*;

public class SEA_10726_이진수_표현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            String[] nm = br.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);

            // m의 이진수 표현의 마지막 n비트가 모두 1로 켜져 있는지 아닌지 판별
            int goal = (1<<n) - 1;

            String answer = "OFF";
            if ((m & goal) == goal) {
                answer = "ON";
            }

            bw.write("#" + test_case + " " + answer + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
