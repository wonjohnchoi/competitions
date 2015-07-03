import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        int n, w;
        n = in.nextInt();
        w = in.nextInt();
        int[] a = new int[2 * n];
        for (int i = 0; i < a.length; i++) {
            a[i] = in.nextInt();
        }
        Arrays.sort(a);
        long y = a[0];
        long x = a[n];
        long v = Math.min(Math.min(x, 2 * y) * 3L * n, w * 2L);
        out.println(v / 2.0);
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
