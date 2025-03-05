package BOJ;

import java.util.*;

public class BOJ_1197_최소_스패닝_트리 {

    static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < E; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = sc.nextInt();
            pq.add(new Edge(A, B, C));
        }

        parent = new int[V+1];
        for (int i = 1; i < V+1; i++) {
            parent[i] = i;
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int u = edge.u;
            int v = edge.v;
            int wt = edge.wt;

            if (find(u) == find(v)) { continue; } // cycle
            answer += wt;
            union(u, v);
        }
        System.out.println(answer);
        sc.close();
    }

    static int find(int node) {
        if (parent[node] == node) { return node; }
        parent[node] = find(parent[node]);
        return parent[node];
    }

    static void union(int u, int v) {
        int parentU = find(u);
        int parentV = find(v);
        if (parentU <= parentV) {
            parent[parentV] = parentU;
        } else {
            parent[parentU] = parentV;
        }
    }

    static class Edge implements Comparable<Edge>{
        int u;
        int v;
        int wt;
        public Edge(int u, int v, int wt) {
            this.u = u;
            this.v = v;
            this.wt = wt;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.wt, o.wt);
        }
    }
}