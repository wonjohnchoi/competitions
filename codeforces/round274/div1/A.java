import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static class Node implements Comparable<Node> {
        int a, b;
        Node(int aa, int bb) {
            a = aa; b= bb;
        }
        @Override
        public int compareTo(Node n) {
            return Integer.compare(a, n.a);
        }
    }
    public static void main(String args[]) {
        int N = in.nextInt();
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(in.nextInt(), in.nextInt());
        }
        Arrays.sort(nodes);
        int k = 0;
        for (int i = 0; i < N;) {
            int s = nodes[i].a;
            int min = Integer.MAX_VALUE;
            int max = 0;
            while (i < N && nodes[i].a == s) {
                max = Math.max(max, nodes[i].b);
                min = Math.min(min, nodes[i].b);
                i++;
            }
            if (min < k) {
                k = s;
            } else {
                k = max;
            }
        }
        out.println(k);
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
