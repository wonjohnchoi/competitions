import java.util.*;
import java.io.*;
public class A {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    static int TC;
    static String solve(int tc) {
        int N = in.nextInt();
        int[] tot = new int[2];
        int[] a = new int[N];
        int most = 0;
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }

        for (int i = 0; i < N; i++) {
            if (i != N - 1 && a[i] > a[i + 1]) {
                tot[0] += a[i] - a[i + 1];
                most = Math.max(most, a[i] - a[i + 1]);
            }
        }
        for (int i = 0; i < N; i++) {
            if (i != N - 1) {
                int eat = Math.min(a[i], most);
                tot[1] += eat;
            }
        }
        return (tot[0] + " " + tot[1]);
    }
    public static void main(String args[]) {
        TC = in.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            out.printf("Case #%d: %s\n", tc, solve(tc));
        }
        out.close();
    }
    static void d(Object o) {
        out.println(o);
        out.flush();
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
