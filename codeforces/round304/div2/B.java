import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        int N = in.nextInt();
        int[] a = new int[N];
        int tot = 0;
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
            tot += a[i];
        }
        Arrays.sort(a);
        int tot2 = 0;
        for (int i = 0; i < N; i++) {
            if (i != 0) if (a[i] <= a[i - 1]) a[i] = a[i - 1] + 1;
            tot2 += a[i];
        }
        out.println(tot2 - tot);
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
