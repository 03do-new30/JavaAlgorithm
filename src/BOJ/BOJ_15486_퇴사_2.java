package BOJ;
import java.util.*;
import java.io.*;

public class BOJ_15486_퇴사_2 {
	static int N;
	static int[] T, P;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		T = new int[N+1];
		P = new int[N+1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			T[i] = t;
			P[i] = p;
		}
		// dp[i] = i일차에 얻을 수 있는 최대 이익. 퇴사날인 N+1일까지의 이익을 구한다.
		long[] dp = new long[N+2];
		for (int i = 1; i <= N+1; i++) {
			dp[i] = Math.max(dp[i], dp[i-1]);
			if (i == N+1) break;
			int t = T[i]; // 상담에 걸리는 기간
			int p = P[i]; // 상담을 마치고 얻을 수 있는 이익
			int payday = i + t;
			if (payday > N+1) continue;
			dp[payday] = Math.max(dp[payday], dp[i] + p);
		}
		System.out.println(dp[N+1]);
		br.close();
	}
}
