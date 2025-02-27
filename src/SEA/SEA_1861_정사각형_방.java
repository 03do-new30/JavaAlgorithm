package SEA;

import java.io.*;
import java.util.StringTokenizer;

public class SEA_1861_정사각형_방 {

    static int N;
    static int[][] arr;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder result = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int maxCnt = 0;
            int roomNo = N*N;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    int tmp = solve(r, c);
                    if (maxCnt < tmp) {
                        maxCnt = tmp;
                        roomNo = arr[r][c];
                    } else if (maxCnt == tmp) {
                        roomNo = Integer.min(roomNo, arr[r][c]);
                    }
                }
            }

            result.append("#").append(tc).append(" ").append(roomNo).append(" ").append(maxCnt).append("\n");

        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int solve(int r, int c) {
        int step = 1;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) { continue; }
            if (arr[r][c] + 1 != arr[nr][nc]) { continue; }
            int tmp = 1 + solve(nr, nc);
            if (step < tmp) {
                step = tmp;
            }
        }
        return step;
    }
}
