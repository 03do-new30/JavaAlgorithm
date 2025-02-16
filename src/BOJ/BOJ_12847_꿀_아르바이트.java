package BOJ;

import java.util.*;
import java.io.*;
public class BOJ_12847_꿀_아르바이트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken()); // 일을 할 수 있는 날 (고정)
        int[] money = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            money[i] = Integer.parseInt(st.nextToken());
        }

        // 슬라이딩 윈도우
        // 초기값 설정
        long sum = 0;
        for (int i = 0; i < m; i++) {
            sum += money[i];
        }

        long max = sum;
        for (int j = 0; j + m < n; j++) {
            sum -= money[j];
            sum += money[j+m];
            max = Math.max(max, sum);
        }
        System.out.println(max);


        br.close();
    }
}
