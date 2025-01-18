package SEA;

import java.util.*;

public class SEA_4193_수영대회_결승전 {

    private static int[] dr = {0, 0, -1, 1};
    private static int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();
            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            int startR = sc.nextInt();
            int startC = sc.nextInt();
            int goalR = sc.nextInt();
            int goalC = sc.nextInt();

            int answer = bfs(n, arr, startR, startC, goalR, goalC);
            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static int bfs(int n, int[][] arr, int startR, int startC, int goalR, int goalC) {
        boolean[][] visited = new boolean[n][n];
        Queue<Info> q = new LinkedList<>();
        visited[startR][startC] = true;
        q.offer(new Info(startR, startC, 0));

        while (!q.isEmpty()) {
            Info info = q.poll();
            int r = info.r;
            int c = info.c;
            int time = info.time;

            if (r == goalR && c == goalC) {
                return time;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >=n) continue;
                if (visited[nr][nc]) continue;
                if (arr[nr][nc] == 1) continue;

                if (arr[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    q.offer(new Info(nr, nc, time + 1));
                }
                else {
                    if (time % 3 == 2) { // 이동 가능
                        visited[nr][nc] = true;
                        q.offer(new Info(nr, nc, time + 1));
                    } else { // 대기
                        q.offer(new Info(r, c, time + 1));
                    }
                }
            }
        }
        return -1;
    }
}

class Info {
    int r;
    int c;
    int time;
    public Info(int r, int c, int time) {
        this.r = r;
        this.c = c;
        this.time = time;
    }
}