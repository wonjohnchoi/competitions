import java.util.*;
import java.io.*;
public class B {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N = in.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        int[] b = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            b[i] = in.nextInt();
        }
        int[] c = new int[N - 2];
        for (int i = 0; i < N - 2; i++) {
            c[i] = in.nextInt();
        }
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        out.println(f(a, b));
        out.println(f(b, c));
        out.close();
    }
    static int f(int[] a, int[] b) {
        for (int i = 0; i < b.length; i++) {
            if (a[i] != b[i]) return a[i];
        }
        return a[a.length - 1];
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
