import java.util.*;
import java.io.*;
public class D {
    static void solve() {
        int R, S, P;
        R = in.nextInt();
        S = in.nextInt();
        P = in.nextInt();
        double[][][] dp = new double[R + 1][S + 1][P + 1];
        dp[R][S][P] = 1.0;
        double rpp, spp, ppp;
        rpp = spp = ppp = 0;
        for (int r = R; r >= 0; r--) {
            for (int s = S; s >= 0; s--) {
                for (int p = P; p >= 0; p--) {
                    if (r == 0) {
                        spp += dp[r][s][p];
                        continue;
                    }
                    if (s == 0) {
                        ppp += dp[r][s][p];
                        continue;
                    }
                    if (p == 0) {
                        rpp += dp[r][s][p];
                        continue;
                    }
                    double rs = r * s;
                    double sp = s * p;
                    double rp = r * p;
                    double tot = rs + sp + rp;
                    dp[r][s - 1][p] += rs / tot * dp[r][s][p];
                    dp[r][s][p - 1] += sp / tot * dp[r][s][p];
                    dp[r - 1][s][p] += rp / tot * dp[r][s][p];
                }
            }
        }
        out.println(rpp + " " + spp + " " + ppp);
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
