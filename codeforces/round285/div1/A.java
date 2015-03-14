import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static class Node {
        int label, degree, xor;
        Node(int label, int degree, int xor) {
            this.label = label;
            this.degree = degree;
            this.xor = xor;
        }
    }
    public static void main(String args[]) {
        int N = in.nextInt();
        Node[] nodes = new Node[N];
        LinkedList<Node> degreeOne = new LinkedList<Node>();
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i, in.nextInt(), in.nextInt());
        }
        for (int i = 0; i < N; i++) {
            if (nodes[i].degree == 1
                && (nodes[nodes[i].xor].degree != 1
                    || i < nodes[i].xor)) {
                degreeOne.add(nodes[i]);
            }
        }
        StringBuilder res = new StringBuilder();
        int cnt = 0;
        while (!degreeOne.isEmpty()) {
            Node cur = degreeOne.remove(0);
            // if (cur.degree != 1) continue;
            res.append("\n" + cur.label + " " + cur.xor);
            cnt++;
            nodes[cur.xor].xor ^= cur.label;
            nodes[cur.xor].degree--;
            if (nodes[cur.xor].degree == 1 && nodes[nodes[cur.xor].xor].degree != 1) degreeOne.add(nodes[cur.xor]);
        }
        out.println(cnt + res.toString());
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
