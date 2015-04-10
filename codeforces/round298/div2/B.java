import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        int N = (int) in.nextLong();
        long[] b = new long[N];
        long[] eb = new long[] {Long.MAX_VALUE, Long.MIN_VALUE};
        for (int i = 0; i < N; i++) {
            b[i] = in.nextLong();
            eb[0] = Math.min(eb[0], b[i]);
            eb[1] = Math.max(eb[1], b[i]);
        }
        long[] cb = new long[2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                if (eb[j] == b[i]) cb[j]++;
            }
        }
        long tot = 0;
        if (eb[0] == eb[1]) tot = (long) N * (N - 1) / 2;
        else tot = cb[0] * cb[1];
        out.println(eb[1] - eb[0] + " " + tot);
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
    /*
    public int DONTUSEnextInt() {
	return Integer.parseInt(next());
        }*/
}
