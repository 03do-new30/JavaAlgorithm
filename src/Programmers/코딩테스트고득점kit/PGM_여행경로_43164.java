package Programmers.코딩테스트고득점kit;

import java.util.*;

public class PGM_여행경로_43164 {
    public static void main(String[] args) {
        String[][][] tickets = {
                {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}},
                {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}}
        };
        String[][] result = {{"ICN", "JFK", "HND", "IAD"}, {"ICN", "ATL", "ICN", "SFO", "ATL", "SFO"}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(
                    Arrays.equals(new Solution().solution(tickets[i]), result[i])
            );
        }
    }
    static class Solution {
        boolean[] visited;
        ArrayList<String> allRoute;
        public String[] solution(String[][] tickets) {
            String[] answer = {};
            int cnt = 0;
            visited = new boolean[tickets.length];
            allRoute = new ArrayList<>();

            dfs("ICN", "ICN", tickets, cnt);
            Collections.sort(allRoute);
            answer = allRoute.get(0).split(" ");
            return answer;
        }

        public void dfs(String start, String route, String[][] tickets, int cnt) {
            if (cnt == tickets.length) {
                allRoute.add(route);
                return;
            }
            for (int i = 0; i < tickets.length; i++) {
                if (start.equals(tickets[i][0]) && !visited[i]){
                    visited[i] = true;
                    dfs(tickets[i][1], route + " " + tickets[i][1], tickets, cnt + 1);
                    visited[i] = false;
                }
            }
        }
    }
}
