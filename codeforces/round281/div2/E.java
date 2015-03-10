import java.util.*;
import java.io.*;
public class E {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static long MOD = (long) (1e9 + 7);
    public static void main(String args[]) {
        long T, A, B;
        T = in.nextLong();
        A = in.nextLong();
        B = in.nextLong();
        if (T == 1 && A == 1) {
            if (B == 1) {
                out.println("inf");
            } else {
                out.println(0);
            }
        } else
            out.println(f(T, A, A, B));
    }
    // http://codeforces.com/contest/493/submission/8968299
    static HashMap<Long, HashMap<Long, Long>> cache = new HashMap<>();
    static long f(long a, long b, long c, long d) {
        if (b == 0 && d == 0) return 1;
        if (b == 0 || d == 0) return 0;
        HashMap<Long, Long> cache1 = cache.get(b);
        if (cache1 != null) {
            Long cache2 = cache1.get(d);
            if (cache2 != null) return cache2;
        } else {
            cache1 = new HashMap<>();
            cache.put(b, cache1);
        }
        long ret = 0;
        for (int k = 0; d % c + c * k <= b; k++) {
            long a0 = d % c + c * k;
            if (a0 % a == b % a) {
                ret += f(a, (b - a0) / a, c, (d - a0) / c);
                ret %= MOD;
            }
        }
        cache1.put(d, ret);
        return ret;
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
