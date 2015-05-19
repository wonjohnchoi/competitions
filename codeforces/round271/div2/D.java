import java.util.*;
import java.io.*;
public class D {
    static int add(int a, int b) {
        return (a + b) % MOD;
    }
    static int MOD = (int) 1e9 + 7;
    static void solve() {
        int T = in.nextInt();
        int K = in.nextInt();
        int MAX = 100001;
        int[] g = new int[MAX];
        int[] f = new int[MAX];
        g[0] = 1;
        for (int i = 1; i < MAX; i++) {
            g[i] = add(g[i - 1], g[i]);
            if (i - K >= 0) g[i] = add(g[i - K], g[i]);
        }
        f[0] = 0;
        for (int i = 1; i < MAX; i++) {
            f[i] = add(f[i - 1], g[i]);
        }
        for (int t = 0; t < T; t++) {
            int a, b;
            a = in.nextInt();
            b = in.nextInt();
            out.println(add(f[b] - f[a - 1], MOD));
        }
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
