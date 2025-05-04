import java.util.*;
import java.io.*;

public class BOJ_2533_사회망_서비스 {
	static int N;
	static List<Integer>[] graph;
	static boolean[] visited;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new List[N+1];
		for (int i = 1; i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
		br.close();
		
		visited = new boolean[N+1];
		// dp[i][1] = i가 얼리어답터이기 위해 필요한 최소 얼리어답터의 수
		dp = new int[N+1][2]; // dp[i][0] = not early, dp[i][1] = early
		
		dfs(1);
		int answer = Math.min(dp[1][0], dp[1][1]);
		System.out.println(answer);
	}
	
	private static void dfs(int node) {
		visited[node] = true;
		dp[node][0] = 0;
		dp[node][1] = 1;
		for (int next : graph[node]) {
			if (visited[next]) continue;
			dfs(next);
			dp[node][0] += dp[next][1];
			dp[node][1] += Math.min(dp[next][1], dp[next][0]);
		}
	}
}
