import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static class Node {
        Node n1, n2;
        int l, r;
        int max;
        Node(int l, int r) {
            n1 = n2 = null;
            this.l = l;
            this.r = r;
            max = r - l;
        }
        int insert(int x) {
            if (n1 == null) {
                n1 = new Node(l, x);
                n2 = new Node(x, r);
            } else if (n1.r == x) {
                return -1;
            } else if (n1.r > x) {
                n1.insert(x);
            } else {
                n2.insert(x);
            }
            max = Math.max(n1.max, n2.max);
            return 0;
        }
    }
    public static void main(String args[]) {
        int W, H, N;
        W = in.nextInt();
        H = in.nextInt();
        N = in.nextInt();
        Node tx = new Node(0, W);
        Node ty = new Node(0, H);
        for (int i = 0; i < N; i++) {
            String s = in.next();
            int p = in.nextInt();
            if (s.equals("H")) {
                ty.insert(p);
            } else {
                tx.insert(p);
            }
            out.println((long) ty.max * tx.max);
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
