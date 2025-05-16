package BOJ;
import java.util.*;
import java.io.*;

public class BOJ_3015_오아시스_재결합 {
	static int N;
	static class Node {
		int height;
		int count;
		public Node(int height, int count) {
			this.height = height;
			this.count = count;
		}
		@Override
		public String toString() {
			return "[height:" + height + ", count:" + count + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Deque<Node> stack = new ArrayDeque<>();
		long pair = 0;// 쌍의 수
		for (int i = 0; i < N; i++) {
			Node current = new Node(Integer.parseInt(br.readLine()), 1);
			// 새로 들어온 current가 기존에 줄서있던 사람을 가리는 경우
			while (!stack.isEmpty() && stack.peek().height <= current.height) {
				Node prev = stack.pop();
				pair += prev.count;
				if (prev.height == current.height) {
					current.count += prev.count; // 같은 키를 가진 사람 수를 누적
				}
			}
			
			// current보다 키가 큰 사람과 짝
			if (!stack.isEmpty()) {
				pair++;
			}
			
			stack.push(current);
		}
		System.out.println(pair);
		br.close();
	}
}
