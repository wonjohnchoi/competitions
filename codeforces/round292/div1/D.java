import java.util.*;
import java.io.*;
public class D {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static ArrayList<Edge>[] edges;
    // copied from http://codeforces.com/contest/516/submission/9898972
    static class Edge {
        int to;
        long cost;
        public Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    static void fillDists(int v, int parent, long dist, long[] dists) {
        dists[v] = dist;
        for (Edge e : edges[v]) {
            if (e.to != parent) {
                fillDists(e.to, v, dist + e.cost, dists);
            }
        }
    }
    static class Node implements Comparable<Node> {
        long cost;
        int v;
        public Node(long cost, int v) {
            this.cost = cost;
            this.v = v;
        }
        @Override
        public int compareTo(Node n) {
            return Long.compare(n.cost, cost);
        }
    }
    static class Dsu {
        int[] p;
        int[] val;
        Dsu(int n) {
            p = new int[n];
            val = new int[n];
        }
        void clear() { // call clear before usage of Dsu
            for (int i = 0; i < p.length; i++) {
                p[i] = i;
                val[i] = 0;
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
            val[y] += val[x];
        }
    }
    public static void main(String args[]) {
        int N = in.nextInt();
        edges = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            int v, w;
            long d;
            v = in.nextInt() - 1;
            w = in.nextInt() - 1;
            d = in.nextLong();
            edges[v].add(new Edge(w, d));
            edges[w].add(new Edge(v, d));
        }
        int root1 = 0; long[] dists = new long[N];
        fillDists(root1, -1, 0, dists);
        int root2 = 0; long[] dists2 = new long[N];
        for (int i = 0; i < N; i++) {
            if (dists[i] > dists[root2]) root2 = i;
        }
        fillDists(root2, -1, 0, dists2);
        int root3 = 0; long[] dists3 = new long[N];
        for (int i = 0; i < N; i++) {
            if (dists2[i] > dists2[root3]) root3 = i;
        }
        fillDists(root3, -1, 0, dists3);
        long[] maxDists = new long[N];
        for (int i = 0; i < N; i++) {
            maxDists[i] = Math.max(dists2[i], dists3[i]);
        }
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(maxDists[i], i);
            // out.println(nodes[i].cost + " " + i);
        }
        Arrays.sort(nodes);
        for (int i = 0; i < N; i++) {
            // out.println(nodes[i].cost + " " + nodes[i].v);
        }
        Dsu dsu = new Dsu(N);
        int Q = in.nextInt();
        for (int j = 0; j < Q; j++) {
            long L = in.nextLong();
            dsu.clear();
            int it = 0;
            boolean[] marked = new boolean[N];
            long best = 0;
            for (int i = 0; i < N; i++) {
                if (i != 0) dsu.val[dsu.get(nodes[i - 1].v)]--;
                long minDist = nodes[i].cost - L;
                // out.println(nodes[i].v + " " + minDist);
                while (it < N && nodes[it].cost >= minDist) {
                    // out.println(it + " " + nodes[it].cost + " " + minDist);
                    int x = nodes[it].v;
                    dsu.val[dsu.get(x)]++;
                    marked[x] = true;
                    // dsu.unite(x, nodes[i].v);
                    for (Edge edge : edges[x]) {
                        // out.println(edge.to + " " + nodes[edge.to].cost + " " + minDist);
                        if (marked[edge.to]) {
                            // out.println("CONNECT: " + x +  " - " + edge.to);
                            dsu.unite(x, edge.to);
                        }
                    }
                    it++;
                    // out.println("CUR: " + dsu.val[dsu.get(x)]);
                    best = Math.max(best, dsu.val[dsu.get(x)]);
                }
            }
            out.println(best);
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
