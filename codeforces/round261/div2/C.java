import java.util.*;
import java.io.*;
public class C {
    static int[][] res;
    static int N, K, D;
    static void solve() {
        N = in.nextInt();
        K = in.nextInt();
        D = in.nextInt();
        res = new int[D][N];
        f(0, N - 1, 0);
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < N; j++) {
                if (j != 0) out.print(" ");
                out.print(res[i][j]);
            }
            out.println();
        }
    }
    static void f(int l, int r, int d) {
        if (d == D) {
            if (l != r) {
                out.println(-1);
                out.close();
                System.exit(0);
            }
            return;
        }
        int n = r - l + 1;
        int p;
        if (n % K == 0) p = n / K;
        else p = n / K + 1;
        for (int i = 0; i < K; i++) {
            int start = l + p * i;
            int end = Math.min(l + p * (i + 1) - 1, r);
            // out.println(start + " " + end); out.flush();
            for (int j = start; j <= end; j++) {
                res[d][j] = i + 1;
            }
            f(start, end, d + 1);
            if (end == r) break;
        }
    }
    static PrintWriter out = new PrintWriter(System.out);
    static InputReader in = new InputReader(System.in);
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
