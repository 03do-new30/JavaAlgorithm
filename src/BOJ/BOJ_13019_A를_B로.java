package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_13019_A를_B로 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();
        N = A.length;

        if (isValidOperation(A, B)) {
            int cnt = 0;
            int aPtr = N-1;
            int bPtr = N-1;

            // 뒤에서부터 확인
            while (0 <= aPtr && 0 <= bPtr) {
                if (A[aPtr] == B[bPtr]) {
                    bPtr--;
                } else {
                    cnt++;
                }
                aPtr--;
            }
            bw.write(cnt + "\n");
        } else {
            bw.write(-1 + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static boolean isValidOperation(char[] current, char[] target) {
        // current, target의 글자 수가 같지 않으면 불가능
        int[] cnt = new int['Z' - 'A' + 1];
        for (int i = 0; i < N; i++) {
            cnt[current[i] - 'A'] += 1;
            cnt[target[i] - 'A'] -= 1;
        }
        // cnt[i]가 0이 아닌 것이 하나라도 있다면 불가능
        for (int i = 0; i < 'Z' - 'A' + 1; i++) {
            if (cnt[i] != 0) return false;
        }
        return true;
    }
}
