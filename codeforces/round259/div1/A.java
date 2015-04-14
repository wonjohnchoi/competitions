import java.util.*;
import java.io.*;
public class A {
    static void solve() {
        double M = in.nextDouble();
        int N = in.nextInt();
        double tot = M;
        for (double i = 1; i < M; i++) {
            tot -= pow(i / M, N);
        }
        out.println(tot);
    }
    static double pow(double x, int y) {
        if (y == 0) return 1;
        if (y % 2 == 1) return pow(x, y - 1) * x;
        double z = pow(x, y / 2);
        return z * z;
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
