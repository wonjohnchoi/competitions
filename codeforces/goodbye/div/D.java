import java.util.*;
import java.io.*;
public class D {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static int N;
    static List<List<Edge>> adj;
    static class Edge {
        int to, cost;
        int cnt;
        boolean marked;
        Edge(int t, int c) {
            to = t;
            cost = c;
            cnt = 0;
            marked = false;
        }
    }
    public static void main(String args[]) {
        N = in.nextInt();
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Edge>());
        }
        Edge[][] edges = new Edge[N - 1][2];
        for (int i = 0; i < N - 1; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int d = in.nextInt();
            Edge e1 = new Edge(a, d);
            Edge e2 = new Edge(b, d);
            adj.get(a).add(e2); adj.get(b).add(e1);
            edges[i][0] = e1;
            edges[i][1] = e2;
        }
        for (int i = 0; i < N; i++) {
            for (Edge e : adj.get(i)) {
                if (!e.marked) {
                    out.println("started dfs from " + i + " to " + e.to);
                    dfs(e, i);
                }
            }
        }
        double tot = 0;
        for (int i = 0; i < N - 1; i++) {
            out.println(edges[i][0].cnt + " " + edges[i][1].cnt);
            tot += (double) edges[i][0].cnt * edges[i][1].cnt * edges[i][0].cost / N * 3;
        }
        out.println("initally.. " + tot);
        int M = in.nextInt();
        for (int i = 0; i < M; i++) {
            int r, w;
            r = in.nextInt() - 1;
            w = in.nextInt();
            int diff = edges[r][0].cost - w;
            edges[r][0].cost = w;
            edges[r][1].cost = w;
            tot -= (double) diff * edges[r][0].cnt * edges[r][1].cnt / N * 3;
            out.println(tot);
        }
    }
    static void dfs(Edge e, int p) {
        out.println("dfs: " + e.to + " " + p);
        e.marked = true;
        e.cnt = 1;
        int v = e.to;
        for (Edge e2 : adj.get(v)) {
            if (!e2.marked) {
                //dfs(e2, v);
            }
            if (e2.to != p  && !e2.marked) {
                dfs(e2, v);
                e.cnt += e2.cnt;
                out.println(e.cnt + " increased from " + e2.cnt);
            }
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
