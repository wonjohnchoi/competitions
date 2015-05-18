import java.util.*;
import java.io.*;
public class A {
    static void solve() {
        int N = in.nextInt();
        int M = in.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        boolean[][] conn = new boolean[N][N];
        for (int i = 0; i < M; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            conn[x][y] = true;
            conn[y][x] = true;
        }
        long tot = 0;
        for (int i = 0; i < N; i++) {
            int best = -1;
            for (int j = 0; j < N; j++) {
                if (a[j] != -1) {
                    if (best == -1 || a[j] > a[best]) {
                        best = j;
                    }
                }
            }
            // out.println("b : " + best + " " + tot);
            for (int j = 0; j < N; j++) {
                if (conn[best][j] && a[j] != -1 && j != best) {
                    // out.println("removing .. " + best + " " + j);
                    tot += a[j];
                }
            }
            a[best] = -1;
        }
        out.println(tot);
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
