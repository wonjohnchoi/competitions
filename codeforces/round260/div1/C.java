import java.util.*;
import java.io.*;
public class C {
    static int N, M, Q;
    static List<List<Integer>> adj;
    static void solve() {
        N = in.nextInt();
        M = in.nextInt();
        Q = in.nextInt();
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Integer>());
        }
        int[] dia = new int[N];
        Arrays.fill(dia, -1);
        Dsu dsu = new Dsu(N); dsu.clear();
        for (int i = 0; i < M; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            // con a b
            dsu.unite(a, b);
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        int[] dists = new int[N];
        for (int i = 0; i < N; i++) {
            int j = dsu.get(i);
            if (dia[j] == -1) {
                dists[j] = 0;
                int k = dfs(dists, -1, j);
                dists[k] = 0;
                int l = dfs(dists, -1, k);
                dia[j] = dists[l];
            }
        }
        for (int i = 0; i < Q; i++) {
            int t = in.nextInt();
            int x, y;
            if (t == 1) {
                x = dsu.get(in.nextInt() - 1);
                out.println(dia[x]);
            } else {
                x = dsu.get(in.nextInt() - 1);
                y = dsu.get(in.nextInt() - 1);
                if (x == y) continue;
                dsu.unite(x, y);
                int d = (int) (Math.ceil(dia[x] / 2.0) + Math.ceil(dia[y] / 2.0) + 1);
                dia[dsu.get(x)] = Math.max(Math.max(dia[x], dia[y]), d);
                // out.println(x + " " + y + " " + dia[dsu.get(x)]);
            }
        }
    }
    static int dfs(int[] dists, int par, int cur) {
        int max = cur;
        for (int next : adj.get(cur)) {
            // out.println("search "  + cur + " " + next);
            if (next != par) {
                dists[next] = dists[cur] + 1;
                int i = dfs(dists, cur, next);
                if (dists[max] < dists[i]) max = i;
            }
        }
        return max;
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
    static class Dsu {
        int[] p;
        Dsu(int n) {
            p = new int[n];
        }
        void clear() { // call clear before usage of Dsu
            for (int i = 0; i < p.length; i++) {
                p[i] = i;
            }
        }
        int get(int x) {
            return p[x] == x ? x : (p[x] = get(p[x]));
        }
        void unite(int x, int y) {
            x = get(x);
            y = get(y);
            if (x == y) {
                return;
            }
            p[x] = y;
        }
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
