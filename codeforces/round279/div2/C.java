import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        String S = in.next();
        long A = in.nextLong();
        long B = in.nextLong();
        long[] cacheA = new long[S.length()];
        long[] cacheB = new long[S.length()];
        long a = 0;
        for (int i = 0; i < S.length(); i++) {
            a *= 10;
            a += (long) (S.charAt(i) - '0');
            a %= A;
            cacheA[i] = a;
        }
        long pow = 1;
        long b = 0;
        for (int i = S.length() - 1; i >= 0; i--) {
            b += (long) (S.charAt(i) - '0') * pow;
            b %= B;
            pow *= 10;
            pow %= B;
            cacheB[i] = b;
        }
        for (int i = 1; i < S.length(); i++) {
            // i - 1, i
            if (S.charAt(i) != '0') {
                if (cacheA[i - 1] == 0 && cacheB[i] == 0) {
                    out.println("YES");
                    out.println(S.substring(0, i));
                    out.println(S.substring(i));
                    System.exit(0);
                }
            }
        }
        out.println("NO");
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
