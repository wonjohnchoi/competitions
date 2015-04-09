import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        long N = in.nextLong();
        long e = 0;
        long h = 0;
        long c = 0;
        for (int i = 0; i < N; i++) {
            long h2 = in.nextLong();
            e += h - h2;
            h = h2;
            if (e < 0) {
                c += -e;
                e = 0;
            }
        }
        out.println(c);
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
