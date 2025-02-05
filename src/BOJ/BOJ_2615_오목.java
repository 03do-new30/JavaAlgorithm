package BOJ;

import java.util.*;
import java.io.*;
public class BOJ_2615_오목 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = 20;
        int[][] arr = new int[N][N];

        // 검사 방향: 아래, 우상향, 좌하향, 우측
        int[] dr = {1, -1, 1, 0};
        int[] dc = {0, 1, 1, 1};

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int winner = 0;
        int winnerR = 0;
        int winnerC = 0;

        for (int r = 1; r < N; r++) {
            for (int c = 1; c < N; c++) {
                
                if (winner > 0) break; // 승부 결정 완료
                if (arr[r][c] == 0) continue;

                // 방향 체크
                for (int i = 0; i < 4; i++) {

                    int cnt = 1; // (r, c)에서 시작, 연속된 바둑돌 개수 카운트
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    while (1 <= nr && nr < N && 1 <= nc && nc < N) {
                        if (arr[nr][nc] != arr[r][c]) {
                            break;
                        }
                        nr += dr[i];
                        nc += dc[i];
                        cnt++;
                    }

                    // i방향으로 연속된 바둑돌이 5개인 경우
                    if (cnt == 5) {
                        // (r, c) 이전의 바둑돌 색상을 확인하여 6개 이상인지 체크
                        int prevR = r - dr[i];
                        int prevC = c - dc[i];

                        boolean isValid = false;
                        if (1 <= prevR && prevR < N && 1 <= prevC && prevC < N) {
                            if (arr[r][c] != arr[prevR][prevC]) {
                                isValid = true;
                            }
                        }
                        else { // 이전의 바둑돌이 없다면 5개임이 보장
                            isValid = true;
                        }

                        if (isValid) {
                            winner = arr[r][c];
                            winnerR = r;
                            winnerC = c;
                            break; // 승부 결정 완료
                        }
                    }
                }
            }
        }

        bw.write(winner + "\n");
        if (winner > 0) {
            bw.write(winnerR + " " + winnerC);
        }


        br.close();
        bw.flush();
        bw.close();
    }
}
