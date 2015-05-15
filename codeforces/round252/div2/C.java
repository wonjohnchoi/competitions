import java.util.*;
import java.io.*;
public class C {
    static void solve() {
        int N = in.nextInt();
        int M = in.nextInt();
        int K = in.nextInt();
        int j = 0;
        int k = 0;
        int rev = 1;
        for (int i = 0; i < K - 1; i++) {
            out.print(2);
            for (int l = 0; l < 2; l++) {
                p(j, k);
                k += rev;
                if (k == M || k == -1) {
                    k += -rev;
                    rev *= -1;
                    j++;
                }
            }
            out.println();
        }
        out.print(N * M - 2 * (K - 1));
        while (j < N) {
            p(j, k);
            k += rev;
            if (k == M || k == -1) {
                k += -rev;
                rev *= -1;
                j++;
            }
        }
        out.println();
    }
    static void p(int j, int k) {
        out.print(" " + (j + 1) + " " + (k + 1));
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
