import java.util.*;
import java.io.*;
public class B2 {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N, S, L;
        N = in.nextInt();
        S = in.nextInt();
        L = in.nextInt();
        int[] a = new int[N];
        SegmentTree min = new SegmentTree(N);
        SegmentTree max = new SegmentTree(N);
        SegmentTree dpTree = new SegmentTree(N + 1);
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
            min.put(i, a[i]);
            max.put(i, -a[i]);
        }
        int r = N - 1;
        dpTree.put(N, 0);
        // out.println(dpTree.get(7, 7));
        for (int i = N - 1; i >= 0; i--) {
            // check i ~ i + L - 1 is good
            while (true) {
                // out.println(i + " " + (i + L - 1) + " " + Arrays.toString(minMax));
                if (-max.get(i, r) - min.get(i, r) > S) {
                    r--;
                } else {
                    break;
                }
            }
            // out.println(i + " " + r);
            if (r - i + 1 >= L) { // i ~ r. i ~ r - L + 1. i + L ~ r + 1
                dpTree.put(i, dpTree.get(i + L, r + 1) + 1);
            }
        }
        int ret = dpTree.get(0, 0);
        if (ret >= SegmentTree.EMPTY) out.println(-1);
        else out.println(ret);
    }
    static class SegmentTree {
        static int EMPTY = (int) 1e9 + 7; // MINIMUM RANGE QUERY so use MAX_VALUE for garbage
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
            Arrays.fill(ans, EMPTY);
        }
        int unite(int val1, int val2) {
            return Math.min(val1, val2);
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
            if (needL > r || needR < l) {
                return EMPTY;
            }
            if (needL <= l && needR >= r) {
                return ans[v];
            }
            int mid = (l + r) / 2;
            return unite(get(v * 2 + 1, l, mid, needL, needR),
                         get(v * 2 + 2, mid + 1, r, needL, needR));
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
