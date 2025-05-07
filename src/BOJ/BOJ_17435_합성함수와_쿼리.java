package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_17435_합성함수와_쿼리 {
	static int m, q;
	static int[] f;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		m = Integer.parseInt(br.readLine());
		f = new int[m+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= m; i++) {
			f[i] = Integer.parseInt(st.nextToken());
		}
		// result init
		// dp[i][j] = f^{2^j}(i)
		dp = new int[m+1][20]; // 2^19 = 524,288 (1 <= n <= 500,000)
		init();
		
		q = Integer.parseInt(br.readLine());
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			System.out.println(getResult(n, x));
		}
		br.close();
	}
	
	private static int getResult(int n, int x) {
		for (int i = 0; i < 20; i++) {
			if ((n & (1 << i)) != 0) {
				x = dp[x][i];
			}
		}
		return x;
	}
	
	private static void init() {
		for (int i = 1; i <= m; i++) {
			dp[i][0] = f[i]; // 1번 f(x)를 수행했을 때 결과값
		}
		for (int step = 1; step < 20; step++) {
			for (int param = 1; param <= m; param++) {
				dp[param][step] = dp[dp[param][step-1]][step-1];
			}
		}
	}
}
