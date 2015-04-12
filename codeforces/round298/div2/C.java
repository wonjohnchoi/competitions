import java.util.*;
import java.io.*;
public class C {
    static void solve() {
        long N, A;
        N = in.nextLong();
        A = in.nextLong();
        long tot = 0;
        long[] d = new long[(int) N];
        for (int i = 0; i < N; i++) {
            d[i] = in.nextLong();
            tot += d[i];
        }
        for (int i = 0; i < N; i++) {
            long l = A - tot + d[i]; // <= 1
            long r = A - (N - 1); // >= d[i]
            long tot2  = 0;
            if (1 <= l && r <= d[i]) tot2 = r - l + 1;
            else if (l <= 1 && d[i] <= r) tot2 = d[i];
            else if (1 <= l && l <= d[i]) tot2 = d[i] - l + 1;
            else if (1 <= r && r <= d[i]) tot2 = r;
            if (i != 0) out.print(" ");
            out.print(d[i] - tot2);
        }
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
