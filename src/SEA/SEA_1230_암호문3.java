package SEA;

import java.util.*;
import java.io.*;

public class SEA_1230_암호문3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int test_case = 1; test_case <= 10; test_case++) {
            int N = Integer.parseInt(br.readLine());
            List<char[]> passwordBundle = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                passwordBundle.add(st.nextToken().toCharArray());
            }

            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            for (int cmdCount = 0; cmdCount < M; cmdCount++) {
                String cmd = st.nextToken();

                if (cmd.equals("I")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < y; i++) {
                        passwordBundle.add(x + i, st.nextToken().toCharArray());
                    }
                } else if (cmd.equals("D")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < y; i++) {
                        passwordBundle.remove(x + i);
                    }
                } else if (cmd.equals("A")) {
                    int y = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < y; i++) {
                        passwordBundle.add(st.nextToken().toCharArray());
                    }
                }
            }

            // 공백 문자 후 수정된 암호문 뭉치의 앞 10개 암호문을 출력
            bw.write("#" + test_case + " ");
            for(int i = 0; i < 10; i++) {
                char[] charArray = passwordBundle.get(i);
                String[] strArray = new String[charArray.length];
                for (int j = 0; j < charArray.length; j++) {
                    strArray[j] = String.valueOf(charArray[j]);
                }
                bw.write(String.join("", strArray) + " ");
            }
            bw.write("\n");
        }



        br.close();
        bw.flush();
        bw.close();
    }
}
