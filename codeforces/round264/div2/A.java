import java.util.*;
import java.io.*;
public class A {
    static void solve() {
        long N, S;
        N = in.nextLong();
        S = in.nextLong();
        long best = -1;
        for (int i = 0; i < N; i++) {
            long X, Y;
            X = in.nextLong();
            Y = in.nextLong();
            if (S * 100 >= X * 100 + Y) {
                if (best == -1 || best < (100 - Y) % 100)
                    best = (100 - Y) % 100;
            }
        }
        out.println(best);
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
