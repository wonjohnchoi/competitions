import java.util.*;
import java.io.*;
public class D {
    static void solve() {
        long N, M, K;
        N = in.nextLong();
        M = in.nextLong();
        K = in.nextLong();
        long l = 1;
        long r = N * M;
        while (l < r) {
            long m = (l + r) / 2;
            long le = 0;
            for (int i = 1; i <= N; i++) {
                le += Math.min(m / i, M);
            }
            // out.println(m + " " + le);
            if (le < K) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        out.println(l);
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
