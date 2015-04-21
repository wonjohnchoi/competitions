import java.util.*;
import java.io.*;
public class C {
    static void solve() {
        int N, M;
        N = in.nextInt();
        M = in.nextInt();
        SegmentTree st = new SegmentTree(N);
        for (int i = 0; i < M; i++) {
            int t = in.nextInt();
            int l, r, x;
            if (t == 1) {
                l = in.nextInt() - 1;
                r = in.nextInt() - 1;
                x = in.nextInt();
                st.put(0, 0, N - 1, l, r, x);
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
    static long EMPTY = 0; // MINIMUM RANGE QUERY so use MAX_VALUE for garbage
    long[] ans;
    long[] vals, vals2;
    int size;
    SegmentTree(long[] vals) {
        this.vals = vals;
        this.size = vals.length;
        ans = new long[size * 4 + 10];
        build(0, 0, size - 1);
    }
    SegmentTree(int size) {
        this.vals = null;
        this.size = size;
        //d(size + " " + (size * 4 + 10));
        ans = new long[size * 4 + 10];
        vals = new long[size * 4 + 10];
        vals2 = new long[size * 4 + 10];
        Arrays.fill(vals, -1);
        Arrays.fill(vals2, -1);
        vals[0] = 1;
        vals2[0] = size;
        Arrays.fill(ans, -1);
    }
    long unite(long val1, long val2) {
        return val1 + val2;
    }
    void build(int v, int l, int r) { // O(N)
        if (l == r) {
            ans[v] = vals[l];
        } else {
            int m = (l + r) / 2;
            build(v * 2 + 1, l, m);
            build(v * 2 + 2, m + 1, r);
            ans[v] = unite(ans[v * 2 + 1], ans[v * 2 + 2]);
        }
    }
    long get(int needL, int needR) {
        return get(0, 0, size - 1, needL, needR);
    }
    long get(int v, int l, int r, int needL, int needR) { // O(log N)
        if (needL > r || needR < l) {
            return EMPTY;
        }
        if (needL <= l && needR >= r && vals[v] != -2) {
            out.println(needL + " " + needR + " " + ans[v]);
            return ans[v];
        }
        int mid = (l + r) / 2;
        return ans[v] + unite(get(v * 2 + 1, l, mid, needL, needR),
                     get(v * 2 + 2, mid + 1, r, needL, needR));
    }
    // NEW. to use this lazy st, need to modify above get in a way that mixes ans[v] and unite(...).
    void put(int v, int l, int r, int needL, int needR, int val) {
        d(v + " " + l + " " + r + " " + needL + " " + needR + " " + val + " " + vals[v]);
        if (needL > r || needR < l) return;
        if (needL <= l && needR >= r && vals[v] != -2) {
            if (ans[v] == -1) ans[v] = 0;
            if (vals[v] == vals2[v]) {
                ans[v] += Math.abs(vals[v] - val) * (r - l + 1);
            } else {
                if (val <= vals[v]) {
                    long a = vals[v] - val;
                    long b = vals2[v] - val;
                    ans[v] += (a + b) * (b - a + 1)  / 2;
                } else if (val >= vals2[v]) {
                    long a = val - vals2[v];
                    long b = val - vals[v];
                    ans[v] += (a + b) * (b - a + 1)  / 2;
                } else {
                    long a = val - vals[v];
                    long b = vals2[v] - val;
                    ans[v] += a * (a + 1) / 2 + b * (b + 1) / 2;
                }
            }
            vals[v] = val;
            vals2[v] = val;
            out.println(needL + " " + needR + " " + ans[v]);
            return;
        }
        int mid = (l + r) / 2;
        vals[v * 2 + 1] = vals[v];
        vals2[v * 2 + 1] = vals[v] + (vals2[v] - vals[v]) * (mid - l) / (r - l);
        put(v * 2 + 1, l, mid, needL, needR, val);
        vals[v * 2 + 2] = vals[v] + (vals2[v] - vals[v]) * (mid + 1 - l) / (r - l);
        vals2[v * 2 + 2] = vals2[v];
        put(v * 2 + 2, mid + 1, r, needL, needR, val);
        vals[v] = -2;
    }
    void put(int i, int tl, int tr, int pos, int val) { // O(log N)
	if (tl == tr) {
	    ans[i] = val;
	    return;
	}
	int m = (tl + tr) / 2;
	if (pos <= m)
	    put(2 * i + 1, tl, m, pos, val);
	else
	    put(2 * i + 2, m + 1, tr, pos, val);
	ans[i] = unite(ans[2 * i + 1], ans[2 * i + 2]);
    }
    void put(int pos, int val) {
	put(0, 0, size - 1, pos, val);
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
