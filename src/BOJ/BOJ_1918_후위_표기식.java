package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_1918_후위_표기식 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] infix = br.readLine().toCharArray();
        int n = infix.length;

        // infix -> postfix
        // stack: 연산자를 저장
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            switch(infix[i]) {
                case '+':
                case '-':
                case '*':
                case '/':
                    // stack top의 우선순위가 현재 연산자보다 낮을 때까지 pop
                    while (!stack.isEmpty() && power(stack.peekFirst()) >= power(infix[i])) {
                        char x = stack.pollFirst();
                        result.append(x);
                    }
                    stack.offerFirst(infix[i]);
                    break;

                case '(':
                    stack.offerFirst(infix[i]);
                    break;
                case ')':
                    // 여는 괄호를 만날 때까지 pop
                    while (!stack.isEmpty()) {
                        char x = stack.pollFirst();
                        if (x == '(') { break; }
                        result.append(x);
                    }
                    break;
                default:
                    result.append(infix[i]);
            }
        }

        // stack에 남아있는 연산자 pop
        while (!stack.isEmpty()) {
            result.append(stack.pollFirst());
        }
        System.out.println(result);


        br.close();
    }

    private static int power(char operator) {
        if (operator == '(' || operator == ')' ){ return 0; }
        if (operator == '+' || operator == '-') { return 1; }
        if (operator == '*' || operator == '/') { return 2; }
        return -1;
    }

}
