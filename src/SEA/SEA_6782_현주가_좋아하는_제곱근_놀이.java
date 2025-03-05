package SEA;

import java.io.*;

public class SEA_6782_현주가_좋아하는_제곱근_놀이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            long N = Long.parseLong(br.readLine());
            int cnt = solve(N, 0);
            System.out.println("#" + tc + " " + cnt);
        }
        br.close();
    }

    static int solve(long n, int cnt) {
        if (n == 2) { return cnt; }
        long root = (long) Math.sqrt(n);
        if (Math.sqrt(n) == root) {
            return solve(root, cnt + 1);
        } else {
            long nextRoot = root + 1;
            long nextN = nextRoot * nextRoot;
            return solve(nextN, cnt + (int) (nextN - n));
        }
    }
}
