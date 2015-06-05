import java.util.*;
import java.io.*;
public class E {
    static void solve() {
        int N = in.nextInt();
        int M = in.nextInt();
        int SOURCE = N;
        int SINK = N + 1;
        int[][] c = new int[N + 2][N + 2];
        int[][] f = new int[N + 2][N + 2];
        int[] a = new int[N];
        int[] b = new int[N];
        int[] diff = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < N; i++) {
            b[i] = in.nextInt();
            diff[i] = b[i] - a[i];
        }
        int INF = 100000;
        for (int i = 0; i < M; i++) {
            int v = in.nextInt() - 1;
            int w = in.nextInt() - 1;
            c[v][w] = c[w][v] = INF;
        }
        for (int i = 0; i < N; i++) {
            if (diff[i] > 0) {
                c[i][SINK] = diff[i];
            } else {
                c[SOURCE][i] = -diff[i];
            }
        }
        while (dfs(c, f, SOURCE, new boolean[N + 2], SINK));
        for (int i = 0; i < N; i++) {
            if (c[i][SINK] != f[i][SINK] || c[SOURCE][i] != f[SOURCE][i]) {
                out.println("NO");
                return;
            }
        }
        int[][] g = new int[N][N];
        for (int i = 0; i < N; i++) {
            g[i][i] = a[i];
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    if (f[i][j] > 0) {
                        g[i][i] -= f[i][j];
                        g[i][j] = f[i][j];
                    }
                }
            }
            g[i][i] = Math.max(g[i][i], 0);
        }
        out.println("YES");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j != 0) {
                    out.print(" ");
                }
                out.print(g[i][j]);
            }
            out.println();
        }
    }
    static boolean dfs(int[][] c, int[][] f, int cur, boolean[] marked, int SINK) {
        if (cur == SINK) return true;
        if (marked[cur]) return false;
        marked[cur] = true;
        for (int next = 0; next < c.length; next++) {
            if (next != cur && c[cur][next] > f[cur][next] && dfs(c, f, next, marked, SINK)) {
                f[cur][next]++;
                f[next][cur]--;
                return true;
            }
        }
        return false;
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
