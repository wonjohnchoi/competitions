import java.util.*;
import java.io.*;
public class A {
    static void solve() {
        int N, M;
        N = in.nextInt();
        M = in.nextInt();
        int[] x = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = in.nextInt();
        }
        double best = 0.0;
        for (int i = 0; i < M; i++) {
            int a, b, c;
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;
            c = in.nextInt();
            best = Math.max(best, ((double) x[a] + x[b]) / c);
        }
        out.println(best);
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
