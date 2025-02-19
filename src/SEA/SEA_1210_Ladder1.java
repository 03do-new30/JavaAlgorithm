package SEA;

import java.io.*;
import java.util.*;

public class SEA_1210_Ladder1 {
	
	static final int N = 100;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int test_case = 0; test_case < 10; test_case++) {

			int tc = Integer.parseInt(br.readLine());

			arr = new int[N][N];

			StringTokenizer st;

			// 도착점
			int r = N-1;
			int c = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					// 도착점 찾기
					if (arr[i][j] == 2) {
						c = j;
						break;
					}
				}
			}

			// (r, c)에서 출발해서 도착하는 지점을 구한다.
			while (r > 0) {
				r--; // 행은 기본적으로 항상 줄어든다.
				if (c + 1 < N && arr[r][c+1] == 1) { // 오른쪽으로 가는 길이 있는가?
					// 더이상 갈 수 없을 때까지 오른쪽으로 진행한다.
					while (c + 1 < N && arr[r][c + 1] == 1) {
						c++;
					}
				} else if (0 <= c - 1 && arr[r][c-1] == 1) { // 왼쪽으로 가는 길이 있는가?
					// 더이상 갈 수 없을 때까지 왼쪽으로 진행한다.
					while (0 <= c - 1 && arr[r][c - 1] == 1) {
						c--;
					}
				}
			}
			bw.write("#" + tc + " " + c + "\n");

		}

		bw.close();
		br.close();
	}
}
