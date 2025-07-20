package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_2056_작업 {
    static int N;
    static int[] indegree; // 차수 저장
    static int[] time; // 시간 저장
    static List<Integer>[] adjList; // 인접 리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        indegree = new int[N+1];
        time = new int[N+1];
        adjList = new List[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int prevId = Integer.parseInt(st.nextToken());
                adjList[prevId].add(i);
                ++indegree[i];
            }
        }
        int ans = topologicalSort();
        System.out.println(ans);
        br.close();
    }

    private static int topologicalSort() {

        Queue<Integer> q = new ArrayDeque<>();
        int[] visitTime = new int[N+1];

        for (int i = 1; i <= N; i++) {
            visitTime[i] = time[i];
            if (indegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int current = q.poll();
            for (int next : adjList[current]) {
                visitTime[next] = Math.max(visitTime[next], visitTime[current] + time[next]);
                if (--indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        int ret = 0;
        for (int i = 1; i <= N; i++) {
            ret = Math.max(ret, visitTime[i]);
        }
        return ret;
    }

}
