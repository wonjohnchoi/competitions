import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static class Node {
        int i;
        Node (int ii) {
            i = ii;
        }
    }
    public static void main(String args[]) {
        int N = in.nextInt();
        String S = in.next();
        String T = in.next();
        Node[][] nodes = new Node[26][26];
        Node[][] nodes2 = new Node[2][26];
        Node[] ans = new Node[2];
        int diff = 0;
        for (int i = 0; i < N; i++) {
            int a = (int) S.charAt(i) - 'a';
            int b = (int) T.charAt(i) - 'a';
            if (a == b) continue;
            diff++;
            nodes[a][b] = new Node(i + 1);
            nodes2[0][a] = nodes[a][b];
            nodes2[1][b] = nodes[a][b];
            if (nodes[b][a] != null) {
                ans[0] = nodes[a][b];
                ans[1] = nodes[b][a];
            }
        }
        if (ans[0] != null) {
            out.println(diff - 2);
            out.println(ans[0].i + " " + ans[1].i);
        } else {
            for (int i = 0; i < 26; i++) {
                if (nodes2[0][i] != null && nodes2[1][i] != null) {
                    out.println(diff - 1);
                    out.println(nodes2[0][i].i + " " + nodes2[1][i].i);
                    System.exit(0);
                }
            }
            out.println(diff);
            out.println("-1 -1");
        }
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
