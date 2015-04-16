import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        int N = in.nextInt();
        int[] a = new int[N];
        int[] sa = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
            sa[i] = a[i];
        }
        Arrays.sort(sa);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (a[i] != sa[i]) {
                min = Math.min(min, i);
                max = Math.max(max, i);
            }
        }
        for (int i = min; i <= (min + max) / 2; i++) {
            int j = min + max - i;
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
        for (int i = 0; i < N; i++) {
            if (a[i] != sa[i]) {
                out.println("no");
                return;
            }
        }
        out.println("yes");
        if (min <= max)
            out.println((min + 1) + " " + (max + 1));
        else out.println(1 + " " + 1);
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
