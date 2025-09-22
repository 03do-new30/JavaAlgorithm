package BOJ;

import java.sql.Array;
import java.util.*;
import java.io.*;
public class BOJ_18352_특정_거리의_도시_찾기 {
    static int N, M, K, X;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        graph = new List[N+1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
        }

        // BFS
        int[] dist = new int[N+1];
        for (int i = 0; i <= N; i++) {
            dist[i] = -1;
        }
        dist[X] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(X);
        while(!q.isEmpty()) {
            int prev = q.poll();
            if (dist[prev] == K) continue; // K보다 더 멀리는 큐에 안 넣음
            for (int next : graph[prev]) {
                if (dist[next] != -1) continue;
                dist[next] = dist[prev] + 1;
                q.offer(next);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                sb.append(i).append("\n");
            }
        }
        System.out.println(sb.length() > 0 ? sb.toString() : "-1\n");
        br.close();
    }
}
