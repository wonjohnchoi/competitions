import java.util.*;
import java.io.*;
public class IOPC15C {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static class Edge {
        Node[] nodes = new Node[2];
        long c;
        long[]
        public Edge(Node n1, Node n2, long c) {
            this.u = u;

        }
    }
    static class Node {
        List<Edge> edges = new ArrayList<>();
    }
    static Node[] nodes;
    static Edge[] edges;
    public static void main(String args[]) {
        int T = in.nextInt();
        for (int tc = 0; tc < T; tc++) {
            int N = in.nextInt();
            int u, v;
            long c;
            u = in.nextInt();
            v = in.nextInt();
            c = in.nextLong();
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
