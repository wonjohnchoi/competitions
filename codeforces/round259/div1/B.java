import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        int N = in.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        int MAXA = 54;
        int[] pr = new int[MAXA];
        Arrays.fill(pr, -1);
        int r = 0; // max 16
        outer : for (int i = 2; i < MAXA; i++) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    continue outer;
                }
            }
            pr[i] = r++;
        }
        int[] factors = new int[MAXA];
        for (int i = 0; i < MAXA; i++) {
            int f = 0;
            for (int j = 2; j <= i; j++) {
                if (pr[j] != -1 && (i % j == 0)) {
                    f |= 1 << pr[j];
                }
            }
            factors[i] = f;
        }
        List<List<Integer>> bb2 = new ArrayList<>();
        for (int i = 0; i < 1 << r; i++) {
            List<Integer> bbb = new ArrayList<>();
            for (int j = 1; j < MAXA; j++) {
                int mask = factors[j];
                if ((i & mask) == 0) bbb.add(j);
            }
            bb2.add(bbb);
        }
        // out.println(r); out.flush();
        int[][] dp = new int[1 << r][N];
        int[][] dp2 = new int[1 << r][N];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < N; i++) {
            for (int k = 0; k < dp.length; k++) {
                if (!((i == 0 && k == 0) || (i >= 1 && dp[k][i - 1] != -1))) continue;
                for (int bb : bb2.get(k)) {
                    int nk = k | factors[bb];
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
            mask ^= factors[b[i]];
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
