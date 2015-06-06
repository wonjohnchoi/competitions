import java.util.*;
import java.io.*;
public class A {
    static void solve() {
        int N = in.nextInt();
        int M = in.nextInt();
        char[][] b = new char[N][];
        for (int i = 0; i < N; i++) {
            b[i] = in.next().toCharArray();
        }
        int tot = 0;
        for (int r = 0; r < N - 1; r++) {
            outer : for (int c = 0; c < M - 1; c++) {
                String FACE = "face";
                boolean[] check = new boolean[4];
                for (int dr = 0; dr < 2; dr++) {
                    for (int dc = 0; dc < 2; dc++) {
                        int nr = r + dr;
                        int nc = c + dc;
                        int idx = FACE.indexOf(b[nr][nc]);
                        if (idx != -1) check[idx] = true;
                    }
                }
                for (int i = 0; i < 4; i++) {
                    if (!check[i]) {
                        continue outer;
                    }
                }
                tot++;
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
