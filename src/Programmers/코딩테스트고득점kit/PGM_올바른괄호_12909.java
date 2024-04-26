package Programmers.코딩테스트고득점kit;

import java.util.*;

public class PGM_올바른괄호_12909 {
    public static void main(String[] args) {
        String[] s = {"()()", "(())()", ")()(", "(()("};
        boolean[] answer = {true, true, false, false};
        Solution sol = new Solution();
        for (int i =0 ; i< answer.length; i++) {
            System.out.println("# " + i);
            System.out.println(
                    answer[i] == sol.solution(s[i])
            );
        }
    }
    static class Solution {
        boolean solution(String s) {

            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < s.length(); i++) {
                char tmp = s.charAt(i);
                if (tmp == '(') {
                    stack.push(tmp);
                } else {
                    if (stack.isEmpty())
                        return false;
                    stack.pop();
                }
            }

            return stack.isEmpty();
        }
    }
}
