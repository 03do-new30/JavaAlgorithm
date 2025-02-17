package SEA;

import java.util.*;
import java.io.*;

class SEA_3000_중간값_구하기
{
	private static final int MOD = 20171109;
	
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			
			// 왼쪽 n/2개
			PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder()); // maxHeap
			// 오른쪽 n/2 + 1개
			// 중간값: right의 원소들 중 가장 작은 것.
			PriorityQueue<Integer> right = new PriorityQueue<>(); // minHeap
			
			right.add(A);
			
			int answer = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				// n1, n2 중 작은 것을 왼쪽에, 큰 것을 오른쪽에 둔다.
				if (n1 <= n2) {
					left.add(n1);
					right.add(n2);
				} else {
					left.add(n2);
					right.add(n1);
				}
				// right의 최소값이 left의 최대값보다 작으면 두 수의 자리를 바꿔준다.
				if (left.peek() > right.peek()) {
					int fromRight = right.poll();
					int fromLeft = left.poll();
					left.add(fromRight);
					right.add(fromLeft);
				}
				// 중간값은 right의 최소값이다.
				answer = (answer + right.peek() % MOD) % MOD; 
			}
			System.out.println("#" + tc + " " + answer);
		}
		br.close();
	}
	
}
