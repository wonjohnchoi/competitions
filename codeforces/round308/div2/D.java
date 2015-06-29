import java.util.*;
import java.io.*;
public class D {
    static void solve() {
        int N = in.nextInt();
        int[] x = new int[N];
        int[] y = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        if (N < 3) {
            out.println(0);
            return;
        }
        long tot = (long) N * (N - 1) * (N - 2);
        for (int i = 0; i < N; i++) {
            long[][] cnt = new long[401][401];
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    int[] key = getKey(x[i] - x[j], y[i] - y[j]);
                    cnt[key[0] + 200][key[1] + 200]++;
                }
            }
            for (int j = 0; j < 401; j++) {
                for (int k = 0; k < 401; k++) {
                    if (cnt[j][k] >= 2) {
                        tot -= cnt[j][k] * (cnt[j][k] - 1);
                    }
                }
            }
        }
        out.println(tot / 6);
    }
    static int[] getKey(int x, int y) {
        int gcd = gcd(Math.abs(x), Math.abs(y));
        x /= gcd;
        y /= gcd;
        if (x < 0) {
            x = -x; y = -y;
        }
        if (x == 0 && y < 0) {
            y = -y;
        }
        return new int[] { x, y };
    }
    static int gcd(int x, int y) {
        // out.println(x + " " + y); out.flush();
        if (x > y) return gcd(y, x);
        if (x == 0) return y;
        return gcd(y % x, x);
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
