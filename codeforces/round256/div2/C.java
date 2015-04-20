import java.util.*;
import java.io.*;
public class C {
    static void solve() {
        int N = in.nextInt();
        long[] a = new long[N];
        HashMap<Long, Integer> hIMap = new HashMap<>();
        long[] h = new long[N + 1];
        int hs = 0;
        for (int i = 0; i < N; i++) {
            a[i] = in.nextLong();
            if (!hIMap.containsKey(a[i])) {
                h[hs] = a[i];
                hIMap.put(a[i], hs++);
            }
        }
        h[hs] = 0;
        hIMap.put(0L, hs++);
        long[][] dp = new long[N][hs];
        long inf = (long) 1e14;
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], inf);
        }
        dp[0][0] = a[0];
        dp[0][hs - 1] = Math.min(1, dp[0][0]);
        long best = inf;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < hs; j++) {
                if (dp[i - 1][j] != inf) {
                    int k = hIMap.get(a[i]);
                    if (h[j] >= h[k]) {
                        dp[i][k] = Math.min(dp[i][k], dp[i - 1][j]);
                    } else {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                        dp[i][k] = Math.min(dp[i][k], dp[i - 1][j] + h[k] - h[j]);
                    }
                }
            }
        }
        for (int i = 0; i < hs; i++) {
            best = Math.min(best, dp[N - 1][i]);
        }
        out.println(best);
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
