package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_2470_두_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        br.close();

        Arrays.sort(arr);
        int left = 0, leftIdx = 0;
        int right = N-1, rightIdx = N-1;
        long min = 1_000_000_000 * 2;
        while (leftIdx < rightIdx) {
            long cur = arr[leftIdx] + arr[rightIdx];

            if (min > Math.abs(cur)) {
                min = Math.abs(cur);
                left = leftIdx;
                right = rightIdx;
                if (cur == 0) break;
            }

            if (cur < 0) {
                leftIdx++;
            } else {
                rightIdx--;
            }
        }
        System.out.println(arr[left] + " " + arr[right]);
    }
}
