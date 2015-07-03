import java.util.*;
import java.io.*;
public class A {
    static void solve() {
        int N = in.nextInt();
        int[][] vals = new int[2][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                vals[j][i] = in.nextInt();
            }
        }
        for (int i = 0; i < 3; i++) {
            int cur = 0;
            for (int j = 0; j < 3; j++) {
                cur += vals[0][j];
            }
            // out.println(cur + " " + N);
            if (cur < N) {
                int need = N - cur;
                int use = Math.min(need, vals[1][i] - vals[0][i]);
                vals[0][i] += use;
                // out.println(i + " " + use);
            }
        }
        out.println(vals[0][0] + " " + vals[0][1] + " " + vals[0][2]);
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
