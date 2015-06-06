import java.util.*;
import java.io.*;
public class G {
    static void solve() {
        int N = in.nextInt();
        long[] a = new long[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextLong() + i;
        }
        Arrays.sort(a);
        long[] b = new long[N];
        for (int i = 0; i < N; i++) {
            b[i] = a[i] - i;
        }
        for (int i = 0; i < N - 1; i++) {
            if (b[i] > b[i + 1]) {
                out.println(":(");
                return;
            }
        }
        for (int i = 0; i < N; i++) {
            if (i != 0) out.print(" ");
            out.print(b[i]);
        }
        out.println();
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
