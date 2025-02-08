package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1806_부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        // 합이 S 이상이 되는 것 중 가장 짧은 것의 길이를 구한다.
        // 투포인터
        int left = 1;
        int right = 1;
        int currentSum = arr[1];
        int answer = N + 1;
        while (right < N + 1) {
//            bw.write("left:" + left +", right:" + right + ", currentSum:" + currentSum + "\n");

            if (currentSum >= S) {
                // 현재 길이를 저장해둔다.
                answer = Integer.min(answer, right - left + 1);
                // 범위를 줄여본다.
                currentSum -= arr[left++];
            } else {
                // 범위를 늘려본다.
                if (right + 1 >= N+1) break;
                currentSum += arr[++right];
            }
        }
        if (answer == N+1) {
            answer = 0;
        }
        bw.write(answer + "\n");

        br.close();
        bw.flush();
        bw.close();
    }
}
