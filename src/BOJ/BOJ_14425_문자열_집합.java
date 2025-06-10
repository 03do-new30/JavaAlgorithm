package BOJ;
import java.util.*;
import java.io. *;

public class BOJ_14425_문자열_집합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            set.add(str);
        }
        int answer = 0;
        for (int i = 0; i < M; i++) {
            if (set.contains(br.readLine())) ++answer;
        }
        System.out.println(answer);
        br.close();
    }
}
