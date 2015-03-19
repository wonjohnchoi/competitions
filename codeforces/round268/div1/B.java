import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static class Node implements Comparable<Node> {
        int res;
        Node(int pp) {
            p = pp;
        }
        int p;
        @Override
        public int compareTo(Node n) {
            return Integer.compare(p, n.p);
        }
        @Override
        public boolean equals(Object o) {
            return p == ((Node) o).p;
        }
    }
    public static void main(String args[]) {
        int N, a, b;
        N = in.nextInt();
        int[] label;
        a = in.nextInt();
        b = in.nextInt();
        if (a < b) {
            label = new int[] {0, 1};
        } else {
            int tmp = a;
            a = b;
            b = tmp;
            label = new int[] {1, 0};
        }
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(in.nextInt());
        }
        TreeSet<Node> ts = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            ts.add(nodes[i]);
        }
        while (!ts.isEmpty()) {
            Node n = ts.first();
            Node an = new Node(a - n.p);
            Node bn = new Node(b - n.p);
            SortedSet<Node> ats = ts.tailSet(an);
            SortedSet<Node> bts = ts.tailSet(bn);
            Node an2 = ats.isEmpty() ? null : ats.first();
            Node bn2 = bts.isEmpty() ? null : bts.first();
            Node n2 = null;
            int l = 0;
            if (bn2 != null && bn2.equals(bn)) {
                n2 = bn2;
                l = label[1];
            } else if (an2 != null && an2.equals(an)) {
                n2 = an2;
                l = label[0];
            } else {
                NO();
            }
            if (!n.equals(n2)) {
                ts.remove(n);
                n.res = l;
            }
            ts.remove(n2);
            n2.res = l;
        }
        out.println("YES");
        for (int i = 0; i < nodes.length; i++) {
            if (i != 0) out.print(" ");
            out.print(nodes[i].res);
        }
        out.println();
    }
    static void NO() {
        out.println("NO");
        System.exit(0);
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
