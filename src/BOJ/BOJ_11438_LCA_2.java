package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_11438_LCA_2 {
	static int N, M;
	static List<Integer>[] graph;
	static int[] depth;
	static int[][] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new List[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		// depth, parent 초기화
		depth = new int[N+1];
		parent = new int[N+1][21];
		dfs(1, 1, 0); // 루트가 1번이므로 1번 노드부터 탐색을 시작
		fillParents();
		
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(LCA(a, b));
		}
		br.close();
	}
	
	private static int LCA(int a, int b) {
		int depthA = depth[a];
		int depthB = depth[b];
		// a가 depth가 더 클 수 있도록 조정
		if (depthA < depthB) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		// depth를 맞춰준다.
		for (int i = 20; i >= 0; i--) {
			if (depth[a] - depth[b] >= (1<<i)) {
				a = parent[a][i]; // a를 쭉쭉 위로 끌어올린다.
			}
		}
		
		if (a == b) return a;
		
		// LCA 찾기
		for (int i = 20; i >= 0; i--) {
			if (parent[a][i] != parent[b][i]) {
				a = parent[a][i]; // a를 윗단계 노드로 갱신
				b = parent[b][i]; // b를 윗단계 노드로 갱신
			}
		}
		return parent[a][0];
		
	}
	
	// 나머지 parent 채워넣기
	private static void fillParents() {
		/*
		 * 어떤 노드의 2^k번째 조상은
		 * 그 노드의 2^(k-1)번째 조상의 2^(k-1)번째 조상이다!
		 */
		for (int step = 1; step <= 20; step++) {
			for (int node = 1; node <= N; node++) {
				parent[node][step] = parent[parent[node][step-1]][step-1];
			}
		}
	}
	
	private static void dfs(int curNode, int curDepth, int parentNode) {
		depth[curNode] = curDepth;
		for (int nextNode : graph[curNode]) {
			if (nextNode == parentNode) continue;
			parent[nextNode][0] = curNode;
			dfs(nextNode, curDepth + 1, curNode);
		}
	}
}
