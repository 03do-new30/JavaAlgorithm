package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_20125_쿠키의_신체_측정 {

    static int N;
    static char[][] arr;
    static int heartR = -1, heartC = -1, leftArm, rightArm, waist, leftLeg, rightLeg;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    public static void main(String[] args) throws Exception {
        input();
        findHeart();
        measure();
        System.out.println(heartR + " " + heartC);
        System.out.println(leftArm + " " + rightArm + " " + waist + " " + leftLeg + " " + rightLeg);
    }

    private static void measure() {
        boolean[][] visited = new boolean[N+1][N+1];
        for (int c = 1; c <= N; c++) {
            if (arr[heartR][c] == '*') {
                if (c < heartC) {
                    leftArm++;
                }
                else if (c > heartC) {
                    rightArm++;
                }
                visited[heartR][c] = true;
            }
        }
        for (int r = heartR + 1; r <= N; r++) {
            if (arr[r][heartC] == '*') waist++;
            visited[r][heartC] = true;
        }

        for (int r = 1; r <= N; r++) {
            for (int c = 1 ; c <= N; c++) {
                if (visited[r][c] || arr[r][c] == '_') continue;
                if (c < heartC) {
                    leftLeg++;
                }
                else if (c > heartC) {
                    rightLeg++;
                }
                visited[r][c] = true;
            }
        }
    }

    private static void findHeart() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (arr[r][c] == '*') {
                    // 상하좌우에 모두 *이 있어야 심장이다.
                    int cnt = 0;
                    for (int i = 0; i < 4; i++) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if (1 <= nr && nr <= N && 1 <= nc && nc <= N) {
                            if (arr[nr][nc] == '*') cnt++;
                        }
                    }
                    if (cnt == 4) {
                        heartR = r;
                        heartC = c;
                        return;
                    }
                }
            }
        }
    }

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            String tmp = ' ' + br.readLine();
            for (int j = 1; j <= N; j++) {
                arr[i][j] = tmp.charAt(j);
            }
        }
        br.close();
    }
}
