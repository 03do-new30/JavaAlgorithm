package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1486_등산 {
    private static class Node {
        int r;
        int c;
        int weight;
        public Node(int r, int c, int weight) {
            this.r = r;
            this.c = c;
            this.weight = weight;
        }

        public String toString() {
            return "(" + r + ", " + c + ", time:" + weight + ")";
        }
    }

    private static int N;
    private static int M;
    private static int T;
    private static int D;
    private static int[][] arr;
    private static int[] dr = {0, 0, -1, 1};
    private static int[] dc = {-1, 1, 0, 0};
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] tmpChar = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (Character.isLowerCase(tmpChar[j])) {
                    arr[i][j] = tmpChar[j] - 'a' + 26;
                } else {
                    arr[i][j] = tmpChar[j] - 'A';
                }
            }
        }

        // 세준이가 다녀올 수 있는 "가장 높은 곳"의 높이를 반환한다.
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < N * M; i++) {
            graph.add(new ArrayList<>());
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {

                int index = r * M + c;

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                        continue;
                    }

                    int time;
                    if (arr[r][c] >= arr[nr][nc]) {
                        time = 1;
                    } else {
                        time = (int)Math.pow(arr[nr][nc] - arr[r][c], 2);
                    }

                    if (time <= T) {
                        graph.get(index).add(new Node(nr, nc, time));
                    }
                }

            }
        }

        for (List<Node> x : graph) {
            bw.write(x + "\n");
        }

        // (0, 0)에서 도달할 수 있는 mountain의 위치를 저장한다.
        int[][] dist = new int[N*M][N*M];
        for (int i = 0; i < N*M; i++) {
            for (int j = 0; j < N*M; j++) {
                dist[i][j] = INF;
            }
        }
        dist[0][0] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((node1, node2) -> Integer.compare(node1.weight, node2.weight));

        for (int mountain = 1; mountain < N*M; mountain++) {

        }

        br.close();
        bw.flush();
        bw.close();
    }
}
