import java.util.*;
import java.io.*;
public class D {
    static void solve() {
        int N = (int) in.nextLong();
        long K = in.nextLong();
        boolean[][] g = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(g[i], true);
        }
        for (int i = 0; i < K; i++) {
            int[] a = new int[N];
            for (int j = 0; j < N; j++) {
                a[j] = (int) in.nextLong() - 1;
                for (int k = 0; k < j; k++) {
                    g[a[j]][a[k]] = false;
                }
            }
        }
        long max = 0;
        dp = new long[N]; Arrays.fill(dp, -1);
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dfs(i, g, new boolean[N]));
        }
        out.println(max);
    }
    static long[] dp;
    static long dfs(int cur, boolean[][] g, boolean[] marked) {
        if (dp[cur] != -1) return dp[cur];
        marked[cur] = true;
        long max = 1;
        for (int i = 0; i < marked.length; i++) {
            if (!marked[i] && g[cur][i]) {
                max = Math.max(max, dfs(i, g, marked) + 1);
            }
        }
        marked[cur] = false;
        dp[cur] = max;
        return max;
    }
    static PrintWriter out = new PrintWriter(System.out);
    static InputReader in = new InputReader(System.in);
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
    /*
    public int DONTUSEnextInt() {
	return Integer.parseInt(next());
        }*/
}
