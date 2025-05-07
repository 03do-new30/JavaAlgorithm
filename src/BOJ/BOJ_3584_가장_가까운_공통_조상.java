package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_3584_가장_가까운_공통_조상 {
	static int T, N;
	static List<Integer>[] graph;
	static int[][] parent;
	static int[] depth, indegree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			graph = new List[N+1];
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			indegree = new int[N+1];
			for (int i = 0; i < N-1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph[a].add(b); // a가 b의 부모임
				indegree[b]++;
			}
			// root 탐색
			int root = -1;
			for (int i = 1; i <= N; i++) {
				if (indegree[i] == 0) {
					root = i;
					break;
				}
			}
			
			// init parent & depth
			depth = new int[N+1];
			parent = new int[N+1][21];
			init(root, 1);
			fillParents();
			// query
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
		if (depthA < depthB) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		// a를 끌어 올려서 depth 맞춰주기
		for (int i = 20; i >= 0; i--) {
			if (depth[a] - depth[b] >= (1<<i)) {
				a = parent[a][i];
			}
		}
		if (a == b) return a;
		
		// depth를 줄여가며 가장 가까운 공통 조상 찾기
		for (int i = 20; i >= 0; i--) {
			if (parent[a][i] == parent[b][i]) continue;
			a = parent[a][i];
			b = parent[b][i];
		}
		return parent[a][0];
	}
	
	private static void fillParents() {
		for (int step = 1; step <= 20; step++) {
			for (int node = 1; node <= N; node++) {
				parent[node][step] = parent[parent[node][step-1]][step-1];
			}
		}
	}
	
	private static void init(int curNode, int curDepth) {
		depth[curNode] = curDepth;
		for (int nextNode : graph[curNode]) {
			parent[nextNode][0] = curNode;
			init(nextNode, curDepth + 1);
		}
	}
}
