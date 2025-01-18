package PGM.코딩테스트고득점kit;

public class PGM_전력망을둘로나누기_86971 {
    public static void main(String[] args) {
        int[] n = {9, 4, 7};
        int[][][] wires = { {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}}, {{1,2},{2,3},{3,4}}, {{1,2},{2,7},{3,7},{3,4},{4,5},{6,7}} };
        int[] result = {3, 0, 1};

        Solution sol = new Solution();

        for (int i = 0; i < result.length; i++) {
            System.out.println("#" + i);
            System.out.println(sol.solution(n[i], wires[i]) == result[i]);
        }
    }

    static class Solution {
        public int solution(int n, int[][] wires) {
            // 송전탑 개수의 차이가 작을수록 송전탑의 개수가 비슷하다.
            int answer = 1000;
            for (int i = 0; i < wires.length; i++) {
                answer = Math.min(answer, cut(n, wires, i));
            }
            return answer;
        }

        // idx번째 전선을 끊을 때
        private int cut(int n, int[][] wires, int idx) {
            boolean[][] graph = new boolean[n+1][n+1];
            for (int i = 0; i < wires.length; i++) {
                if (i == idx) continue; // idx번째 전선은 스킵하고 그래프 생성
                int v1 = wires[i][0];
                int v2 = wires[i][1];
                graph[v1][v2] = graph[v2][v1] = true;
            }

            boolean[] visited = new boolean[n+1];
            findNetwork(1, visited, graph);

            // 네트워크 1에 속한 송전탑 개수
            int network1 = 0;
            for (boolean x : visited) {
                if (x) network1++;
            }
            int network2 = n - network1;
            return Math.abs(network1 - network2);
        }

        // target 송전탑과 연결된 전력망들을 찾는다
        private void findNetwork(int target, boolean[] visited, boolean[][] graph){
            visited[target] = true;
            for (int nextTarget = 1; nextTarget < graph.length; nextTarget++) {
                if (!visited[nextTarget] && graph[target][nextTarget]) {
                    findNetwork(nextTarget, visited, graph);
                }
            }
        }
    }
}
