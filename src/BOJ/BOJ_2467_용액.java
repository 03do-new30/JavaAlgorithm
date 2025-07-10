package BOJ;

import java.util.*;
import java.io.*;
public class BOJ_2467_용액 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, leftIdx = 0;
        int right = N-1, rightIdx = N-1;
        int min = 2_000_000_000;
        while (leftIdx < rightIdx && min > 0) {
            int current = arr[leftIdx] + arr[rightIdx];
            if (Math.abs(current) < min) {
                left = leftIdx;
                right = rightIdx;
                min = Math.abs(current);
            }

            if (current < 0) {
                // 음수라면 절대값을 더 작게 하기 위해 큰 숫자를 더해줘야한다
                leftIdx++;
            } else {
                rightIdx--;
            }
        }
        System.out.println(arr[left] + " " + arr[right]);
    }
}
