import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        int N = in.nextInt();
        double[] a = new double[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextDouble();
        }
        Arrays.sort(a);
        if (a[N - 1] >= 0.5 || N == 1) {
            out.println(a[N - 1]);
            return;
        }
        double[] mult = new double[N];
        double aa = 0.0;
        double b = 1.0;
        for (int i = N - 1; i >= 0 ; i--) {
            aa = aa * (1 - a[i]) + b * a[i];
            b = b * (1 - a[i]);
            mult[i] = aa;
            //out.println(aa);
        }
        int i = N - 1;
        for (; i >= 1; i--) {
            if (mult[i] > mult[i - 1]) {
                break;
            }
        }
        out.println(mult[i]);
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
