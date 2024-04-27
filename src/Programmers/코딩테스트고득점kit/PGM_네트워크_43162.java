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
                visited[i] = false;
            }
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    bfs(i, computers, visited);
                    answer++;
                }
            }
            return answer;
        }

        public void bfs(int computer, int[][] computers, boolean[] visited) {
            int n = visited.length;
            Queue<Integer> q = new LinkedList<>();
            q.add(computer);
            visited[computer] = true;
            while (!q.isEmpty()) {
                int current = q.poll();
                for (int i = 0; i < n; i++) {
                    if (i == current)
                        continue;
                    if (computers[current][i] == 1) {
                        if (visited[i])
                            continue;
                        visited[i] = true;
                        q.add(i);
                    }
                }
            }
        }
    }
}

