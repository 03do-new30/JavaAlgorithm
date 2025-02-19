package SEA;
import java.util.*;
import java.io.*;

public class SEA_3304_최장_공통_부분_수열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			// 최장 공통부분 수열 = 주어진 여러 개의 수열 모두의 부분 수열이 되는 수열들 중, 가장 긴 것을 찾는 문제
			// 부분수열이기 때문에, 문자 사이를 건너뛰어 공통되면서 가장 긴 부분 문자열을 찾으면 된다.
			int N = str1.length();
			int M = str2.length();
			int[][] dp = new int[N+1][M+1];
			
			// dp[i][j] = str1의 i번째 문자와 str2의 j번째 문자를 비교했을 때, 지금까지의 최장 공통 부분 수열 길이
			// str1의 i번째 문자와 str2의 j번째 문자가 같다면
			// 	str[i-1][j-1] + 1
			// str1의 i번째 문자와 str2의 j번째 문자가 다르다면
			//	str1의 i-1번째 문자와 str2의 j번째 문자를 비교한 값과, str1의 i번째 문자와 str2의 j-1번째 문자를 비교한 값을 비교
			// 	max(str[i-1][j], str[i][j-1])
			
			
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < M + 1; j++) {
					int idx1 = i -1;
					int idx2 = j - 1;
					if (str1.charAt(idx1) == str2.charAt(idx2)) {
						dp[i][j] = dp[i-1][j-1] + 1;
					} else {
						dp[i][j] = Integer.max(dp[i-1][j], dp[i][j-1]);
					}
				}
			}
			
			bw.write("#" + tc + " " + dp[N][M] + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
