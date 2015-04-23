import java.util.*;
import java.io.*;
public class C {
    static class Line implements Comparable<Line> {
        int l, r, c;
        Line(int ll, int rr, int cc) {
            l = ll;
            r = rr;
            c = cc;
        }
        public int compareTo(Line line) {
            return l - line.l;
        }
    }
    static void solve() {
        int N, M;
        N = in.nextInt();
        M = in.nextInt();
        TreeSet<Line> lines = new TreeSet<>();
        for (int i = 1; i <= N; i++) {
            lines.add(new Line(i - 1, i - 1, i));
        }
        Line seeker = new Line(-1, -1, -1);
        Node st = new Node(N);
        List<Line> rem = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int t = in.nextInt();
            int l, r, x;
            if (t == 1) {
                l = in.nextInt() - 1;
                r = in.nextInt() - 1;
                x = in.nextInt();
                seeker.l = l;
                Line left = lines.floor(seeker);
                seeker.l = r;
                Line right = lines.floor(seeker);
                rem.clear();
                for (Line line : lines.subSet(left, true, right, true)) {
                    rem.add(line);
                }
                for (Line line : rem) {
                    lines.remove(line);
                    // out.println(line.l + " " + line.r + " " + line.c);
                    st.put(Math.max(l, line.l), Math.min(r, line.r), Math.abs(line.c - x));
                }
                if (left.l <= l - 1) {
                    lines.add(new Line(left.l, l - 1, left.c));
                }
                if (r + 1 <= right.r) {
                    lines.add(new Line(r + 1, right.r, right.c));
                }
                lines.add(new Line(l, r, x));
            } else {
                l = in.nextInt() - 1;
                r = in.nextInt() - 1;
                out.println(st.get(l, r));
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
static class Node {
    Node left, right;
    long lazyVal = 0;
    long sum = 0;
    int l, r;
    public Node(int size) {
        this(0, size - 1);
    }
    public Node(int l, int r) {
	this.l = l;
	this.r = r;
	if (r > l) {
	    int mid = (l + r) >> 1;
	    left = new Node(l, mid);
	    right = new Node(mid + 1, r);
	}
    }
    // lazy put
    void put(int ql, int qr, int qval) {
	if (l > qr || ql > r) return;
	if (ql <= l && r <= qr) {
            lazyVal += qval;
	    return;
	}
        left.lazyVal += lazyVal;
        right.lazyVal += lazyVal;
        lazyVal = 0;
        left.put(ql, qr, qval);
        right.put(ql, qr, qval);
        sum = left.getSum() + right.getSum();
    }
    long getSum() {
        return lazyVal * (r - l + 1) + sum;
    }
    long get(int ql, int qr) {
	if (l > qr || ql > r) {
            return 0;
	}
	if (ql <= l && r <= qr) {
	    return getSum();
	}
        left.lazyVal += lazyVal;
        right.lazyVal += lazyVal;
        lazyVal = 0;
        long ret = 0;
        ret = left.get(ql, qr)
            + right.get(ql, qr);
        sum = left.getSum() + right.getSum();
        return ret;
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
