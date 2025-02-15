package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_10835_카드게임 {

    static int N;
    static int[] left;
    static int[] right;
    static int[][] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        left = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            left[i] = Integer.parseInt(st.nextToken());
        }
        right = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            right[i] = Integer.parseInt(st.nextToken());
        }

        // memoization
        // memo[i][j] = 왼쪽 카드의 최상단이 i번째 카드, 오른쪽 카드의 최상단이 j번째 카드일 때 점수의 최대값 저장
        memo = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                memo[i][j] = -1; // empty
            }
        }

        int answer = solve(0, 0);
        bw.write(answer + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    // 왼쪽 카드의 최상단이 i번째 카드, 오른쪽 카드의 최상단이 j번째 카드일 때 점수의 최대값을 구하는 함수
    private static int solve(int i, int j) {
        if (i == N || j == N) { return 0; }

        if (memo[i][j] != -1) { return memo[i][j]; }

        // 왼쪽 카드만 버리는 경우, 둘 다 버리는 경우
        memo[i][j] = Integer.max(solve(i+1, j), solve(i+1, j+1));

        // 오른쪽 카드를 버리고 점수를 먹는 경우
        if (left[i] > right[j]) {
            memo[i][j] = Integer.max(memo[i][j], solve(i, j+1) + right[j]);
        }

        return memo[i][j];
    }
}
