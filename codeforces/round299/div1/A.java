import java.util.*;
import java.io.*;
public class A {
    static long A, B, N;
    static long L, T, M;
    static void solve() {
        A = in.nextLong();
        B = in.nextLong();
        N = in.nextLong();
        for (int i = 0; i < N; i++) {
            L = in.nextLong();
            T = in.nextLong();
            M = in.nextLong();
            int ll = 0;
            int rr = 1000;
            long j;
            if (T < A + B * (L - 1)) {
                j = -1;
            } else {
                while (ll < rr) {
                    int m = (ll + rr) / 2;
                    long k = f(m);
                    if (T < k) {
                        rr = m;
                    } else if (T >= k) {
                        ll = m + 1;
                    }
                }
                j = rr;
            }
            long k = -1;
            if (j != -1) {
                if (j != 0) {
                    T -= f(j - 1);
                } else {
                    T -= A + B * (L - 1);
                }
                k = 1;
                int nb = Math.min(T / B, M - 1);
                k += nb;
            }
            if (j == -1) {
                out.println(-1);
            } else {
                // out.println(j + " " + k);
                out.println(L - 1 + j * M + k);
            }
        }
    }
    static long f(long i) {
        return A * (i + 1) + B * (L * (i + 1) + (2 + i) * (i + 1) / 2 * M - 2 * (i + 1))
            + A + B * (L + i);
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
