import java.util.*;
import java.io.*;
public class C {
    static void solve() {
        int N = in.nextInt();
        int[] x = new int[N];
        int[] h = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = in.nextInt();
            h[i] = in.nextInt();
        }
        int cur = Integer.MIN_VALUE;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (cur < x[i] - h[i]) {
                cur = x[i];
                cnt++;
            } else if (i + 1 == N || x[i] + h[i] < x[i + 1]) {
                cur = x[i] + h[i];
                cnt++;
            } else {
                cur = x[i];
            }
        }
        out.println(cnt);
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
