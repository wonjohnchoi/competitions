import java.util.*;
import java.io.*;
public class F {
    static int R, C;
    static int[] nr, nc;
    static void solve() {
        R = in.nextInt();
        C = in.nextInt();
        nr = new int[R];
        nc = new int[C];
        for (int i = 0; i < R; i++) {
            nr[i] = in.nextInt();
        }
        for (int i = 0; i < C; i++) {
            nc[i] = in.nextInt();
        }
        boolean[][] b = new boolean[R][C];
        int[] nr2 = new int[R];
        int[] nc2 = new int[C];
        find(b, 0, 0, nr2, nc2);
    }
    static boolean[] bb = new boolean[] {true, false};
    static void find(boolean[][] b, int r, int c, int[] nr2, int[] nc2) {
        if (r == R) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    out.print(b[i][j] ? "*" : ".");
                }
                out.println();
            }
            out.close();
            System.exit(0);
        }
        int rr = r;
        int cc = c + 1;
        if (cc == C) {
            rr++;
            cc = 0;
        }
        for (boolean cb : bb) {
            b[r][c] = cb;
            boolean ir = cb && (c == 0 || (c >= 1 && !b[r][c - 1]));
            boolean ic = cb && (r == 0 || (r >= 1 && !b[r - 1][c]));
            if (ir) nr2[r]++;
            if (ic) nc2[c]++;
            int mr = nr2[r] + (C - 1 + (cb ? 0 : 1) - c) / 2;
            int mc = nc2[c] + (R - 1 + (cb ? 0 : 1) - r) / 2;
            // out.println(r + " " + c);
            // out.println(nr2[r] + " " + nc2[c] + " " + mr + " " + mc); out.flush();
            if (nr2[r] <= nr[r] && nr[r] <= mr && nc2[c] <= nc[c] && nc[c] <= mc) {
                find(b, rr, cc, nr2, nc2);
            }
            if (ir) nr2[r]--;
            if (ic) nc2[c]--;
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
