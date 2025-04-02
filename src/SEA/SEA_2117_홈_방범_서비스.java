package SEA;

import java.io.*;
import java.util.*;
import java.awt.Point;

public class SEA_2117_홈_방범_서비스 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static List<Point> houses;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            input();
            int answer = 0;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    Point current = new Point(r, c);
                    int maxCnt = 0;
                    for (int k = 1; k <= 2*N; k++) {
                        int operation = k*k + (k-1)*(k-1);
                        int houseCnt = 0;
                        for (Point house : houses) {
                            if (getDist(current, house) < k){
                                houseCnt++;
                            }
                        }
                        if (operation <= houseCnt * M) {
                            maxCnt = Integer.max(maxCnt, houseCnt);
                        }
                    }
                    answer = Integer.max(answer, maxCnt);
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
        br.close();
    }

    static int getDist(Point a, Point b){
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        houses = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
               if (Integer.parseInt(st.nextToken()) == 1) {
                    houses.add(new Point(i, j));
               }
            }
        }
    }
}
