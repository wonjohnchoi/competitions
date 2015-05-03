import java.util.*;
import java.io.*;
public class A {
    static void solve() {
        int N = in.nextInt();
        String s1 = in.next();
        String s2 = in.next();
        int tot = 0;
        for (int i = 0; i < N; i++) {
            tot += get(s1.charAt(i) - '0', s2.charAt(i) - '0');
        }
        out.println(tot);
    }
    static int get(int a, int b) {
        if (a < b) return get(b, a);
        return Math.min(a - b, 10 + b - a);
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
