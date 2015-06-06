import java.util.*;
import java.io.*;
public class D {
    static void solve() {
        int N = in.nextInt();
        int M = in.nextInt();
        char[][] b = new char[N][];
        for (int i = 0; i < N; i++) {
            b[i] = in.next().toCharArray();
        }
        int[][] v = new int[N][M];
        int tot = 0;
        for (int r = N - 1; r >= 0; r--) {
            for (int c = M - 1; c >= 0; c--) {
                boolean ok = (b[r][c] == 'W' && v[r][c] == 1)
                    || (b[r][c] == 'B' && v[r][c] == -1);
                if (!ok) {
                    // paint 0,0~r,c with adding
                    int need = 0;
                    if (b[r][c] == 'W') need = 1 - v[r][c];
                    else need = -1 - v[r][c];
                    for (int rr = 0; rr <= r; rr++) {
                        for (int cc = 0; cc <= c; cc++) {
                            v[rr][cc] += need;
                        }
                    }
                    tot++;
                }
            }
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
