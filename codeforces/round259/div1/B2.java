import java.util.*;
import java.io.*;
public class B2 {
    static void solve() {
        int N = in.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        int MAXA = 101;
        int[] pr = new int[31];
        boolean[] prime = new boolean[31];
        int r = 0;
        outer : for (int i = 2; i < 31; i++) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    continue outer;
                }
            }
            pr[i] = r++;
            prime[i] = true;
        }
        int[][] dp = new int[1 << r][N];
        int[][] dp2 = new int[1 << r][N];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < N; i++) {
            for (int k = 0; k < dp.length; k++) {
                if (!((i == 0 && k == 0) || (i >= 1 && dp[k][i - 1] != -1))) continue;
                cand : for (int bb = 1; bb < 31; bb++) {
                    int nk = k;
                    for (int j = 2; j <= bb; j++) {
                        if (prime[j] && (bb % j == 0)) {
                            int mask = 1 << pr[j];
                            if ((nk & mask) == 0) {
                                nk |= mask;
                            } else {
                                continue cand;
                            }
                        }
                    }
                    int val = (i > 0 ? dp[k][i - 1] : 0) + Math.abs(bb - a[i]);
                    if (dp[nk][i] == -1 || dp[nk][i] > val) {
                        dp[nk][i] = val;
                        dp2[nk][i] = bb;
                    }
                }
            }
        }
        int mask = -1;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i][N - 1] != -1) {
                if (mask == -1 || dp[mask][N - 1] > dp[i][N - 1])
                    mask = i;
            }
        }
        int[] b = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            b[i] = dp2[mask][i];
            for (int j = 2; j <= b[i]; j++) {
                if (prime[j] && (b[i] % j == 0)) {
                    if ((mask & (1 << pr[j])) == 0) throw new RuntimeException("WTF");
                    mask ^= 1 << pr[j];
                }
            }
            // out.println(b[i] + " " + mask);
        }
        for (int i = 0; i < N; i++) {
            if (i != 0) out.print(" ");
            out.print(b[i]);
        }
        out.println();
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
