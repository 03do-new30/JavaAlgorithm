package BOJ;
import java.util.*;
import java.io.*;

public class BOJ_2493_탑 {
	static int N;
	static class Node implements Comparable<Node>{
		int idx;
		int height;
		public Node(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.height, o.height);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] result = new int[N+1];
		Deque<Node> stack = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			Node current = new Node(i, Integer.parseInt(st.nextToken()));
			while (!stack.isEmpty() && stack.peek().height < current.height) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				result[i] = 0;
			} else {
				result[i] = stack.peek().idx;
			}
			stack.push(current);
		}
		for (int i = 1; i <= N; i++) {
			System.out.print(result[i] + " ");
		}
		br.close();
	}
}
