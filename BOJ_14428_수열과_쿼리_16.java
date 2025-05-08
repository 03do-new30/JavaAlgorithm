package BOJ;
import java.util.*;
import java.io.*;

public class BOJ_14428_수열과_쿼리_16 {
	
	static class Node implements Comparable<Node> {
		int idx, val;
		public Node(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
		@Override
		public int compareTo(Node o) {
			if (this.val == o.val) {
				return Integer.compare(this.idx, o.idx);
			}
			return Integer.compare(this.val, o.val);
		}
		@Override
		public String toString() {
			return "(idx:" + this.idx + ", val:" + this.val + ")";
		}
		
	}
	
	static int N, M;
	static int[] A;
	static Node[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		// segment tree
		tree = new Node[N*4];
		init(1, N, 1);
		// query
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (cmd == 1) {
				A[a] = b;
				update(1, N, 1, a, b);
			} else {
				System.out.println(getAnswer(1, N, 1, a, b).idx);
			}
		}
		br.close();
	}
	
	private static Node getAnswer(int start, int end, int node, int left, int right) {
		// 범위를 벗어나는 경우
		if (left > end || right < start) return new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);
		// 범위 내에 있는 경우 바로 리턴
		if (left <= start && end <= right) return tree[node];
		int mid = (start + end) / 2;
		Node leftNode = getAnswer(start, mid, node * 2, left, right);
		Node rightNode = getAnswer(mid + 1, end, node * 2 + 1, left, right);
		return leftNode.compareTo(rightNode) <= 0 ? leftNode : rightNode;
	}
	
	private static void update(int start, int end, int node, int idx, int newVal) {
		if (idx < start || idx > end) return;
		if (start == end) {
			tree[node] = new Node(idx, newVal);
			return;
		}
		int mid = (start + end) / 2;
		update(start, mid, node * 2, idx, newVal);
		update(mid + 1, end, node * 2 + 1, idx, newVal);
		tree[node] = tree[node*2].compareTo(tree[node*2+1]) <= 0 ? tree[node*2] : tree[node*2+1];
	}
	
	private static Node init(int start, int end, int node) {
		if (start == end) return tree[node] = new Node(start, A[start]);
		int mid = (start + end) / 2;
		Node node1 = init(start, mid, node * 2);
		Node node2 = init(mid + 1, end, node * 2 + 1);
		Node minNode = (node1.compareTo(node2) <= 0) ? node1 : node2;
		return tree[node] = minNode;
	}
}
