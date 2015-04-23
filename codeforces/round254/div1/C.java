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
        SegmentTree st = new SegmentTree(N);
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
static class SegmentTree {
    long[] add, sum;
    int size;
    SegmentTree(int size) {
        this.size = size;
        add = new long[size * 4 + 10];
        sum = new long[size * 4 + 10];
    }
    long get(int needL, int needR) {
        return get(0, 0, size - 1, needL, needR);
    }
    long get(int v, int l, int r, int needL, int needR) { // O(log N)
        //out.println("get: " + l + " " + r + " " + needL + " " + needR);
        if (needL > r || needR < l) {
            return 0;
        }
        if (needL <= l && needR >= r) {
            return getSum(v, l, r);
        }
        add[v * 2 + 1] += add[v];
        add[v * 2] += add[v];
        add[v] = 0;
        int mid = (l + r) / 2;
        long ret = get(v * 2 + 1, l, mid, needL, needR)
            + get(v * 2 + 2, mid + 1, r, needL, needR);
        sum[v] = getSum(v * 2 + 1, l, mid) + getSum(v * 2 + 2, mid + 1, r);
        return ret;
    }
    void put(int needL, int needR, int val) {
        put(0, 0, size - 1, needL, needR, val);
    }
    long getSum(int v, int l, int r) {
        return sum[v] + (r - l + 1) * add[v];
    }
    long put(int v, int l, int r, int needL, int needR, int val) {
        // out.println("put: " + l + " " + r + " " + needL + " " + needR + " " + val);
        if (needL > r || needR < l) return 0;
        if (needL <= l && needR >= r) {
            add[v] += val;
            return getSum(v, l, r);
        }
        add[v * 2 + 1] += add[v];
        add[v * 2] += add[v];
        add[v] = 0;
        int mid = (l + r) / 2;
        long ret = put(v * 2 + 1, l, mid, needL, needR, val)
            + put(v * 2 + 2, mid + 1, r, needL, needR, val);
        sum[v] = getSum(v * 2 + 1, l, mid) + getSum(v * 2 + 2, mid + 1, r);
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
