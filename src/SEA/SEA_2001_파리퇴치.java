package SEA;

import java.util.*;
import java.io.*;

public class SEA_2001_파리퇴치 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // 누적합을 만들기 위해 위쪽, 왼쪽 모서리를 0으로 처리해준다.
            int[][] arr = new int[n+1][n+1];

            for (int i = 1; i < n+1; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j < n+1; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // prefixSum[i][j] = 좌상단 좌표가(0, 0)이고 우하단 좌표가 (i, j)인 사각형 범위의 누적합
            int[][] prefixSum = getPrefixSum(arr);

            // 누적합을 통해 파리채로 잡을 수 있는 파리의 최대 마리수를 구한다.
            int answer = 0;
            for (int r = 1; r < n+1; r++) {
                for (int c = 1; c < n+1; c++) {
                    int nr = r + m - 1;
                    int nc = c + m - 1;
                    if (nr < 0 || nr >= n+1 || nc < 0 || nc >= n+1) {
                        continue;
                    }
                    // 파리채로 잡을 수 있는 구간의 파리 마릿수 구하기
                    int tmp = prefixSum[nr][nc] - prefixSum[r-1][nc] - prefixSum[nr][c-1] + prefixSum[r-1][c-1];
                    if (answer < tmp) {
                        answer = tmp;
                    }
                }
            }

            bw.write("#" + test_case + " " + answer + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static int[][] getPrefixSum(int[][] arr) {
        int n = arr.length;

        // prefixSum[i][j] = arr에서 좌상단 좌표가(0, 0)이고 우하단 좌표가 (i, j)인 사각형 범위의 누적합
        int[][] prefixSum = new int[n][n];

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                prefixSum[i][j] = prefixSum[i][j-1] + prefixSum[i-1][j] - prefixSum[i-1][j-1] + arr[i][j];
            }
        }

        return prefixSum;
    }

}