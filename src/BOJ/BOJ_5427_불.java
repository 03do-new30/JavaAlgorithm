import java.util.*;
import java.io.*;

public class BOJ_5427_불 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int h, w;
	static char[][] arr;
	static int[][] fireTime;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			input();
			setFireTime();
			int result = bfs();
			System.out.println(result > -1 ? result : "IMPOSSIBLE");
		}
		br.close();
	}
	
	static int bfs() {
		int[][] time = new int[h][w];
		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (arr[i][j] == '@') {
					time[i][j] = 0;
					q.offer(new int[] {i, j});
				} else {
					time[i][j] = -1;
				}
			}
		}
		
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			int r = tmp[0];
			int c = tmp[1];
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr < 0 || nr >= h || nc < 0 || nc >= w) {
					return time[r][c] + 1;
				}
				if (arr[nr][nc] != '.') { continue; }
				if (time[nr][nc] > -1) { continue; }
				if (fireTime[nr][nc] == -1 || fireTime[nr][nc] > time[r][c] + 1) {
					time[nr][nc] = time[r][c] + 1;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		return -1;
	}
	
	static void setFireTime( ) {
		fireTime = new int[h][w];
		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (arr[i][j] == '*') {
					q.offer(new int[] {i, j});
					fireTime[i][j] = 0;
				} else {
					fireTime[i][j] = -1;
				}
			}
		}
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			int r = tmp[0];
			int c = tmp[1];
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr < 0 || nr >= h || nc < 0 || nc >= w) { continue; }
				if (arr[nr][nc] == '#') { continue; }
				if (fireTime[nr][nc] > -1) { continue; }
				fireTime[nr][nc] = fireTime[r][c] + 1;
				q.offer(new int[] {nr, nc});
			}
		}
	}
	
	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		arr = new char[h][w];
		for (int i = 0; i < h; i++) {
			arr[i] = br.readLine().toCharArray();
		}
	}
}
