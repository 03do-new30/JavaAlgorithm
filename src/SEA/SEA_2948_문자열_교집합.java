package SEA;


import java.util.*;
import java.io.*;

public class SEA_2948_문자열_교집합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            Set<String> set = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                set.add(st.nextToken());
            }
            int answer = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                if (set.contains(st.nextToken())) {
                    answer++;
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
        br.close();
    }
}
