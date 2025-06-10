package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_5052_전화번호_목록 {
    static int t, n;
    static String[] data;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Node {
        HashMap<Character, Node> child;
        boolean isLast;
        public Node() {
            this.child = new HashMap<>();
            this.isLast = false;
        }
    }

    static class Trie {
        Node root;
        public Trie() {
            this.root = new Node();
        }
        public void insert(String str) {
            Node current = this.root;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                current.child.putIfAbsent(c, new Node());
                if (i == str.length() - 1) {
                    current.child.get(c).isLast = true;
                }
                current = current.child.get(c); // update
            }
        }

        public boolean hasPrefix(String str) {
            Node current = this.root;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (current.isLast && !current.child.isEmpty()) {
                    return true;
                }
                current = current.child.get(c);
            }
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            input();
            Trie trie = new Trie();
            for (String str : data) {
                trie.insert(str);
            }
            String answer = "YES";
            for (String str : data) {
                if (trie.hasPrefix(str)) {
                    answer = "NO";
                    break;
                }
            }
            System.out.println(answer);
        }
        br.close();
    }

    private static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        data = new String[n];
        for (int i = 0; i < n; i++) {
            data[i] = br.readLine();
        }
    }
}
