package PGM.코딩테스트고득점kit;

import java.util.*;

public class PGM_프로세스_42587 {
    public static void main(String[] args) {
        int[][] priorities = {{2, 1, 3, 2}, {1, 1, 9, 1, 1, 1}};
        int[] location = {2, 0};
        int[] result = {1, 5};

        Solution sol = new Solution();

        for (int i = 0; i < result.length; i++) {
            System.out.println("i = " + i);
            System.out.println(
                    sol.solution(priorities[i], location[i])
                == result[i]
            );
        }
    }
    static class Solution {
        public int solution(int[] priorities, int location) {
            int maxPriority = Arrays.stream(priorities).max().orElse(0);
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < priorities.length; i++) {
                q.add(i);
            }
            // 실행 순서
            int processOrder = 0;
            while (!q.isEmpty()) {
                int idx = q.poll();
                if (priorities[idx] == maxPriority) {
                    // 실행 순서 증가
                    processOrder++;
                    // priorities 배열에서 갖는 값 0으로 수정
                    priorities[idx] = 0;
                    // maxPriority 갱신
                    maxPriority = Arrays.stream(priorities).max().orElse(0);
                    // 만약 idx == location이라면 리턴
                    if (idx == location) {
                        return processOrder;
                    }
                } else {
                    // 다시 q에 삽입
                    q.add(idx);
                }
            }
            return processOrder;
        }
    }
}
