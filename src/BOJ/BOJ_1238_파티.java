package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1238_파티 {
    static int N, M, X;
    static int[][] arr, revArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        arr = new int[N+1][N+1]; // 정방향 (a->b)
        revArr = new int[N+1][N+1]; // 역방향 (a<-b)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a =  Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a][b] = c;
            revArr[b][a] = c;
        }

        // X에서 모든 정점으로 가는 최단 거리를 구한다
        int[] fromX = dijkstra(X);
        // 그래프를 역방향으로 전환하고 다시 X에서 모든 정점으로 가는 최단 거리를 구한다
        arr = revArr;
        int[] toX = dijkstra(X);

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, fromX[i] + toX[i]);
        }
        System.out.println(answer);
        br.close();
    }

    private static int[] dijkstra(int start) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        pq.offer(new int[] {0, start}); // (cost, node)
        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            int node = info[1];
            for (int nextNode = 1; nextNode <= N; nextNode++) {
                if (arr[node][nextNode] == 0) continue;
                int newDist = dist[node] + arr[node][nextNode];
                if (dist[nextNode] > newDist) {
                    dist[nextNode] = newDist;
                    pq.offer(new int[] {newDist, nextNode});
                }
            }
        }
        return dist;
    }
}