import java.util.*;
import java.io.*;
public class D {
    static List<List<Integer>> adj;
    static List<LIst<Integer>> before;
    static List<List<Integer>> after;
    static long[] tot;
    static long[] sol;
    static long MOD = (long) 1e9 + 7;
    static void solve() {
        int N = in.nextInt();
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Integer>());
            before.add(new ArrayList<Integer>());
            after.add(new ArrayList<Integer>());
        }
        for (int i = 1; i < N; i++) {
            int j = in.nextInt() - 1;
            adj.get(i).add(j);
            adj.get(j).add(i);
        }
        tot = new long[N];
        sol = new long[N];
        dfs(0, -1, true);
        dfs2(0, -1);
        out.print(sol[0]);
        for (int i = 1; i < N; i++) {
            out.print(" " + sol[i]);
        }
        out.println();
    }
    static void trans(int from, int to) {
        dfs(from, to, false);
        dfs(to, -1, false);
    }
    static void dfs(int v, int p, boolean recur) {
        long val = 1;
        for (int n : adj.get(v)) {
            if (n != p) {
                if (recur) dfs(n, v, true);
                val *= tot[n] + 1;
                val %= MOD;
            }
        }
        tot[v] = val;
        if (recur) {
            for (int j = 0; j < adj.get(v).size(); j++) {
                before.get(v).add(tot);
                tot *= tot[
                tot %= MOD;
            }
        }
    }
    static void dfs2(int v, int p) {
        sol[v] = tot[v];
        for (int n : adj.get(v)) {
            if (n != p) {
                trans(v, n);
                dfs2(n, v);
                trans(n, v);
            }
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
