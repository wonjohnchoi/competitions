import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    static class Node {
        HashSet<Node> dependsOn = new HashSet<Node>();
        char c;
        Node(char cc) {
            c = cc;
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            String S = sc.next();
            words[i] = S;
        }
        Node[] nodes = new Node[26];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node((char) (i + 'a'));
        }
        boolean possible = true;
        loop : for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                String w1 = words[i];
                String w2 = words[j];
                if (w1.startsWith(w2)) {
                    possible = false;
                    break loop;
                } else if (w2.startsWith(w1))
                    continue;
                int minChar = (int) Math.min(w1.length(), w2.length());
                for (int k = 0; k < minChar; k++) {
                    if (w1.charAt(k) != w2.charAt(k)) {
                        nodes[(int) (w2.charAt(k) - 'a')].dependsOn.add(nodes[(int) (w1.charAt(k) - 'a')]);
                        // out.println("from " + w2 + " " + w1);
                        // out.println(w2.charAt(k) + " depends on " + w1.charAt(k));
                        break;
                    }
                }
            }
        }
        String ans = "";
        for (int i = 0; i < 26; i++) {
            Node node = null;
            for (int j = 0; j < 26; j++) {
                if (nodes[j] != null && nodes[j].dependsOn.size() == 0) {
                    node = nodes[j];
                    nodes[j] = null;
                    break;
                }
            }
            if (node == null) {
                // out.println(" became impossible: " + ans);
                possible = false;
                break;
            }
            ans += node.c;
            for (int j = 0; j < 26; j++) {
                if (nodes[j] != null) {
                    nodes[j].dependsOn.remove(node);
                }
            }
        }
        if (!possible) ans = "Impossible";
        out.println(ans);
    }
}
