import java.util.*;
import java.io.*;
public class E {
    static void solve() {
        int X, K, P;
        X = in.nextInt();
        K = in.nextInt();
        P = in.nextInt();
        int MASK = (1 << 8) - 1;
        double[][][][] dp = new double[2][MASK + 1][2][300];
        for (int mask = 0; mask < dp[0].length; mask++) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 231; j++) {
                    int cnt = 0;
                    for (int k = 0; k < 8; k++) {
                        if ((mask & (1 << k)) == 0) cnt++;
                        else break;
                    }
                    if (cnt == 8 && i == 0) {
                        cnt += j;
                    }
                    dp[0][mask][i][j] = cnt;
                }
            }
        }
        for (int k = 1; k < K + 1; k++) {
            for (int mask = 0; mask < dp[0].length; mask++) {
                for (int last = 0; last < 2; last++) {
                    for (int cnt = 0; cnt < 299; cnt++) {
                        int nlast = mask >> 8;
                        int ncnt = 0;
                        if (nlast == last) ncnt = cnt + 1;
                        else ncnt = 1;
                        double a = dp[0][(mask << 1) & MASK][nlast][ncnt];
                        double b;
                        if (mask + 1 <= MASK) b = dp[0][mask + 1][last][cnt];
                        else {
                            if (last == 0) {
                                nlast = 1;
                                ncnt = 0;
                            } else {
                                nlast = 0;
                                ncnt = cnt;
                            }
                            b = dp[0][(mask + 1) & MASK][nlast][ncnt];
                        }
                        dp[1][mask][last][cnt] = a * P / 100 + b * (100 - P) / 100;
                    }
                }
            }
            dp[0] = dp[1];
        }
        int cnt = 0;
        int last = MASK >> 8;
        for (int i = 8; i < 31; i++) {
            if (last == (MASK >> i)) {
                cnt++;
            } else break;
        }
        out.println(dp[0][X & MASK][last][cnt]);
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
