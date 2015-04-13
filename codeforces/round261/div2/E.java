import java.util.*;
import java.io.*;
public class E {
    static int N, M;
    static int[] from, to, cost, md;
    static List<Integer>[] adjE;
    static void solve() {
        N = in.nextInt();
        M = in.nextInt();
        from = new int[M];
        to = new int[M];
        cost = new int[M];
        md = new int[M];
        Arrays.fill(md, -1);
        adjE = new List[N];
        for (int i = 0; i < N; i++) {
            adjE[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.nextInt();
            from[i] = a;
            to[i] = b;
            cost[i] = c;
            adjE[a].add(i);
        }
        int best = 0;
        for (int i = 0; i < M; i++) {
            if (md[i] == -1) dfs(i);
            best = Math.max(best, md[i]);
        }
        out.println(best);
    }
    static void dfs(int cur) {
        int v = to[cur];
        int best = 1;
        for (int e : adjE[v]) {
            if (cost[e] > cost[cur]) {
                if (md[e] == -1) dfs(e);
                best = Math.max(best, md[e] + 1);
            }
        }
        md[cur] = best;
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
    public int nextInt() {
	return Integer.parseInt(next());
    }
}
