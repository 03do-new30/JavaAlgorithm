package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_3176_도로_네트워크 {
	static int N, K;
	static class Edge {
		int to;
		int weight;
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	static List<Edge>[] graph;
	static int[] depth;
	static int[][] parent, minEdge, maxEdge;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new List[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Edge>();
		}
		for (int i = 1; i <= N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new Edge(b, c));
			graph[b].add(new Edge(a, c));
		}
		// init
		depth = new int[N+1];
		parent = new int[N+1][21];
		minEdge = new int[N+1][21];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= 20; j++) {
				minEdge[i][j] = 1_000_000;
			}
		}
		maxEdge = new int[N+1][21];
		init(1, 1, 0);
		fill();
		// query
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int[] result = getResult(d, e);
			System.out.println(result[0] + " " + result[1]);
		}
		br.close();
	}
	
	private static int[] getResult(int a, int b) {
		int minVal = 1_000_000;
		int maxVal = 0;
		
		int depthA = depth[a];
		int depthB = depth[b];
		if (depthA < depthB) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		// a와 b의 뎁스가 같아질수 있도록 a를 끌어올린다.
		for (int i = 20; i >= 0; i--) {
			if (depth[a] - depth[b] >= (1 << i)) {
				minVal = Math.min(minVal, minEdge[a][i]);
				maxVal = Math.max(maxVal, maxEdge[a][i]);
				a = parent[a][i];
			}
		}
		if (a == b) return new int[] {minVal, maxVal};
		// a와 b의 공통 조상을 찾을 때까지 두 노드를 끌어올린다.
		for (int i = 20; i >= 0; i--) {
			if (parent[a][i] == parent[b][i]) continue;
			minVal = Math.min(minVal, Math.min(minEdge[a][i], minEdge[b][i]));
			maxVal = Math.max(maxVal, Math.max(maxEdge[a][i], maxEdge[b][i]));
			a = parent[a][i];
			b = parent[b][i];
		}
		// 마지막으로 공통 조상의 바로 아래 간선까지 포함해서 minVal, maxVal을 구한다.
		minVal = Math.min(minVal, Math.min(minEdge[a][0], minEdge[b][0]));
		maxVal = Math.max(maxVal, Math.max(maxEdge[a][0], maxEdge[b][0]));
		return new int[] {minVal, maxVal}; 
	}
	
	private static void fill() {
		for (int step = 1; step <= 20; step++) {
			for (int node = 1; node <= N; node++) {
				parent[node][step] = parent[parent[node][step-1]][step-1];
				minEdge[node][step] = Math.min(minEdge[node][step-1], minEdge[parent[node][step-1]][step-1]);
				maxEdge[node][step] = Math.max(maxEdge[node][step-1], maxEdge[parent[node][step-1]][step-1]);
			}
		}
	}
	
	private static void init(int curNode, int curDepth, int parentNode) {
		depth[curNode] = curDepth;
		for (Edge edge : graph[curNode]) {
			if (edge.to == parentNode) continue;
			parent[edge.to][0] = curNode;
			minEdge[edge.to][0] = edge.weight;
			maxEdge[edge.to][0] = edge.weight;
			init(edge.to, curDepth + 1, curNode);
		}
	}
}
