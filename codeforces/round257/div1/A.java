import java.util.*;
import java.io.*;
public class A {
    static void solve() {
        long N, M, K;
        N = in.nextLong();
        M = in.nextLong();
        K = in.nextLong();
        if (K  > N - 1 + M - 1) {
            out.println(-1);
            return;
        }
        long minJ = Math.max(K + 1 -M, 0);
        long maxJ = Math.min(K, N - 1);
        long a = (N / (minJ + 1)) * (M / (K - minJ + 1));
        long b = (N / (maxJ + 1)) * (M / (K - maxJ + 1));
        out.println(Math.max(a, b));
    }
    static PrintWriter out = new PrintWriter(System.out);
    static InputReader in = new InputReader(System.in);
    static void d(Object o) {
        out.println(o);
        out.flush();
    }
    public static void main(String args[]) {
        solve();
        out.close();
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
