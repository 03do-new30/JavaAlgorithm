package BOJ;
import java.util.*;
import java.io.*;

public class BOJ_14725_개미굴 {
    static class Node {
        SortedMap<String, Node> child;

        public Node() {
            this.child = new TreeMap<>();
        }
    }

    static class Trie {
        Node root;
        public Trie() {
            root = new Node();
        }
        public void insert(String[] strings) {
            Node node = this.root;
            for (int i = 0; i < strings.length; i++) {
                node.child.putIfAbsent(strings[i], new Node());
                node = node.child.get(strings[i]);
            }
        }

        public void print(Node node, int depth) {
           for (String key : node.child.keySet()) {
               System.out.print("--".repeat(depth));
               System.out.println(key);
               print(node.child.get(key), depth+1);
           }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            String[] strings = new String[K];
            for (int j = 0; j < K; j++) {
                strings[j] = st.nextToken();
            }
            trie.insert(strings);
        }
        trie.print(trie.root, 0);
        br.close();
    }
}
