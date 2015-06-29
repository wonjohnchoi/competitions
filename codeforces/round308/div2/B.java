import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        int N = in.nextInt();
        // 1 ~ N
        long tot = 0;
        for (int i = 0; i < 10; i++) {
            int s = (int) Math.pow(10, i);
            int e = (int) Math.min(Math.pow(10, i + 1), N + 1);
            // s ~ e - 1
            // i + 1
            if (s <= N) {
                tot += (long) (e - s) * (i + 1);
            }
        }
        out.println(tot);
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
