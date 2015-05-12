import java.util.*;
import java.io.*;
public class B {
    static List<List<Integer>> adj;
    static void solve() {
        N = in.nextInt();
        M = in.nextInt();
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < M; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        int[][] stl = new int[2][3];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                stl[i][j] = in.nextInt();
                if (j != 2) stl[i][j]--;
            }
        }
        dists = new int[N][N];
        for (int i = 0; i < N; i++) {
            bfs(i);
        }
        int best = -1;
        for (int k = 0; k < 2; k++) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int a = dists[stl[0][k]][i];
                int b = dists[i][j];
                int c = dists[j][stl[0][(k + 1) % 2]];
                int d = dists[stl[1][0]][i];
                int e = dists[j][stl[1][1]];
                int l1 = stl[0][2];
                int l2 = stl[1][2];
                if (a + b + c <= l1 && d + b + e <= l2) {
                    int cur = M - a - b - c - d - e;
                    best = Math.max(best, cur);
                }
            }
        }
        int a = dists[stl[0][k]][stl[0][(k + 1) % 2]];
        int b = dists[stl[1][0]][stl[1][1]];
        int l1 = stl[0][2];
        int l2 = stl[1][2];
        if (a <= l1 && b <= l2) {
            best = Math.max(best, M - a - b);
        }
        }
        out.println(best);
    }
    static int N, M;
    static int[][] dists;
    static void bfs(int v) {
        boolean[] marked = new boolean[N];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(v);
        marked[v] = true;
        while (!queue.isEmpty()) {
            int c = queue.poll();
            for (int n : adj.get(c)) {
                if (!marked[n]) {
                    queue.add(n);
                    dists[v][n] = dists[v][c] + 1;
                    marked[n] = true;
                }
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
