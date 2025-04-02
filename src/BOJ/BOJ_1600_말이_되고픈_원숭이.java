package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_1600_말이_되고픈_원숭이 {

    static int K, W, H;
    static int[][] arr;
    static int[][][] count;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int[] hr = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] hc = {-2, -1, 1, 2, -2, -1, 1, 2};

    static class Info {
        int r, c, horseCnt;

        public Info(int r, int c, int horseCnt) {
            this.r = r;
            this.c = c;
            this.horseCnt = horseCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();
        br.close();
    }

    static void bfs() {
        count = new int[H][W][K+1];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                for (int k = 0; k < K+1; k++) {
                    count[i][j][k] = -1;
                }
            }
        }
        Queue<Info> q = new ArrayDeque<>();
        count[0][0][0] = 0;
        q.offer(new Info(0, 0, 0));

        while (!q.isEmpty()) {
            Info info = q.poll();
            if (info.r == H-1 && info.c == W-1) {
                System.out.println(count[info.r][info.c][info.horseCnt]);
                return;
            }
            if (info.horseCnt < K) {
                for (int i = 0; i < 8; i++) {
                    int nr = info.r + hr[i];
                    int nc = info.c + hc[i];
                    if (!isInRange(nr, nc) ||
                            arr[nr][nc] == 1 ||
                            count[nr][nc][info.horseCnt + 1] > -1) {
                        continue;
                    }
                    count[nr][nc][info.horseCnt + 1] = count[info.r][info.c][info.horseCnt] + 1;
                    q.offer(new Info(nr, nc, info.horseCnt + 1));
                }
            }
            for (int i = 0; i< 4; i++) {
                int nr = info.r + dr[i];
                int nc = info.c + dc[i];
                if (!isInRange(nr, nc) || arr[nr][nc] == 1 || count[nr][nc][info.horseCnt] > -1) {
                    continue;
                }
                count[nr][nc][info.horseCnt] = count[info.r][info.c][info.horseCnt] + 1;
                q.offer(new Info(nr, nc, info.horseCnt));
            }
        }
        System.out.println(-1);
    }

    static boolean isInRange(int r, int c) {
        return 0 <= r && r < H && 0 <= c && c < W;
    }
}
