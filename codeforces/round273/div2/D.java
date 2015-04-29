import java.util.*;
import java.io.*;
public class D {
    static int R, G;
    static int H;
    static int MOD = (int) 1e9 + 7;
    /* This uses too much memory
    static int[][] c = new int[200001][1000];
    static int get(int r, int h, boolean first) {
        if (R < r) return 0;
        if (G < (long) (1 + h) * h / 2 - r) return 0;
        if (!first && h == H) return 1;
        if (c[r][h] == -1) {
            int res = first ? h : 0;
            for (int nr : new int[] {r + (h + 1), r}) {
                int nres = get(nr, h + 1, first);
                if (first) res = Math.max(res, nres);
                else res = (res + nres) % MOD;
            }
            c[r][h] = res;
        }
        return c[r][h];
        }*/
    static int get2() {
        int[] c = new int[R + 1];
        for (int h = H; h >= 0; h--) {
            int tot = (1 + h) * h / 2;
            for (int r = Math.max(0, tot - G); r < c.length; r++) {
                if (h == H) c[r] = 1;
                else {
                    if (R >= r + h + 1)
                        c[r] = (c[r] + c[r + h + 1]) % MOD;
                }
            }
        }
        return c[0];
    }
    static void solve() {
        R = in.nextInt();
        G = in.nextInt();
        H = (int) ((Math.sqrt(1 + 8 * (R + G)) - 1) / 2);
        out.println(get2());
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
