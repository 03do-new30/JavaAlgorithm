package SEA;

import java.util.*;
import java.io.*;

public class SEA_2112_보호_필름 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int D, W, K, minCnt;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            input();
            minCnt = Integer.MAX_VALUE;
            dfs(0, 0);
            System.out.println("#" + tc + " " + minCnt);
        }
        br.close();
    }

    static void dfs(int cnt, int startRow) {

        if (cnt >= minCnt) { return; }

        if (isAllPass()) {
            minCnt = cnt;
            return;
        }

        for (int row = startRow; row < D; row++) {

            int[] original = new int[W];
            for (int col = 0; col < W; col++) {
                original[col] = arr[row][col];
            }

            // 약품 0
            Arrays.fill(arr[row], 0);
            dfs(cnt + 1, row + 1);


            // 약품 1
            Arrays.fill(arr[row], 1);
            dfs(cnt + 1, row + 1);


            // backtracking
            for (int col = 0; col < W; col++) {
                arr[row][col] = original[col];
            }
        }
    }

    static boolean isAllPass() {
        for (int col = 0; col < W; col++) {
            if (!pass(col)) { return false; }
        }
        return true;
    }
    static boolean pass(int col) {
        int streak = 1;
        if (streak == K) { return true; } // 헉스

        for (int r = 1; r < D; r++) {
            if (arr[r][col] == arr[r-1][col]) {
                streak++;
                if (streak == K) {
                    return true;
                }
            }else {
                streak = 1;
            }
        }
        return false;
    }


    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[D][W];
        for (int i = 0; i < D; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                arr[i][j] = st.nextToken().charAt(0) - '0';
            }
        }
    }


}
