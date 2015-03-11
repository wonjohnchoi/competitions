import java.util.*;
import java.io.*;
public class F {
    // http://codeforces.com/contest/490/submission/8819845
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static List<List<Integer>> adj;
    static int[] f, r;
    static int N;
    public static void main(String args[]) {
        N = in.nextInt();
        r = new int[N];
        for (int i = 0; i < N; i++) {
            r[i] = in.nextInt();
        }
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < N - 1; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        int max = 0;
        f = new int[N];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dfs(i, -1));
        }
        out.println(max);
    }
    static int dfs(int v, int p) {
        // out.println(v + " " + p + " " + val);
        int ll = 0, rl = N - 1;
        while (ll < rl) {
            int mid = (ll + rl) / 2;
            if (f[mid] > r[v]) {
                rl = mid;
            } else if (f[mid] < r[v]) {
                ll = mid + 1;
            } else {
                ll = rl = mid;
            }
        }
        int tmp = f[ll];
        f[ll] = r[v];
        int len = ll + 1;
        for (int w : adj.get(v)) {
            if (w != p) {
                // out.println(w + " " + r[w]);
                len = Math.max(len, dfs(w, v));
            }
        }
        f[ll] = tmp;
        return len;
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
