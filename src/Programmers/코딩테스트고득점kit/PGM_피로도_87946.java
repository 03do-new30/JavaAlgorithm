package Programmers.코딩테스트고득점kit;

public class PGM_피로도_87946 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        System.out.println(sol.solution(80, dungeons) == 3);
    }

    static class Solution {
        public int solution(int k, int[][] dungeons) {
            int answer = goDungeon(k, 0, dungeons, new boolean[dungeons.length]);
            return answer;
        }

        private int goDungeon(int k, int cnt, int[][] dungeons, boolean[] visited){
            int ret = 0;
            boolean moreDungeon = false;
            for (int i = 0; i < dungeons.length; i++) {
                if (visited[i]) continue;
                if (k >= dungeons[i][0]) {
                    moreDungeon = true;
                    visited[i] = true;
                    ret = Math.max(ret, goDungeon(k - dungeons[i][1], cnt + 1, dungeons, visited));
                    visited[i] = false;
                }
            }
            if (!moreDungeon) return cnt;
            return ret;
        }
    }
}
