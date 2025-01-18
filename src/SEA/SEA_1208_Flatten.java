package SEA;

import java.util.*;
import java.io.*;

public class SEA_1208_Flatten {

    private static final int T = 10;
    private static final int N = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        for (int test_case = 1; test_case <= T; test_case++) {

            int cnt = Integer.parseInt(br.readLine());
            int[] boxes = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                boxes[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < cnt; i++) {
                Arrays.sort(boxes);
                int maxBox = boxes[N-1];
                int minBox = boxes[0];

                if (maxBox - minBox <= 1) {
                    break;
                }

                boxes[N-1]--;
                boxes[0]++;
            }

            Arrays.sort(boxes);
            int answer = boxes[N-1] - boxes[0];
            bw.write("#" + test_case + " " + answer + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}