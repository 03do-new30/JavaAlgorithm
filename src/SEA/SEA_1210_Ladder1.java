package SEA;

import java.io.*;
import java.util.*;

public class SEA_1210_Ladder1 {
	
	static final int N = 100;
	static int[][] arr;
	static int[] dr = { -1, 0, 0 };
	static int[] dc = { 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int test_case = 0; test_case < 10; test_case++) {

			int tc = Integer.parseInt(br.readLine());

			arr = new int[N][N];

			StringTokenizer st;

			// 도착점
			int r = 0;
			int c = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					// 도착점 찾기
					if (arr[i][j] == 2) {
						r = i;
						c = j;
						break;
					}
				}
			}

			// (r, c)에서 출발해서 도착하는 지점을 구한다.
			// 위, 오른쪽, 왼쪽 방향만 검사해주면 된다.
			int curDir = 0; // 초기 방향은 위로 설정
			boolean[][] visited = new boolean[N][N];
			visited[r][c] = true;
			
			while (r != 0) { // 꼭대기에 도착할 때까지

				// 다른 방향으로 전환할 수 있는지 검사
				for (int i = 0; i < 3; i++) {
					
					if (i == curDir) {
						continue;
					}
					
					int nr = r + dr[i];
					int nc = c + dc[i];
					if (isInRange(nr, nc) && arr[nr][nc] != 0 && !visited[nr][nc]) { // i방향으로 나아갈 수 있음
						// 현재 방향에서 방향 전환이 가능하다면 방향을 바꿔준다.
						curDir = i;
						break;
					}
				}
				
				// update
				r += dr[curDir];
				c += dc[curDir];
				visited[r][c] = true;
			}

			bw.write("#" + tc + " " + c + "\n");

		}

		bw.close();
		br.close();
	}

	private static boolean isInRange(int r, int c) {
		if (0 <= r && r < N && 0 <= c && c < N) {
			return true;
		}
		return false;
	}
}
