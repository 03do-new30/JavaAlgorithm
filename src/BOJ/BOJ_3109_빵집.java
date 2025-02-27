package BOJ;

import java.util.*;

public class BOJ_3109_빵집 {

    static int R;
    static int C;
    static char[][] arr;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        arr = new char[R][C];
        for (int i = 0; i < R; i++) {
            String tmp = sc.next();
            arr[i] = tmp.toCharArray();
        }
        visited = new boolean[R][C];
        int answer = 0;
        for (int r = 0; r < R; r++) {
            visited[r][0] = true;
            if (pipe(r, 0)) {
                answer++;
            }
        }
        System.out.println(answer);
        sc.close();
    }

    private static boolean pipe(int r, int c) {
        if (c == C-1) {
            return true;
        }

        for (int i = 0; i < 3; i++) {
            int nr = r + dr[i];
            int nc = c + 1;
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                continue;
            }
            if (arr[nr][nc] == 'x') { continue; }
            if (visited[nr][nc]) { continue; }
            visited[nr][nc] = true;
            boolean success = pipe(nr, nc);
            if (success) {
                return true;
            }
        }
        return false;
    }
}
