package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_3055_탈출 {

    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    private static char[][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int r;
        int c;
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new char[r][c];
        for (int i = 0; i < r; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        Point start = new Point(-1, -1); // D(비버 굴)의 위치 저장
        Point destination = new Point(-1, -1); // S(고슴도치)의 위치 저장
        List<Point> pools = new ArrayList<>(); // *(웅덩이)의 위치 저장

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (arr[i][j] == 'S') {
                    start = new Point(i, j);
                } else if (arr[i][j] == 'D') {
                    destination = new Point(i, j);
                } else if (arr[i][j] == '*') {
                    pools.add(new Point(i, j));
                }
            }
        }

        // floodTime[i][j] = (i, j) 위치에 물이차는 시간 기록
        int[][] floodTime = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                floodTime[i][j] = -1; // -1로초기화
            }
        }
        checkFloodTime(floodTime, pools);


        // 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간을 출력
        int ret = getMinTime(start, destination, floodTime);
        // 안전하게 비버의 굴로 이동할 수없다면 "KAKTUS"
        bw.write((ret == -1 ? "KAKTUS" : ret) + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    private static void checkFloodTime(int[][] floodTime, List<Point> pools) {

        int n = arr.length;
        int m = arr[0].length;

        Queue<Point> q = new LinkedList<>();

        for (Point p : pools) {
            q.offer(p);
            floodTime[p.x][p.y] = 0;
        }

        while (!q.isEmpty()) {
            Point current = q.poll();
            int x = current.x;
            int y = current.y;
            int time = current.time;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (floodTime[nx][ny] > -1) {
                    continue;
                }
                if (arr[nx][ny] == '.') {
                    floodTime[nx][ny] = time + 1;
                    q.offer(new Point(nx, ny, time + 1));
                }
            }
        }
    }

    private static int getMinTime(Point start, Point destination, int[][] floodTime) {

        int n = arr.length;
        int m = arr[0].length;

        Queue<Point> q = new LinkedList<>();
        q.offer(start);
        boolean[][] visited = new boolean[n][m];
        visited[start.x][start.y] = true;

        while (!q.isEmpty()) {
            Point current = q.poll();
            int x = current.x;
            int y = current.y;
            int time = current.time;

            if (x == destination.x && y == destination.y) {
                return time;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (floodTime[nx][ny] > -1 && floodTime[nx][ny] <= time + 1) {
                    continue;
                }
                if (arr[nx][ny] != 'X') {
                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny, time + 1));
                }
            }
        }
        return -1;
    }
}

class Point {
    int x;
    int y;
    int time;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        time = 0;
    }

    public Point(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}

