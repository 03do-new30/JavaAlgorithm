package BOJ;

import java.util.*;
public class BOJ_12891_DNA_비밀번호 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int S = sc.nextInt();
        int P = sc.nextInt();
        String dna = sc.next();
        int A = sc.nextInt();
        int C = sc.nextInt();
        int G = sc.nextInt();
        int T = sc.nextInt();

        // 민호가 만들 수 있는 비밀번호 종류의 수
        int answer = 0;

        int left = 0;
        int right = P;
        int a, c, g, t;
        a = c = g = t = 0;
        // 처음으로 만들 수 있는 부분문자열에 대해 검사
        for (int i = left ; i < right; i++) {
            if (dna.charAt(i) == 'A') {
                a++;
            } else if (dna.charAt(i) == 'C') {
                c++;
            } else if (dna.charAt(i) == 'G') {
                g++;
            } else if (dna.charAt(i) == 'T') {
                t++;
            }
        }
        if (A <= a && C <= c && G <= g && T <= t) {
            answer++;
        }
        
        // 슬라이딩 윈도우
        while (right < S) {

            switch (dna.charAt(left)) {
                case 'A':
                    a--;
                    break;
                case 'C':
                    c--;
                    break;
                case 'G':
                    g--;
                    break;
                case 'T':
                    t--;
                    break;
            }
            left++;

            switch (dna.charAt(right)) {
                case 'A':
                    a++;
                    break;
                case 'C':
                    c++;
                    break;
                case 'G':
                    g++;
                    break;
                case 'T':
                    t++;
                    break;
            }
            right++;


            if (A <= a && C <= c && G <= g && T <= t) {
                answer++;
            }
        }

        System.out.println(answer);
        sc.close();
    }
}
