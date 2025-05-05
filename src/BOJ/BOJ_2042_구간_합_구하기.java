package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_2042_구간_합_구하기 {

    static int N, M, K;
    static long[] arr, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 초기 숫자 입력
        arr = new long[N + 1];
        for (int i =1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // 세그먼트트리 크기 지정
        tree = new long[4 * N];
        init(1, N, 1);

        for (int i = N + 2; i <= N + M + K + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long dif = c - arr[b];
                arr[b] = c;
                update(1, N ,1, b, dif);
            } else if (a == 2) {
                System.out.println(sum(1, N, 1, b, (int) c));
            }
        }

        br.close();
    }

    // start: 시작 인덱스, end: 끝 인덱스
    private static long init(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        // 재귀적으로 두 부분으로 나눈 뒤 그 합을 자기 자신으로 한다.
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    // 구간 합을 구하는 함수
    // start: 시작 인덱스, end: 끝 인덱스
    // left, right: 구간합의 범위
    private static long sum(int start, int end, int node, int left, int right) {
        // 범위 밖
        if (left > end || right < start) return 0;
        // 범위 안
        if (left <= start && end <= right) return tree[node];
        // 그렇지 않다면 두 부분으로 나누어 합을 구하기
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    // 특정 원소의 값을 수정하는 함수
    // 해당 원소를 포함하고 있는 모든 구간 합 노드를 갱신한다.
    // start: 시작 인덱스, end: 끝 인덱스
    // index: 구간 합을 수정하고자 하는 노드
    // dif: 수정할 값 (기존 값과의 차이)
    private static void update(int start, int end, int node, int index, long dif) {
        // 범위 밖
        if (index < start || index > end) return;
        // 범위 안에 있다면 다른 원소도 갱신
        tree[node] += dif;
        if (start == end) return;
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, dif);
        update(mid + 1, end, node * 2 + 1, index, dif);
    }
}
