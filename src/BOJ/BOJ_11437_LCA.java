package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_11437_LCA {
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
		// LCA 알고리즘을 수행하기 전, 초기화
		depth = new int[N+1];
		parent = new int[N+1][21]; //parent[i][j] = i번째 노드의 2^j번째 조상을 저장
		dfs(1, 1, 0);
		fillParents();
		// M개의 쿼리
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(LCA(a, b));
		}
		br.close();
	}
	
	// LCA를 구해준다
	private static int LCA(int a, int b) {
		int depthA = depth[a];
		int depthB = depth[b];
		if (depthA < depthB) { // a를 더 depth가 깊은 쪽으로 맞춰준다.
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		// 높이를 동등하게 맞춰준다.
		for (int i = 20; i >= 0; i--) {
			if (depth[a] - depth[b] >= (1<<i)) { // a가 b보다 최소 2^i만큼 더 깊으면, a를 2^i칸 위로 올려!
				a = parent[a][i];
			}
		}
		
		// 높이를 맞췄는데 공통 조상인 경우
		if (a == b) return a;
		
		// LCA 찾기
		// a와 b의 공통 조상을 찾기 위해
		// 2^i씩 위로 점프하면서, 공통 조상 바로 아래 지점까지 끌어올리는 과정
		for (int i = 20; i >= 0; i--) {
			if (parent[a][i] == parent[b][i]) continue;
			a = parent[a][i];
			b = parent[b][i];
		}
		return parent[a][0];
	}
	
	// 나머지 부모 노드를 채워준다
	private static void fillParents() {
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
			parent[nextNode][0] = curNode; // 직접적으로 연결되어 있으므로 curNode는 nextNode의 2^0번째 조상
			dfs(nextNode, curDepth + 1, curNode);
		}
	}
}
