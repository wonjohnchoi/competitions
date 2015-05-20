import java.util.*;
import java.io.*;
public class F {
    static void solve() {
        int N = in.nextInt();
        SegmentTree st = new SegmentTree(N);
        for (int i = 0; i < N; i++) {
            st.put(i, in.nextInt());
        }
        int M = in.nextInt();
        for (int i = 0; i < M; i++) {
            int a, b;
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;
            int[] val = st.get(a, b);
            if (val[0] == val[2]) {
                out.println(b - a + 1 - val[1]);
            } else {
                out.println(b - a + 1);
            }
            // out.println(val[0] + " " + val[1] + " " + val[2]);
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
    static int EMPTY = Integer.MAX_VALUE; // MINIMUM RANGE QUERY so use MAX_VALUE for garbage
    int[] ans;
    int[] cnt;
    int[] gcd;
    int[] vals;
    int size;
    SegmentTree(int[] vals) {
        this.vals = vals;
        this.size = vals.length;
        ans = new int[size * 4 + 10];
        build(0, 0, size - 1);
    }
    SegmentTree(int size) {
        this.vals = null;
        this.size = size;
        ans = new int[size * 4 + 10];
        cnt = new int[size * 4 + 10];
        gcd = new int[size * 4 + 10];
        Arrays.fill(ans, EMPTY);
    }
    int[] unite(int[] val1, int[] val2) {
        int ans = Math.min(val1[0], val2[0]);
        int cnt = 0;
        if (ans == val1[0]) cnt += val1[1];
        if (ans == val2[0]) cnt += val2[1];
        return new int[] {ans, cnt, gcd(val1[2], val2[2])};
    }
    static int gcd(int a, int b) {
        if (a < b) return gcd(b, a);
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    void build(int v, int l, int r) { // O(N)
        if (l == r) {
            ans[v] = vals[l];
        } else {
            int m = (l + r) / 2;
            build(v * 2 + 1, l, m);
            build(v * 2 + 2, m + 1, r);
            int[] val = unite(new int[] {
                    ans[v * 2 + 1], cnt[v * 2 + 1], gcd[v * 2 + 1]},
                new int[] {
                    ans[v * 2 + 2], cnt[v * 2 + 2], gcd[v * 2 + 1]});
            ans[v] = val[0];
            cnt[v] = val[1];
            gcd[v] = val[2];
        }
    }
    int[] get(int needL, int needR) {
        return get(0, 0, size - 1, needL, needR);
    }
    int[] get(int v, int l, int r, int needL, int needR) { // O(log N)
        if (needL > r || needR < l) {
            return new int[] {EMPTY, 0, 0};
        }
        if (needL <= l && needR >= r) {
            return new int[] {ans[v], cnt[v], gcd[v]};
        }
        int mid = (l + r) / 2;
        return unite(get(v * 2 + 1, l, mid, needL, needR),
                     get(v * 2 + 2, mid + 1, r, needL, needR));
    }
    void put(int v, int l, int r, int need, int val) { // O(log N)
	if (l == r) {
	    ans[v] = val;
            cnt[v] = 1;
            gcd[v] = val;
	    return;
	}
	int m = (l + r) / 2;
	if (need <= m)
	    put(2 * v + 1, l, m, need, val);
	else
	    put(2 * v + 2, m + 1, r, need, val);
        int[] val2= unite(new int[] {
                ans[v * 2 + 1], cnt[v * 2 + 1], gcd[v * 2 + 1]},
            new int[] {
                ans[v * 2 + 2], cnt[v * 2 + 2], gcd[v * 2 + 2]});
        ans[v] = val2[0];
        cnt[v] = val2[1];
        gcd[v] = val2[2];
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
