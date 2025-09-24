package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_6593_상범_빌딩 {
    static int L, R, C;
    static char[][][] arr;
    // 동, 서, 남, 북, 상, 하
    static int[] dl = {0, 0, 0, 0, -1, 1};
    static int[] dr = {0, 0, 1, -1, 0, 0};
    static int[] dc = {1, -1, 0, 0, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if (L == 0 && R == 0 && C == 0) { break; }
            arr = new char[L][R][C];
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    arr[i][j] = br.readLine().toCharArray();
                }
                br.readLine();
            }
            int result = bfs();
            if (result == -1) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in " + result + " minute(s).");
            }
        }
    }

    private static int bfs() {
        int startL = -1;
        int startR = -1;
        int startC = -1;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < R; j++) {
                for (int k = 0; k < C; k++) {
                    if (arr[i][j][k] == 'S') {
                        startL = i;
                        startR = j;
                        startC = k;
                        break;
                    }
                }
            }
        }
        boolean[][][] visited = new boolean[L][R][C];
        Queue<int[]> q = new ArrayDeque<>();
        visited[startL][startR][startC] = true;
        q.offer(new int[] {startL, startR, startC, 0});
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int l = tmp[0];
            int r = tmp[1];
            int c = tmp[2];
            int sec = tmp[3];
            if (arr[l][r][c] == 'E') return sec;
            for (int i = 0; i < 6; i++) {
                int nl = l + dl[i];
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nl < 0 || nr < 0 || nc < 0 ||
                nl >= L || nr >= R || nc >= C) {
                    continue;
                }
                if (visited[nl][nr][nc] || arr[nl][nr][nc] == '#') continue;
                visited[nl][nr][nc] = true;
                q.offer(new int[] {nl, nr, nc, sec + 1});
            }
        }
        return -1;
    }
}
