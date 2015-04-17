import java.util.*;
import java.io.*;
public class B {
    static class E {
        int v, u;
        long c;
        boolean train;
        E(int vv, int uu, long cc, boolean tt) {
            v = vv;
            u = uu;
            c = cc;
            train = tt;
        }
        int other(int vv) {
            return v == vv ? u : v;
        }
    }
    static class Node implements Comparable<Node> {
        List<Integer> to = new ArrayList<Integer>();
        long dist = (long) 1e18;
        boolean marked = false;
        int v;
        Node(int vv) {
            v = vv;
        }
        public int compareTo(Node n) {
            return Long.compare(dist, n.dist);
        }
        boolean train = false;
    }
    static Node[] nodes;
    static int N, M, K;
    static E[] edges;
    static void solve() {
        N = in.nextInt();
        M = in.nextInt();
        K = in.nextInt();
        edges = new E[M + K];
        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < M; i++) {
            edges[i] = new E(in.nextInt() - 1, in.nextInt() - 1, in.nextLong(), false);
            nodes[edges[i].v].to.add(i);
            nodes[edges[i].u].to.add(i);
        }
        for (int i = M; i < M + K; i++) {
            edges[i] = new E(0, in.nextInt() - 1, in.nextLong(), true);
            nodes[edges[i].v].to.add(i);
            nodes[edges[i].u].to.add(i);
        }
        nodes[0].dist = 0;
        nodes[0].marked = true;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(nodes[0]);
        long need = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (!cur.marked) continue;
            cur.marked = false;
            for (int tto : cur.to) {
                E e = edges[tto];
                Node next = nodes[e.other(cur.v)];
                long ndist = cur.dist + e.c;
                if (next.dist > ndist) {
                    next.marked = true;
                    next.dist = ndist;
                    pq.add(next);
                    next.train = e.train;
                } else if (next.dist == ndist) {
                    if (!e.train) next.train = false;
                }
            }
        }
        int tot = 0;
        for (int i = 1; i < N; i++) {
            if (nodes[i].train) {
                tot++;
            }
        }
        out.println(K - tot);
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
