package Programmers.코딩테스트고득점kit;

import java.sql.SQLOutput;
import java.util.*;

public class PGM_네트워크_43162 {
    public static void main(String[] args) {
        int[] n = {3, 3};
        int[][][] computers = {
                {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}},
                {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}
        };
        int[] result = {2, 1};
        for (int i = 0; i < result.length; i++) {
            Solution sol = new Solution();
            System.out.println(sol.solution(n[i], computers[i]) == result[i]);
        }
    }

    static class Solution {
        public int solution(int n, int[][] computers) {
            int answer = 0;

            boolean[] visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    bfs(i, n, computers, visited);
                    answer++;
                }
            }

            return answer;
        }

        private void bfs(int i, int n, int[][] computers, boolean[] visited){
            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            visited[i] = true;

            while(!q.isEmpty()) {
                int node = q.poll();
                for (int nextNode = 0; nextNode < n; nextNode++) {
                    if (node == nextNode) continue;
                    if (computers[node][nextNode] == 1 && !visited[nextNode]) {
                        q.offer(nextNode);
                        visited[nextNode] = true;
                    }
                }
            }
        }
    }
}

