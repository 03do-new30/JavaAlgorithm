import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] indegree;
	static List<Integer> result;
	static List<Integer>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		indegree = new int[N+1];
		graph = new List[N+1];
		for (int i = 1; i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			indegree[to]++;
			graph[from].add(to);
		}
		
		topologySort();
		for (int node : result) {
			System.out.print(node + " ");
		}
		System.out.println();
		br.close();
	}
	
	private static void topologySort() {
		result = new ArrayList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 1; i < N+1; i++) {
			if (indegree[i] == 0) {
				pq.add(i);
			}
		}
		
		while (!pq.isEmpty()) {
			int node = pq.poll();
			result.add(node);
			for (int nextNode : graph[node]) {
				if (--indegree[nextNode] == 0)
					pq.add(nextNode);
			}
		}
	}
}
