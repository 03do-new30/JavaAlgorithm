package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_24391_귀찮은_해강이 {
    static int N, M;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u, v);
        }


        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        for (int i = 0; i < N-1; i++) {
            int u = arr[i];
            int v = arr[i+1];
            if (find(u) != find(v)) {++cnt;}
        }
        System.out.println(cnt);

        br.close();
    }

    private static void init() {
        parents = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    private static boolean union(int u, int v) {
        int parentU = find(u);
        int parentV = find(v);
        if (parentU == parentV) return false;
        if (parentU < parentV) {
            parents[parentV] = parentU;
        } else {
            parents[parentU] = parentV;
        }
        return true;
    }
}
