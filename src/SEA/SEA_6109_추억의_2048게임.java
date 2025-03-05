package SEA;

import java.io.*;
import java.util.*;

public class SEA_6109_추억의_2048게임 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static String S;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            input();
            if (S.equals("left")) {

                playLeft(arr);


            } else if (S.equals("right")) {
                int[][] newArr = new int[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        newArr[i][j] = arr[i][N-1-j]; // 열 역순
                    }
                }

                playLeft(newArr);

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        arr[i][j] = newArr[i][N-1-j]; // 열 원상복구
                    }
                }
            } else if (S.equals("up")) {
                int[][] newArr = new int[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        newArr[i][j] = arr[j][N-1-i]; // -90도 회전
                    }
                }

                playLeft(newArr);


                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        arr[i][j] = newArr[N-1-j][i]; // 90도 회전
                    }
                }
            } else { // down
                int[][] newArr = new int[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        newArr[i][j] = arr[N-1-j][i]; // 90도 회전
                    }
                }

                playLeft(newArr);

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        arr[i][j] = newArr[j][N-1-i]; // -90도 회전
                    }
                }
            }
            System.out.println("#" + tc);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    static void playLeft(int[][] arr) {
        pushLeft(arr);
        moveLeft(arr);
        pushLeft(arr);
    }
    static void moveLeft(int[][] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (arr[i][j] == 0) { continue; } //empty

                if (arr[i][j-1] == arr[i][j]) { // merge
                    arr[i][j-1] *= 2;
                    arr[i][j] = 0;
                }
            }
        }
    }

    static void pushLeft(int[][] arr) {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 0) { continue; } // empty
                q.add(arr[i][j]);
            }
            for (int j = 0; j < N; j++) {
                if (!q.isEmpty()) {
                    arr[i][j] = q.poll();
                    continue;
                }
                arr[i][j] = 0;
            }
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = st.nextToken();
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
