import java.util.*;
import java.io.*;
public class D {
    static void solve() {
        long L, R, K;
        L = in.nextLong();
        R = in.nextLong();
        K = in.nextLong();
        if (K == 1) {
            long cur = L;
            while (Long.bitCount(cur) >= 2) {
                long next = next(cur);
                if (next > R) {
                    break;
                }
                cur = next;
            }
            out.println(Long.bitCount(cur));
            out.println(1);
            out.println(cur);
        } else if (K == 2) {

        } else if (R - L <= 10) {

        } else {

        }
    }

    static long next(long v) {
        int cnt = 0;
        long i = 0;
        while (cnt < 2) {
            while (v & (1L << i) == 1) {
                cnt++;
                i++;
            } else {
                i++;
            }
        }
        v += 1L << i;
        return v;
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
