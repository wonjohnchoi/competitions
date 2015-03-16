import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N, S, L;
        N = in.nextInt();
        S = in.nextInt();
        L = in.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        SegmentTree st = new SegmentTree(a);
        int[] dp = new int[N + 1];
        dp[N] = 0;
        for (int i = N - 1; i >= 0; i--) {
            // check i ~ i + L - 1 is good
            dp[i] = Integer.MAX_VALUE;
            if (N - 1 - i + 1 < L) continue;
            int[] minMax = st.get(i, i + L - 1);
            // out.println(i + " " + (i + L - 1) + " " + Arrays.toString(minMax));
            if (minMax[1] - minMax[0] > S) continue;
            if (N - 1 - i + 1 == L) {
                dp[i] = 1;
                continue;
            }
            for (int j = i + L - 1; j < N; j++) {
                minMax[0] = Math.min(minMax[0], a[j]);
                minMax[1] = Math.max(minMax[1], a[j]);
                if (minMax[1] - minMax[0] <= S) {
                    if (dp[j + 1] != Integer.MAX_VALUE)
                        dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                } else {
                    break;
                }
            }
        }
        if (dp[0] == Integer.MAX_VALUE) out.println(-1);
        else out.println(dp[0]);
    }
    static class SegmentTree {
        static int[] EMPTY = new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE}; // MINIMUM RANGE QUERY so use MAX_VALUE for garbage
        int[][] ans;
        int[] vals;
        int N;
        SegmentTree(int[] vals) {
            this.vals = vals;
            N = vals.length;
            ans = new int[N * 4][2];
            build(0, 0, N - 1);
        }
        int[] unite(int[] val1, int[] val2) {
            return new int[] {Math.min(val1[0], val2[0]), Math.max(val1[1], val2[1])};
        }
        void build(int v, int l, int r) { // O(N)
            if (l == r) {
                ans[v] = new int[] {vals[l], vals[l]};
            } else {
                int m = (l + r) / 2;
                build(v * 2 + 1, l, m);
                build(v * 2 + 2, m + 1, r);
                ans[v] = unite(ans[v * 2 + 1], ans[v * 2 + 2]);
            }
        }
        int[] get(int needL, int needR) {
            return get(0, 0, vals.length - 1, needL, needR);
        }
        int[] get(int v, int l, int r, int needL, int needR) { // O(log N)
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
