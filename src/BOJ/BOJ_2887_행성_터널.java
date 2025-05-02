import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static Planet[] planets;
	static PriorityQueue<Edge> edges;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		input();
		makeEdges();
		
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		int totalCost = 0;
		int edgeCount = 0;
		while (!edges.isEmpty() && edgeCount < N-1) {
			Edge e = edges.poll();
			if (union(e.from, e.to)) {
				totalCost += e.cost;
				edgeCount++;
			}
		}
		System.out.println(totalCost);
	}
	
	static int find(int a) {
		if (parents[a] == a) return a;
		return find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB) return false;
		if (rootA < rootB) {
			parents[rootB] = rootA;
		} else {
			parents[rootA] = rootB;
		}
		return true;
	}
	
	private static void makeEdges() {
		edges = new PriorityQueue<>();
		for (int dim = 0; dim < 3; dim++) {
			final int d = dim;
			Arrays.sort(planets, (a, b) -> {
				if (d == 0) return Integer.compare(a.x, b.x);
				if (d == 1) return Integer.compare(a.y, b.y);
				return Integer.compare(a.z, b.z);
			});
			
			// 차원 별 정렬했을 때, 인접한 행성끼리의 Edge를 저장한다.
			for (int i = 0; i < N-1; i++) {
				Planet a = planets[i];
				Planet b = planets[i+1];
				int cost = d == 0? Math.abs(a.x - b.x): 
							d == 1? Math.abs(a.y - b.y) :
								Math.abs(a.z - b.z);
				edges.add(new Edge(a.id, b.id, cost));
			}
		}
	}
	
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		planets = new Planet[N];
		for (int i = 0; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			planets[i] = new Planet(i, x, y, z);
		}
		br.close();
	}
	
	static class Edge implements Comparable<Edge> {
		int from, to, cost;
		
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	
	static class Planet {
		int id, x, y, z;
		public Planet(int id, int x, int y, int z) {
			this.id = id;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}
