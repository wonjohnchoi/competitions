import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N, M;
        N = in.nextInt();
        M = in.nextInt();
        SegmentTree st = new SegmentTree(N);
        int[] l, r, q;
        l = new int[M];
        r = new int[M];
        q = new int[M];
        for (int i = 0; i < M; i++) {
            l[i] = in.nextInt() - 1;
            r[i] = in.nextInt() - 1;
            q[i] = in.nextInt();
            st.put(0, 0, N - 1, l[i], r[i], q[i]);
        }
        for (int i = 0; i < M; i++) {
            if (st.get(0, 0, N - 1, l[i], r[i]) != q[i]) {
                out.println("NO");
                System.exit(0);
            }
        }
        out.println("YES");
        for (int i = 0; i < N; i++) {
            if (i != 0) out.print(" ");
            out.print(st.get(i, i));
        }
        out.println();
    }
    static class SegmentTree {
        static int EMPTY = 0; // MINIMUM RANGE QUERY so use MAX_VALUE for garbage
        int[] ans;
        int[] vals;
        int size;
        SegmentTree(int[] vals) {
            this.vals = vals;
            this.size = vals.length - 1;
            ans = new int[size * 4 + 10];
            build(0, 0, size - 1);
        }
        SegmentTree(int size) {
            this.vals = null;
            this.size = size;
            ans = new int[size * 4 + 10];
            Arrays.fill(ans, 0);
        }
        int unite(int val1, int val2) {
            return val1 & val2;
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
        int get(int needL, int needR) {
            return get(0, 0, size - 1, needL, needR);
        }
        int get(int v, int l, int r, int needL, int needR) { // O(log N)
            // out.println("get: " + l + " " + r + " " + needL + " " + needR);
            if (needL > r || needR < l) {
                return Integer.MAX_VALUE;
            }
            if (needL <= l && needR >= r) {
                return ans[v];
            }
            int mid = (l + r) / 2;
            return ans[v] | unite(get(v * 2 + 1, l, mid, needL, needR),
                         get(v * 2 + 2, mid + 1, r, needL, needR));
        }
        // NEW
        void put(int v, int l, int r, int needL, int needR, int val) {
            // out.println("put: " + l + " " + r + " " + needL + " " + needR + " " + val);
            if (needL > r || needR < l) return;
            if (needL <= l && needR >= r) {
                ans[v] |= val;
                return;
            }/*
            if (l == r) {
                ans[v] |= val;
                return;
                }*/
            int mid = (l + r) / 2;
            put(v * 2 + 1, l, mid, needL, needR, val);
            put(v * 2 + 2, mid + 1, r, needL, needR, val);
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
