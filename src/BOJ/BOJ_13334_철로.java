import java.util.*;
import java.io.*;

public class BOJ_13334_철로 {
	static class Pair implements Comparable<Pair>{
		int from, to;
		public Pair(int from, int to) {
			this.from = from;
			this.to = to;
		}
		@Override
		public int compareTo(Pair o) {
			if (this.to == o.to) {
				return Integer.compare(this.from, o.from);
			}
			return Integer.compare(this.to, o.to);
		}
		@Override
		public String toString() {
			return from + "->" + to;
		}
		
	}
	static int N, D;
	static List<Pair> pairs;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pairs = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a > b) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			pairs.add(new Pair(a, b));
		}
		D = Integer.parseInt(br.readLine());
		Collections.sort(pairs);
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // from 기준
		int maximum = 0;
		for (Pair pair : pairs) {
			int start = pair.to - D;
			pq.offer(pair.from);
			while(!pq.isEmpty() && pq.peek() < start) {
				pq.poll();
			}
			maximum = Math.max(maximum, pq.size());
		}
		System.out.println(maximum);
		br.close();
	}
}
