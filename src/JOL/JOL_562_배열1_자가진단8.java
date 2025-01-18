package JOL;

import java.util.*;
import java.io.*;


public class JOL_562_배열1_자가진단8 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = 10;
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 짝수번째 입력된 값의 합
        int evenSum = 0;
        // 홀수번째 입력된 값의 합
        int oddSum = 0;

        for (int i = 0; i < 10; i++) {
            if ((i+1) % 2 == 0) {
                evenSum += arr[i];
            } else {
                oddSum += arr[i];
            }
        }

        double oddAvg = oddSum / 5.0;

        bw.write("sum : " + evenSum + "\n");
        bw.write("avg : " + String.format("%.1f",  oddAvg) + "\n");

        br.close();
        bw.flush();
        bw.close();
    }
}
