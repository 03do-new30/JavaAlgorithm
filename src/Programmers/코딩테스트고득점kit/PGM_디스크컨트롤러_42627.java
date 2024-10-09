package Programmers.코딩테스트고득점kit;

import java.util.PriorityQueue;

// 출처: https://easybrother0103.tistory.com/119
public class PGM_디스크컨트롤러_42627 {
    public static void main(String[] args) {
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        int result = 9;

        Solution sol = new Solution();
        System.out.println(sol.solution(jobs) == result);
    }

    static class Solution {
        public int solution(int[][] jobs) {
            int answer = 0;
            // wait 큐: 작업 시간 기준 우선순위 큐
            PriorityQueue<Job> wait = new PriorityQueue<>((o1, o2) -> o1.reqTime - o2.reqTime);

            // ready 큐: 소요 시간 기준 우선순위 큐
            PriorityQueue<Job> ready = new PriorityQueue<>((o1, o2) -> o1.duration - o2.duration);

            // jobs에 담긴 작업들을 Job으로 변환 뒤 wait에 추가
            for (int[] job : jobs) {
                wait.offer(new Job(job[0], job[1]));
            }

            int curTime = 0; // 현재시간
            int done = 0; // 완료된 작업 개수
            while (done < jobs.length) {

                // curTime 기준으로 수행할 수 있는 작업들을 ready 큐에 옮겨줌
                while (!wait.isEmpty() && wait.peek().reqTime <= curTime) {
                    ready.offer(wait.poll());
                }

                // ready 큐에 담긴 작업들 중 수행 시간이 가장 짧은 작업을 수행함
                // 만약 ready 큐가 비었다면 시간을 1초 증가시켜줌
                if (ready.isEmpty()) {
                    curTime++;
                    continue;
                }
                Job targetJob = ready.poll();
                int waitTime = curTime + targetJob.duration - targetJob.reqTime; // targetJob의 대기시간
                curTime += targetJob.duration;
                answer += waitTime;
                done++;
            }
            return answer / jobs.length;
        }

        static class Job {
            public int reqTime;
            public int duration;

            public Job(int reqTime, int duration) {
                this.reqTime = reqTime;
                this.duration = duration;
            }

            public String toString() {
                return "reqTime:" + reqTime + " duration:" + duration;
            }
        }
    }
}
