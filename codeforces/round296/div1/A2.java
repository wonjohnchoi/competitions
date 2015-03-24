import java.util.*;
import java.io.*;
public class A2 {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    static class Node implements Comparable<Node> {
        int size, i;
        Node(int size, int i) {
            this.size = size;
            this.i = i;
        }
        @Override
            public int compareTo(Node n) {
            int r = size - n.size;
            if (r == 0) r = i - n.i;
            return r;
        }
    }
    public static void main(String args[]) {
        int W, H, N;
        W = in.nextInt();
        H = in.nextInt();
        N = in.nextInt();
        TreeSet<Node> sh = new TreeSet<Node>();
        sh.add(new Node(H, 0));
        TreeSet<Node> sv = new TreeSet<Node>();
        sv.add(new Node(W, 0));
        TreeSet<Integer> th = new TreeSet<>();
        th.add(0); th.add(H);
        TreeSet<Integer> tv = new TreeSet<>();
        tv.add(0); tv.add(W);
        for (int i = 0; i < N; i++) {
            String s = in.next();
            int p = in.nextInt();
            TreeSet<Node> sc;
            TreeSet<Integer> tc;
            if (s.equals("H")) {
                sc = sh; tc = th;
            } else {
                sc = sv; tc = tv;
            }
            int p2 = tc.higher(p);
            int p1 = tc.lower(p);
            tc.add(p);
            sc.remove(new Node(p2 - p1, p1));
            sc.add(new Node(p - p1, p1));
            sc.add(new Node(p2 - p, p));
            out.println((long) sv.last().size * sh.last().size);
        }
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
