import java.util.*;
import java.io.*;
public class A {
    static void solve() {
        int[] a = new int[6];
        for (int i = 0; i < a.length; i++) {
            a[i] = in.nextInt();
        }
        int big = a[0] + a[1] + a[2];
        int[] small = new int[] {
            a[0], a[2], a[4]
        };
        int ans = 0;
        ans += tri(big);
        for (int i = 0; i < small.length; i++) {
            ans -= tri(small[i]);
        }
        out.println(ans);
    }
    static int tri(int a) {
        return a * a;
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
