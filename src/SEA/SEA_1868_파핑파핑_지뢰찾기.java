package SEA;

import java.io.*;
import java.util.*;

public class SEA_1868_파핑파핑_지뢰찾기 {

    private static int[] dr = {0, 0, -1, 1, -1, -1, 1, 1};
    private static int[] dc = {-1, 1, 0, 0, -1, 1, -1, 1};

    // 지도에 숫자를 채워넣는 함수
    private static void fillHints(int[][] arr) {
        int n = arr.length;

        for (int r = 0; r < n; r ++) {
            for (int c = 0; c < n; c++) {
                if (arr[r][c] == -1) { // 지뢰
                    continue;
                }

                // 주변 8칸을 검사
                int cnt = 0;
                for (int i = 0; i < 8; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    if (arr[nr][nc] == -1) {
                        cnt++;
                    }
                }

                arr[r][c] = cnt;
            }
        }
    }

    private static void bfs(int[][] arr, boolean[][] visited, int r, int c) {
        int n = arr.length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] rc = q.poll();
            r = rc[0];
            c = rc[1];

            if (arr[r][c] == 0) {
                for (int i = 0; i < 8; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                        continue;
                    }
                    if (visited[nr][nc] || arr[nr][nc] == -1) continue;
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String tmp = st.nextToken();
                for (int j = 0; j < n; j++) {
                    // 빈칸 = 0, 지뢰 = -1
                    arr[i][j] = tmp.charAt(j) == '.'? 0 : -1;
                }
            }

            // 각 지도에 표시될 숫자를 먼저 적는다.
            fillHints(arr);

            int cnt = 0;
            boolean[][] visited = new boolean[n][n];

            // 1. 0인 칸 기준으로 BFS를 수행하며 공개할 수 있는 모든 칸 표시
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (arr[r][c] == 0 && !visited[r][c]) {
                        bfs(arr, visited, r, c);
                        cnt++;
                    }
                }
            }

            // 2. 남은 칸들의 개수를 더해준다.
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (!visited[r][c] && arr[r][c] >= 0) {
                        cnt++;
                    }
                }
            }

            bw.write("#" + test_case + " " + cnt + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
