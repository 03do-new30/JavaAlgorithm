package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_1935_후위_표기식2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[] postfix = br.readLine().toCharArray();
        double[] value = new double[26];
        for (int i = 0; i < N; i++) {
            value[i] = Double.parseDouble(br.readLine());
        }

        // 후위 표기식 계산
        Deque<Double> stack = new ArrayDeque<>();
        for (int i = 0; i < postfix.length; i++) {
            if (Character.isAlphabetic(postfix[i])) {
                stack.offerFirst(value[postfix[i] - 'A']);
            } else {
                double num2 = stack.pollFirst(); // 위에 나온 피연산자가 우측 피연산자
                double num1 = stack.pollFirst();
                switch (postfix[i]) {
                    case '+':
                        stack.offerFirst(num1 + num2);
                        break;
                    case '-':
                        stack.offerFirst(num1 - num2);
                        break;
                    case '*':
                        stack.offerFirst(num1 * num2);
                        break;
                    case '/':
                        stack.offerFirst(num1 / num2);
                        break;
                }
            }
        }
        double result = stack.poll();
        System.out.printf("%.2f%n", result);
        br.close();
    }
}
