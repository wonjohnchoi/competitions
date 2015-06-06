import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        int N = in.nextInt();
        boolean[][] b = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String line = in.next();
            for (int j = 0; j < N; j++) {
                b[i][j] = line.charAt(j) == '1';
            }
        }
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        int M = -1;
        int[] ret = new int[N];
        outer : for (int i = 0; i < 10000; i++) {
            boolean[] comes = new boolean[N];
            for (int j = 0; j < N; j++) {
                if (Math.random() >= 0.5) comes[j] = true;
            }
            int[] a2 = new int[N];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (b[j][k] && comes[j]) a2[k]++;
                }
            }
            for (int j = 0; j < N; j++) {
                if (a[j] == a2[j]) continue outer;
            }
            if (M == -1) {
                M = 0;
                for (int j = 0; j < N; j++) {
                    if (comes[j]) {
                        ret[M++] = j + 1;
                    }
                }
            }
        }
        if (M == -1) {
            out.println(-1);
        } else {
            out.println(M);
            for (int i = 0; i < M; i++) {
                if (i != 0) out.print(" ");
                out.print(ret[i]);
            }
            out.println();
        }
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
