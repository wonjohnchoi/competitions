import java.util.*;
import java.io.*;
public class C {
    static HashMap<Long, Boolean> cache = new HashMap<>();
    static boolean poss(int k, int m, int w) {
        // k = 0 ~ 100
        if (k == 0) {
            if (-1 <= m && m <= 1) return true;
            else return false;
        }
        long key = 101L * m + k;
        if (!cache.containsKey(key)) {
            boolean ret = false;
            for (int i = -1; i <= 1; i++) {
                if ((m + i) % w == 0) {
                    int nm = (m + i) / w;
                    if (poss(k - 1, nm, w)) {
                        ret = true;
                        break;
                    }
                }
            }
            cache.put(key, ret);
        }
        return cache.get(key);
    }
    static void solve() {
        int w = in.nextInt();
        int m = in.nextInt();
        if (poss(100, m, w)) {
            out.println("YES");
        } else {
            out.println("NO");
        }
        for (int i = 0; i < 101; i++) {

        }

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
