import java.util.*;
import java.io.*;
public class A {
    static void solve() {
        long[][] a = new long[2][2];
        a[0][0] = in.nextLong();
        a[0][1] = in.nextLong();
        a[1][0] = in.nextLong();
        a[1][1] = in.nextLong();
        long[][] b = new long[2][2];
        if (a[0][0] == a[1][0]) {
            long size = Math.abs(a[1][1] - a[0][1]);
            b[1][0] = b[0][0] = a[0][0] + size;
            b[0][1] = a[0][1];
            b[1][1] = a[1][1];
        } else if (a[0][1] == a[1][1]) {
            long size = Math.abs(a[0][0] - a[1][0]);
            b[0][1] = b[1][1] = a[1][1] + size;
            b[1][0] = a[1][0];
            b[0][0] = a[0][0];
        } else {
            long s1 = Math.abs(a[0][0] - a[1][0]);
            long s2 = Math.abs(a[0][1] - a[1][1]);
            if (s1 != s2) {
                out.println(-1);
                return;
            }
            b[0] = new long[] {a[0][0], a[1][1]};
            b[1] = new long[] {a[1][0], a[0][1]};
        }
        out.println(b[0][0] + " " + b[0][1] + " " + b[1][0] + " " + b[1][1]);
    }
    static PrintWriter out = new PrintWriter(System.out);
    static InputReader in = new InputReader(System.in);
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
    /*
    public int DONTUSEnextInt() {
	return Integer.parseInt(next());
        }*/
}
