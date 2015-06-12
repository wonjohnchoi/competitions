import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        String a, b, c;
        a = in.next();
        b = in.next();
        c = in.next();
        long[] acnt = cnt(a);
        long[] bcnt = cnt(b);
        long[] ccnt = cnt(c);
        long max = -1;
        long bestBB = -1;
        long bestCC = -1;
        outer : for (long bb = 0; bb <= 100000; bb++) {
            long min = Long.MAX_VALUE;
            for (int i = 0; i < 26; i++) {
                long rem = acnt[i] - bb * bcnt[i];
                if (rem < 0) continue outer;
                if (ccnt[i] != 0) min = Math.min(min, (long) (rem / ccnt[i]));
            }
            if (max < min + bb) {
                max = min + bb;
                bestBB = bb;
                bestCC = min;
            }
        }
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < bestBB; i++) {
            ret.append(b);
        }
        for (int i = 0; i < bestCC; i++) {
            ret.append(c);
        }
        for (int i = 0; i < 26; i++) {
            acnt[i] -= bcnt[i] * bestBB;
            acnt[i] -= ccnt[i] * bestCC;
        }
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < acnt[i]; j++) {
                ret.append((char) (i + 'a'));
            }
        }
        out.println(ret);
    }
    static long[] cnt(String a) {
        long[] ret = new long[26];
        for (char c : a.toCharArray()) {
            ret[c - 'a']++;
        }
        return ret;
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
