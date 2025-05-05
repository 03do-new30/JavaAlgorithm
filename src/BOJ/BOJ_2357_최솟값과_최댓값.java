package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_2357_최솟값과_최댓값 {

    static int N, M;
    static int[] arr, minSeg, maxSeg;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        // 최소값 세그먼트트리
        minSeg = new int[4 * N];
        maxSeg = new int[4 * N];
        minInit(1, N, 1);
        maxInit(1, N, 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(getMin(1, N, 1, a, b) + " " + getMax(1, N, 1, a, b));
        }
        br.close();
    }

    private static int getMin(int start, int end, int node, int left, int right) {
        // 범위 밖
        if (left > end || right < start) return INF;
        // 범위 안
        if (left <= start && end <= right) return minSeg[node];
        // 그렇지 않다면 두 부분으로 나누어 최소값을 구하기
        int mid = (start + end) / 2;
        return Math.min(getMin(start, mid, node * 2, left, right),
                getMin(mid + 1, end, node * 2 + 1, left, right));
    }

    private static int getMax(int start, int end, int node, int left, int right) {
        // 범위 밖
        if (left > end || right < start) return 0;
        // 범위 안
        if (left <= start && end <= right) return maxSeg[node];
        // 그렇지 않다면 두 부분으로 나누어 최소값을 구하기
        int mid = (start + end) / 2;
        return Math.max(getMax(start, mid, node * 2, left, right),
                getMax(mid + 1, end, node * 2 + 1, left, right));
    }
    private static int minInit(int start, int end, int node) {
        if (start == end) return minSeg[node] = arr[start];
        int mid = (start + end) / 2;
        return minSeg[node] = Math.min(minInit(start, mid, node * 2), minInit(mid + 1, end, node * 2 + 1));
    }

    private static int maxInit(int start, int end, int node) {
        if (start == end) return maxSeg[node] = arr[start];
        int mid = (start + end) / 2;
        return maxSeg[node] = Math.max(maxInit(start, mid, node * 2), maxInit(mid + 1, end, node * 2 + 1));
    }
}
