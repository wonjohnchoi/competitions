import java.util.*;
import java.io.*;
public class D {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static long MOD = 1000000007;
    static int D, N;
    static int[] a;
    static boolean[] marked;
    static List<List<Integer>> adj;
    public static void main(String args[]) {
        D = in.nextInt();
        N = in.nextInt();
        a = new int[N];
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < N - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        long tot = 0;
        marked = new boolean[N];
        for (int i = 0; i < N; i++) {
            tot += f(i, i);
            tot %= MOD;
        }
        out.println(tot);
    }
    static long f(int v, int root) {
        long ret = 1;
        marked[v] = true;
        for (int u : adj.get(v)) {
            if (!marked[u]) {
                // out.println(u + " " + root);
                if (a[u] > a[root] + D || a[u] < a[root]) continue;
                if (a[u] == a[root] && root < u) continue;
                ret *= 1 + f(u, root);
                ret %= MOD;
            }
        }
        marked[v] = false;
        // out.println(v + " " + root + " " + ret);
        return ret;
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
