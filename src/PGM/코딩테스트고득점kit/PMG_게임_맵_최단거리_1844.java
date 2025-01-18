package PGM.코딩테스트고득점kit;

import java.util.*;

public class PMG_게임_맵_최단거리_1844 {
    public static void main(String[] args) {
        int[][][] maps = {
                {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}},
                {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}}
        };
        int[] answer = {11, -1};
        for (int i = 0; i < answer.length; i++) {
            System.out.println(
                    new Solution().solution(maps[i]) == answer[i]
            );
        }
    }

    static class Solution {
        public int solution(int[][] maps) {
            int n = maps.length;
            int m = maps[0].length;
            boolean[][] visited = new boolean[n][m];
            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            // BFS
            Queue<Pair> q = new LinkedList<>();
            q.add(new Pair(0, 0, 1));
            visited[0][0] = true;
            while (!q.isEmpty()) {
                Pair pair = q.poll();
                if (pair.r == (n-1) && pair.c == (m-1))
                    return pair.cnt;
                for (int i = 0; i < 4; i++) {
                    int nr = pair.r + dr[i];
                    int nc = pair.c + dc[i];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m)
                        continue;
                    if (visited[nr][nc])
                        continue;
                    if (maps[nr][nc] == 0)
                        continue;
                    // 지나갈 수 있음
                    Pair newPair = new Pair(nr, nc, pair.cnt + 1);
                    q.add(newPair);
                    visited[nr][nc] = true;
                }
            }
            return -1;
        }
    }

    static class Pair {
        int r, c, cnt;
        Pair(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}
