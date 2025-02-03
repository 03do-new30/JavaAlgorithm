package SEA;

import java.util.*;
import java.io.*;

public class SEA_1226_미로1 {

    private static final int T = 10;
    private static final int N = 16;
    private static int[][] arr;
    private static int[] dr = {0, 0, -1, 1};
    private static int[] dc = {-1, 1, 0, 0};

    private static class Point {
        int r;
        int c;

        private Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int test_case = 1; test_case <= T; test_case++) {
            int tc = Integer.parseInt(br.readLine());

            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(str.substring(j, j+1));
                }
            }

            // 출발점
            Point start = null;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == 2) {
                        start = new Point(i, j);
                        break;
                    }
                }
            }

            boolean result = bfs(start);
            bw.write("#" + test_case + " " + (result?1:0) + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static boolean bfs(Point start) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.offer(start);
        visited[start.r][start.c] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();
            int r = point.r;
            int c = point.c;
            if (arr[r][c] == 3) {
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }
                if (visited[nr][nc]) {
                    continue;
                }
                if (arr[nr][nc] == 1) {
                    continue;
                }
                visited[nr][nc] = true;
                q.offer(new Point(nr, nc));
            }
        }

        return false;
    }
}