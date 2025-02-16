package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2003_수들의_합_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 투 포인터
        int left = 0; // inclusive
        int right = 0; // exclusive
        int sum = 0;
        int count = 0;
        while (true) {
            if (sum < M) {
                // 수를 더 더해줘야 한다.
                if (right < N) {
                    sum += arr[right++];
                } else {
                    break;
                }
            } else {
                // 수를 빼줘야 한다.
                sum -= arr[left++];
            }
            // 만약 sum == M이면 count를 늘려준다
            if (sum == M) {
                count++;
            }
        }
        System.out.println(count);
        br.close();
    }
}
