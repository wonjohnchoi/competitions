import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static class Node implements Comparable<Node> {
        long x, w;
        Node(long xx, long ww) {
            x = xx;
            w = ww;
        }
        @Override
        public int compareTo(Node n) {
            return Long.compare(x - w, n.x - n.w);
        }
    }
    public static void main(String args[]) {
        int N = in.nextInt();
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(in.nextLong(), in.nextLong());
        }
        Arrays.sort(nodes);
        long[] xw = new long[N];
        for (int i = 0; i < N; i++) {
            xw[i] = nodes[i].x - nodes[i].w;
        }
        long[] dp = new long[N];
        long[] acc = new long[N];
        for (int i = N - 1; i >= 0; i--) {
            long limit = nodes[i].x + nodes[i].w;
            int j = Arrays.binarySearch(xw, limit);
            if (j < 0) j = -(j + 1);
            if (i >= j) throw new RuntimeException();
            // out.println(i + " " + j);
            // j~
            if (j != N)
                dp[i] = acc[j] + 1;
            else
                dp[i] = 1;
            if (i == N - 1)
                acc[i] = dp[i];
            else
                acc[i] = Math.max(dp[i], acc[i + 1]);
        }
        out.println(acc[0]);
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
