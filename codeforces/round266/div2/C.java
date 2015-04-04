import java.util.*;
import java.io.*;
public class C {
    static void solve() {
        int N = in.nextInt();
        if (N < 3) {
            out.println(0);
            return;
        }
        long[] accum = new long[N - 1];
        for (int i = 0; i < N - 1; i++) {
            int a = in.nextInt();
            if (i == 0) accum[0] = a;
            else accum[i] = a + accum[i - 1];
        }
        long last = accum[N - 2] + in.nextInt();
        if (last % 3 != 0) {
            out.println(0);
            return;
        }
        long[] cntT2 = new long[N - 1];
        for (int i = N - 3; i >= 0; i--) {
            cntT2[i] = cntT2[i + 1];
            if (accum[i + 1] == last * 2 / 3) cntT2[i]++;
        }
        long tot = 0;
        for (int i = 0; i < N - 1; i++) {
            if (accum[i] == last / 3) {
                tot += cntT2[i];
            }
        }
        out.println(tot);
        return;
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
