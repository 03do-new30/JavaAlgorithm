package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_플로이드_11404 {

    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] dist = new int[n+1][n+1];

        // dist 값 초기화
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < n+1; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                }
                else {
                    dist[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < m; i ++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dist[a][b] = Integer.min(dist[a][b], c);

        }

        // Floyd-Warshall
        for (int mid = 0; mid < n+1; mid++) {
            for (int a = 0; a < n+1; a++) {
                for (int b = 0; b < n+1; b++) {
                    if (dist[a][mid] == INF || dist[mid][b] == INF) {
                        continue;
                    }
                    dist[a][b] = Integer.min(dist[a][b], dist[a][mid] + dist[mid][b]);
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if (dist[i][j] == INF) {
                    sb.append("0 ");
                } else {
                    sb.append(dist[i][j] + " ");
                }
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();

    }
}
