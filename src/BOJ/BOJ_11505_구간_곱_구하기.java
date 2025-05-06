package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_11505_구간_곱_구하기 {

    static final long MOD = 1_000_000_007;
    static int N, M, K;
    static int[] arr;
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        // segment tree
        tree = new long[4 * N];
        init(1, N, 1);
        for (int i  = N+2; i <= N+M+K+1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                arr[b] = c;
                update(1, N, 1, b, c);
            } else {
                System.out.println(product(1, N, 1, b, c));
            }
        }
        br.close();
    }
    
    // 업데이트되는 노드를 포함하는 구간을 모두 다시 계산하는 방식
    private static void update(int start, int end, int node, int index, long value) {
        if (index < start || index > end) return;
        if (start == end) {
            tree[node] = value % MOD;
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, value);
        update(mid + 1, end, node * 2 + 1, index, value);
        tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD;
    }

    private static long product(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return 1; // 곱셈의 항등원
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return (product(start, mid, node * 2, left, right)
                * product(mid + 1, end, node * 2 + 1, left, right)) % MOD;
    }

    private static long init(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start] % MOD;
        int mid = (start + end) / 2;
        return tree[node] = (init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1)) % MOD;
    }
}