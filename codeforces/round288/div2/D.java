import java.util.*;
import java.io.*;
public class D {
    public static PrintStream out = System.out;
    static class Node {
        String s;
        LinkedList<Node> out = new LinkedList<>();
        int start = 0;
        int inDegree = 0;
        Node(String s) {
            this.s = s;
        }
    }
    static int ord(char c) {
        // 0 ~ 61
        if ('0' <= c && c <= '9') return (int) (c - '0');
        if ('a' <= c && c <= 'z') return (int) (c - 'a') + 10;
        return (int) (c - 'A') + 10 + 26;
    }
    static int ord(String s) {
        return ord(s.charAt(0)) * 62 + ord(s.charAt(1));
    }
    static char chr1(int a) {
        if (0 <= a && a <= 9) return (char) (a + '0');
        if (10 <= a && a <= 35) return (char) (a - 10 + 'a');
        return (char) (a - 10 - 26 + 'A');
    }
    static String chr(int a) {
        int b = a / 62;
        int c = a % 62;
        return "" + chr1(b) + chr1(c);
    }
    public static void main(String args[]) {
        InputReader sc = new InputReader(System.in);
        int N = sc.nextInt();
        Node[] nodes = new Node[62 * 62];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(chr(i));
        }
        for (int i = 0; i < N; i++) {
            String S = sc.next();
            Node n1 = nodes[ord(S.substring(0, 2))];
            Node n2 = nodes[ord(S.substring(1, 3))];
            n1.out.add(n2);
            n2.inDegree++;
        }
        int start = -1;
        int end = -1;
        for (int i = 0; i < nodes.length; i++) {
            boolean s = nodes[i].inDegree + 1 == nodes[i].out.size();
            boolean e = nodes[i].inDegree == nodes[i].out.size() + 1;
            if (s) {
                if (start != -1) {
                    NO();
                }
                start = i;
            } else if (e) {
                if (end != -1) {
                    NO();
                }
                end = i;
            } else {
                if (nodes[i].inDegree != nodes[i].out.size()) {
                    NO();
                }
            }
        }
        if ((start == -1 && end != -1) || (start != -1 && end == -1)) NO();
        if (start == -1) { // no odd edge
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i].out.size() > 0) {
                    start = i;
                    break;
                }
            }
        }
        List<Node> stack = new LinkedList<>();
        dfs(nodes[start], stack);
        if (stack.size() - 1 != N) NO();
        out.println("YES");
        for (int i = stack.size() - 1; i >= 0; i--) {
            if (i == stack.size() - 1) out.print(stack.get(i).s);
            else out.print(stack.get(i).s.charAt(1));
        }
        out.println();
    }
    static void dfs(Node cur, List<Node> stack) {
        while (cur.out.size() > cur.start) {
            dfs(cur.out.get(cur.start++), stack);
        }
        stack.add(cur);
    }
    static void NO() {
        out.println("NO");
        System.exit(0);
    }
}
class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;
    public InputReader(InputStream stream) {
	reader = new BufferedReader(new InputStreamReader(stream), 32768);
	tokenizer = null;
    }
    public String next() {
	while (tokenizer == null || !tokenizer.hasMoreTokens()) {
	    try {
		tokenizer = new StringTokenizer(reader.readLine());
	    } catch (IOException e) {
		throw new RuntimeException(e);
	    }
	}
	return tokenizer.nextToken();
    }
    public double nextDouble() {
	return Double.parseDouble(next());
    }
    public long nextLong() {
	return Long.parseLong(next());
    }
    public int nextInt() {
	return Integer.parseInt(next());
    }
}
