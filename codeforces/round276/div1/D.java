import java.util.*;
import java.io.*;
import java.math.BigInteger;
public class D {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N = in.nextInt();
        long[] a = new long[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        boolean[] b = new boolean[N - 1]; // b[i] => i | i + 1
        for (int i = 0; i < N - 1; i++) {
            if (a[i] == a[i + 1]) b[i] = true;
        }
        for (int i = 0; i < N - 2; i++) {
            if (a[i] < a[i + 1] && a[i + 1] > a[i + 2]) {
                if (a[i + 2] < a[i + 3]) {
                    if (a[i + 1] - a[i + 2] > a[i + 1] - a[i] + a[i + 3] - a[i + 2]) {
                        b[i] = true;
                        b[i + 2] = true;
                        i += 2; // ok i give up.
                    }
                } else {
                    if (a[i + 1] - a[i] > a[i + 1] - a[i + 2]) {
                        b[i + 1] = true;
                    } else {
                        b[i] = true;
                    }
                }
            }
            if (a[i] > a[i + 1] && a[i + 1] < a[i + 2]) {
                if (a[i] - a[i + 1] > a[i + 2] - a[i + 1]) {
                    b[i + 1] = true;
                } else {
                    b[i] = true;
                }
            }
        }
        long min = a[0];
        long max = a[0];
        BigInteger tot = BigInteger.ZERO;
        for (int i = 0; i < N - 1; i++) {
            if (b[i]) {
                tot = tot.add(new BigInteger((max - min) + ""));
                min = a[i + 1];
                max = a[i + 1];
            } else {
                min = Math.min(min, a[i + 1]);
                max = Math.max(max, a[i + 1]);
            }
        }
        tot = tot.add(new BigInteger((max - min) + ""));
        out.println(tot);
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
