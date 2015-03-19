import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static int N;
    public static void main(String args[]) {
        long L, X, Y;
        N = in.nextInt();
        L = in.nextLong();
        X = in.nextLong();
        Y = in.nextLong();
        long[] a = new long[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextLong();
        }
        boolean hx = have(a, X);
        boolean hy = have(a, Y);
        if (hx && hy) {
            out.println(0);
            System.exit(0);
        } else if (hx) {
            out.println(1);
            out.println(Y);
            System.exit(0);
        } else if (hy) {
            out.println(1);
            out.println(X);
            System.exit(0);
        } else {
            for (int i = 0; i < N; i++) {
                for (long x1 : new long[] {X, -X}) {
                    for (long y1 : new long[] {Y, -Y}) {
                        if (a[i] + x1 >= 0 && a[i] + x1 <= a[a.length - 1]) {
                            if (Arrays.binarySearch(a, a[i] + x1 + y1) >= 0) {
                                out.println(1);
                                out.println(a[i] + x1);
                                System.exit(0);
                            }
                        }
                    }
                }
            }
        }
        out.println(2);
        out.println(X + " " + Y);
    }
    static boolean have(long[] a, long x) {
        for (int i = 0; i < N; i++) {
            if (Arrays.binarySearch(a, a[i] + x) >= 0) return true;
        }
        return false;
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
