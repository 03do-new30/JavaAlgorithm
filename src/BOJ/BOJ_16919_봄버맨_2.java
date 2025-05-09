package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16919_봄버맨_2 {

    private static int[] dr = {0, 0, -1, 1};
    private static int[] dc = {-1, 1, 0, 0};
    private static int R;
    private static int C;
    private static char[][] arr;

    private static class Point {
        int r;
        int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        // 3초에 터지는 지점들 저장
        List<Point> bombAt3Sec = new ArrayList<>();
        // 5초에 터지는 지점들 저장
        List<Point> bombAt5Sec = new ArrayList<>();
        boolean[][] visited = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (arr[r][c] == 'O') {
                    visited[r][c] = true;
                    bombAt3Sec.add(new Point(r, c));
                    for (int i = 0; i < 4; i++) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                            if (arr[nr][nc] == '.' && !visited[nr][nc]) {
                                visited[nr][nc] = true;
                            }
                        }
                    }
                }
            }
        }
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!visited[r][c]) {
                    bombAt5Sec.add(new Point(r, c));
                }
            }
        }

        // N에 맞게 출력
        if (N == 1) {
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    bw.write(arr[r][c]);
                }
                bw.write("\n");
            }
        } else {
            if (N % 2 == 0) {
                char[][] filledArr = getFilledArr();
                for (int r = 0; r < R; r++) {
                    for (int c = 0; c < C; c++) {
                        bw.write(filledArr[r][c]);
                    }
                    bw.write("\n");
                }
            } else {
                char[][] result;
                if (N % 4 == 3) {
                    result = getArr(bombAt3Sec);

                }
                else{ // N % 4 == 1
                    result = getArr(bombAt5Sec);
                }
                for (int r = 0; r < R; r++) {
                    for (int c = 0; c < C; c++) {
                        bw.write(result[r][c]);
                    }
                    bw.write("\n");
                }
            }
        }


        bw.flush();
        bw.close();
        br.close();
    }

    private static char[][] getFilledArr() {
        char[][] ret = new char[R][C];
        for (int r = 0; r < R; r++) {
            Arrays.fill(ret[r], 'O');
        }
        return ret;
    }

    private static char[][] getArr(List<Point> points) {
        char[][] ret = getFilledArr();
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            ret[p.r][p.c] = '.';
            for (int idx = 0; idx < 4; idx ++) {
                int nr = p.r + dr[idx];
                int nc = p.c + dc[idx];
                if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                    if (ret[nr][nc] == '.') { continue; }
                    ret[nr][nc] = '.';
                }
            }
        }
        return ret;
    }
}
