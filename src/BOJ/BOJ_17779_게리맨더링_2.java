package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17779_게리맨더링_2 {
    static int N;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        input();
        int ans = solve();
        System.out.println(ans);
    }

    static int solve() {
        int minDiff = Integer.MAX_VALUE;
        // 기준점을 중심으로 경계선을 나눈다.
        for (int x = 1; x < N+1; x++) {
            for (int y = 1; y < N+1; y++) {
                for (int d1 = 1; x + d1 <= N && 1 <= y - d1 ; d1++) {
                    for (int d2 = 1;
                         y + d2 <= N && x + d1 + d2 <= N && y - d1 + d2 <= N && y + d2 - d1 <= N;
                         d2++) {
                        int tmp = makeBorder(x, y, d1, d2);
                        if (tmp < minDiff) { minDiff = tmp; }
                    }
                }
            }
        }
        return minDiff;
    }

    static int makeBorder(int x, int y, int d1, int d2) {
        int[][] area = new int[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                area[i][j] = 5; // 5번 선거구로 채워둠
            }
        }

        // 경계선은 0으로 채운다.
        for (int i = 0; i <= d1; i++) {
            area[x+i][y-i] = 0;
        }
        for (int i = 0; i <= d2; i++) {
            area[x+i][y+i] = 0;
        }
        for (int i = 0; i <= d2; i++) {
            area[x+d1+i][y-d1+i] = 0;
        }
        for (int i = 0; i <= d1; i++) {
            area[x+d2+i][y+d2-i] = 0;
        }
        
        // 1번 구역 표시
        for (int r = 1; r < x + d1; r++) {
            for (int c = 1; c <= y; c++) {
                if (area[r][c] == 0) { break; }
                area[r][c] = 1;
            }
        }
        // 2번 구역 표시
        for (int r = 1; r <= x + d2; r++) {
            for (int c = N; c >= y+1; c--) {
                if (area[r][c] == 0) { break; }
                area[r][c] = 2;
            }
        }
        // 3번 구역 표시
        for (int r = x + d1; r <= N; r++) {
            for (int c = 1; c < y - d1 + d2; c++) {
                if (area[r][c] == 0) { break; }
                area[r][c] = 3;
            }
        }

        //4번 구역 표시
        for (int r = x + d2 + 1; r <= N; r++) {
            for (int c = N; c >= y - d1 + d2; c--) {
                if (area[r][c] == 0) { break; }
                area[r][c] =4;
            }
        }

        int[] sums = new int[6];
        for (int r = 1; r < N+1; r++) {
            for (int c = 1; c < N+1; c++) {
                if (area[r][c] == 0) {
                    sums[5] += arr[r][c];
                } else {
                    sums[area[r][c]] += arr[r][c];
                }
            }
        }

        int maxSum = Integer.MIN_VALUE;
        int minSum = Integer.MAX_VALUE;
        for (int i = 1; i <= 5; i++) {
            if (maxSum < sums[i]) { maxSum = sums[i]; }
            if (sums[i] < minSum) { minSum = sums[i]; }
        }
        return maxSum - minSum;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
    }
}
