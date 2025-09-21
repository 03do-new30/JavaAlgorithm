package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_4948_베르트랑_공준 {

    final static int MAX = 123456*2;

    static boolean[] isPrime = new boolean[MAX+1];
    static int[] prefixSum = new int[MAX+1];

    public static void main(String[] args) throws IOException {
        init();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            System.out.println(prefixSum[n*2]-prefixSum[n]);
        }
        br.close();
    }

    private static void init() {

        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i <= MAX; i++) {
            if (!isPrime[i]) continue;
            isPrime[i] = true;
            for (int j = 2; i * j <= MAX; j++) {
                isPrime[i*j] = false;
            }
        }

        for (int i = 1; i <= MAX; i++) {
            prefixSum[i] = prefixSum[i-1] + (isPrime[i]? 1 : 0);
        }
    }
}
