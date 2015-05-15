import java.util.*;
import java.io.*;
public class D {
    static void solve() {
        int N = in.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt() - 1;
        }
        int M = in.nextInt();
        int goal = N - M;
        int[] marked = new int[N];
        int cur = cntCycles(a, marked);
        int K = Math.abs(goal - cur);
        out.println(K);
        int[][] ans = new int[K][2];
        int ansI = 0;
        outer : while (goal > cur) {
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (same(a, i, j, marked)) {
                        swap(a, i, j);
                        mark(a, marked, i, cnt++);
                        mark(a, marked, j, cnt++);
                        ans[ansI++] = new int[] {i, j};
                        cur++;
                        continue outer;
                    }
                }
            }
        }
        outer : while(goal < cur) {
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (!same(a, i, j, marked)) {
                        swap(a, i, j);
                        mark(a, marked, i, cnt++);
                        ans[ansI++] = new int[] {i, j};
                        cur--;
                        continue outer;
                    }
                }
            }
        }
        for (int i = 0; i < K; i++) {
            if (i != 0) out.print(" ");
            out.print((ans[i][0] + 1) + " " + (ans[i][1] + 1));
        }
    }
    static boolean same(int[] a, int i, int j, int[] marked) {
        boolean ret = marked[i] == marked[j];
        return ret;
    }
    static int cnt = 0;
    static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    static int cntCycles(int[] a, int[] marked) {
        Arrays.fill(marked, -1);
        for (int i = 0; i < a.length; i++) {
            if (marked[i] == -1) {
                mark(a, marked, i, cnt++);
            }
        }
        return cnt;
    }
    static void mark(int[] a, int[] marked, int i, int color) {
        if (marked[i] == color) return;
        marked[i] = color;
        mark(a, marked, a[i], color);
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
