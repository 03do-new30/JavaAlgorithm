package SEA;

import java.util.*;
import java.io.*;

public class SEA_1954_달팽이_숫자 {

    private static int[] dr = {0, 1, 0, -1};
    private static int[] dc = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[n][n];

            int cnt = 1;
            int r = 0, c = 0;
            int idx = 0;

            while (cnt <= n*n) {
                arr[r][c] = cnt++;
                int nr = r + dr[idx];
                int nc = c + dc[idx];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n || arr[nr][nc] != 0) {
                    idx = (idx + 1) % 4;
                    nr = r + dr[idx];
                    nc = c + dc[idx];
                }

                r = nr;
                c = nc;
            }

            bw.write("#" + test_case + "\n");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    bw.write(arr[i][j] + " ");
                }
                bw.write("\n");
            }

        }

        br.close();
        bw.flush();
        bw.close();
    }
}
