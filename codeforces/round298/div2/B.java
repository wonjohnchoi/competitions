import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        int V1, V2, T, D;
        V1 = in.nextInt();
        V2 = in.nextInt();
        T = in.nextInt();
        D = in.nextInt();
        int cur = V1;
        int tot = cur;
        for (int i = 1; i < T; i++) {
            int next = Math.min((T - i - 1) * D + V2, cur + D);
            // out.println(next); out.flush();
            tot += next;
            cur = next;
        }
        out.println(tot);
    }
    static PrintWriter out = new PrintWriter(System.out);
    static InputReader in = new InputReader(System.in);
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
