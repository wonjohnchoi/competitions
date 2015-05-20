import java.util.*;
import java.io.*;
public class E {
    static class SegmentTree {
        static int EMPTY = Integer.MIN_VALUE; // MINIMUM RANGE QUERY so use MAX_VALUE for garbage
        int[] ans;
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
            Arrays.fill(ans, EMPTY);
        }
        int unite(int val1, int val2) {
            return Math.max(val1, val2);
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
        // NEW. to use this lazy st, need to modify above get in a way that mixes ans[v] and unite(...).
        void put(int v, int l, int r, int needL, int needR, int val) {
            if (needL > r || needR < l) return;
            if (needL <= l && r <= needR) {
                ans[v] = Math.max(ans[v], val);
                return;
            }
            int mid = (l + r) / 2;
            put(v * 2 + 1, l, mid, needL, needR, val);
            put(v * 2 + 2, mid + 1, r, needL, needR, val);
        }
        void put(int v, int l, int r, int need, int val) { // O(log N)
            if (l == r) {
                ans[v] = val;
                return;
            }
            int m = (l + r) / 2;
            if (need <= m)
                put(2 * v + 1, l, m, need, val);
            else
                put(2 * v + 2, m + 1, r, need, val);
            ans[v] = unite(ans[2 * v + 1], ans[2 * v + 2]);
        }
        void put(int pos, int val) {
            put(0, 0, size - 1, pos, val);
        }
    }
    static int hi(long[] hs, int hsI, long h, boolean less) {
        int i = Arrays.binarySearch(hs, 0, hsI, h);
        if (i < 0) {
            i = -(i + 1);
            if (less) i--;
        }
        // out.println(h + " " + i + " " + less);
        return i;
    }
    static void solve() {
        int N = in.nextInt();
        long D = in.nextLong();
        long[] h = new long[N];
        long[] hs = new long[N];
        for (int i = 0; i < N; i++) {
            h[i] = in.nextLong();
            hs[i] = h[i];
        }
        int hsI = 0;
        Arrays.sort(hs);
        for (int i = 0; i < N; i++) {
            if (hsI == 0 || hs[i] != hs[hsI - 1]) {
                hs[hsI++] = hs[i];
            }
        }
        //out.println("hs: " + Arrays.toString(hs) + " " + hsI);
        int[] f = new int[N];
        SegmentTree st = new SegmentTree(N);
        int maxI = -1;
        for (int i = N - 1; i >= 0; i--) {
            f[i] = 1;
            int hi = Arrays.binarySearch(hs, 0, hsI, h[i] - D);
            int low = hi(hs, hsI, h[i] - D, true);
            int high = hi(hs, hsI, h[i] + D, false);
            int cur = hi(hs, hsI, h[i], true);
            //out.println(low + " " + high + " " + cur + " " + h[i]);
            // out.println("st.get(0, 0, N - 1, 0, low): " + st.get(0, 0, N - 1, 0, low));
            //out.println("st.get(0, 0, N - 1, high, N - 1): " + st.get(0, 0, N - 1, high, N - 1));
            if (low >= 0)
                f[i] = Math.max(f[i], st.get(0, 0, N - 1, 0, low) + 1);
            if (high <= N - 1)
                f[i] = Math.max(f[i], st.get(0, 0, N - 1, high, N - 1) + 1);
            if (maxI == -1 || f[maxI] < f[i]) {
                maxI = i;
            }
            //out.println(h[i] + " " + f[i]);
            st.put(0, 0, N - 1, cur, f[i]);
        }
        out.println(f[maxI]);
        out.print(maxI + 1);
        outer : while (true) {
            for (int i = maxI + 1; i < N; i++) {
                if (f[i] == f[maxI] - 1 && Math.abs(h[i] - h[maxI]) >= D) {
                    maxI = i;
                    out.print(" " + (maxI + 1));
                    continue outer;
                }
            }
            break;
        }
        out.println();
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
