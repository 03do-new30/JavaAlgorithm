package BOJ;

import java.util.*;

public class BOJ_21316_스피카 {

    final static int N = 12;
    static int[] degree;
    static boolean[] visited;
    static List<Integer>[] graph;

    public static void main(String[] args) {
        // 인접 리스트
        graph = new List[N+1];
        for (int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList<>(); // 그래프 초기화
        }

        Scanner sc = new Scanner(System.in);
        // 선분 정보 인접 리스트에 저장
        for (int i = 0; i < N; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        // spica의 조건
        // 1) spica의 차수는 3
        // 2) spica와 인접한 정점의 차수는 각각 1, 2, 3
        degree = new int[N+1];
        visited = new boolean[N+1];

        // 차수 구하기
        getDegree(1); // 1번 노드부터 시작
        
        // 정답 구하기
        int answer = 0;

        List<Integer> candidates = new ArrayList<>();
        for (int i = 1; i < N+1; i++) {
            if (degree[i] == 3) {
                candidates.add(i);
            }
        }

        for (int candidate : candidates) {
            int degree1 = 1;
            int degree2 = 1;
            int degree3 = 1;
            for (int adjacent : graph[candidate]) {
                switch (degree[adjacent]) {
                    case 1:
                        degree1--;
                        break;
                    case 2:
                        degree2--;
                        break;
                    case 3:
                        degree3--;
                        break;
                }
            }
            if (degree1 == 0 && degree2 == 0 && degree3 == 0) {
                answer = candidate;
                break;
            }
        }
        System.out.println(answer);

        sc.close();
    }

    private static void getDegree(int node) {
        visited[node] = true;
        degree[node] += graph[node].size();
        for (int nextNode : graph[node]) {
            if (!visited[nextNode]) {
                getDegree(nextNode);
            }
        }
    }
}
