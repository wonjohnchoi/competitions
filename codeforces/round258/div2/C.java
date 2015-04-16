import java.util.*;
import java.io.*;
public class C {
    static void solve() {
        int T = in.nextInt();
        outer : for (int i = 0; i < T; i++) {
            long n, k, d1, d2;
            n = in.nextLong();
            k = in.nextLong();
            d1 = in.nextLong();
            d2 = in.nextLong();
            for (long s2 : new long[] {d2, -d2}) {
                for (long s1 : new long[] {s2 + d1, s2 - d1}) {
                    if (f(s1, s2, k)) {
                        if (s1 < s2) {
                            long tmp = s1;
                            s1 = s2;
                            s2 = tmp;
                        }
                        if (f(s1 - s2, s1, n - k)) {
                            out.println("yes");
                            continue outer;
                        }
                    }
                }
            }
            out.println("no");
        }
    }
    static boolean f(long s1, long s2, long k) {
        boolean b1 = (k - s1 - s2) % 3 == 0;
        long x = (k - s1 - s2) / 3;
        return b1 && x >= 0 && (x + s1) >= 0 && (x + s2) >= 0;
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
