import java.util.*;
import java.io.*;
public class A {
    static void solve() {
        int N, M, B, MOD;
        N = in.nextInt();
        M = in.nextInt();
        B = in.nextInt();
        MOD = in.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        int[][] dp = new int[B + 1][M + 1];
        dp[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int b = 0; b <= B; b++) {
                for (int m = 0; m <= M; m++) {
                    int nb = b + a[i];
                    int nm = m + 1;
                    if (nb <= B && nm <= M) {
                        dp[nb][nm] += dp[b][m];
                        dp[nb][nm] %= MOD;
                    }
                }
            }
        }
        int tot = 0;
        for (int i = 0; i <= B; i++) {
            tot += dp[i][M];
            tot %= MOD;
        }
        out.println(tot);
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
