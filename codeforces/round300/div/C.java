import java.util.*;
import java.io.*;
public class C {
    static void solve() {
        int N = in.nextInt();
        int M = in.nextInt();
        int[][] dh = new int[M][2];
        for (int i = 0; i < M; i++) {
            int d = in.nextInt();
            int h = in.nextInt();
            dh[i][0] = d;
            dh[i][1] = h;
        }
        boolean poss = true;
        int mh = -1;
        for (int i = 0; i < M - 1; i++) {
            int dd = dh[i + 1][0] - dh[i][0];
            int h1 = dh[i][1];
            int h2 = dh[i + 1][1];
            int j = (h1 + h2) / 2;
            while (true) {
                int a = Math.abs(j - h1);
                int b = Math.abs(j - h2);
                if (a + b <= dd) {
                    j++;
                } else break;
            }
            j--;
            int a = Math.abs(j - h1);
            int b = Math.abs(j - h2);
            if (a + b > dd) poss = false;
            else mh = Math.max(mh, j);
        }
        mh = Math.max(mh, dh[0][0] - 1 + dh[0][1]);
        mh = Math.max(mh, N - dh[M - 1][0] + dh[M - 1][1]);
        out.println(poss ? mh : "IMPOSSIBLE");
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
