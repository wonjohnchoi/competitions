import java.util.*;
import java.io.*;
public class D {
    static int R, G;
    static long H;
    static long[][] c = new long[200001][1000];
    static long MOD = (long) 1e9 + 7;
    static long get(int r, int h, boolean first) {
        if (R < r) return 0;
        if (G < (long) (1 + h) * h / 2 - r) return 0;
        if (!first && h == H) return 1;
        if (c[r][h] == -1) {
            long res = first ? h : 0;
            for (int nr : new int[] {r + (h + 1), r}) {
                long nres = get(nr, h + 1, first);
                if (first) res = Math.max(res, nres);
                else res = (res + nres) % MOD;
            }
            c[r][h] = res;
        }
        return c[r][h];
    }
    static void solve() {
        R = in.nextInt();
        G = in.nextInt();
        for (int i = 0; i < c.length; i++) Arrays.fill(c[i], -1);
        H = get(0, 0, true);
        for (int i = 0; i < c.length; i++) Arrays.fill(c[i], -1);
        out.println(get(0, 0, false));
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
