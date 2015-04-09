import java.util.*;
import java.io.*;
public class C {
    static void solve() {
        int N = (int) in.nextLong();
        long[][] a = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = in.nextLong();
            }
        }
        long[] d1 = new long[2 * N - 1];
        long[] d2 = new long[2 * N - 1]; // -(N - 1) ~ (N - 1) // + N - 1
        for (int i = 0; i < d1.length; i++) {
            for (int j = 0; j < N; j++) {
                int k = i - j;
                if (k >= 0 && k < N) {
                    d1[i] += a[j][k];
                }
            }
        }
        for (int i = 0; i < d2.length; i++) {
            int l = i - (N - 1);
            for (int j = 0; j < N; j++) {
                int k = j - l;
                if (k >= 0 && k < N) {
                    d2[i] += a[j][k];
                }
            }
        }
        // out.println(Arrays.toString(d1));
        // out.println(Arrays.toString(d2));
        long[] b = new long[2];
        Arrays.fill(b, -1);
        long[][] c = new long[2][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int k = (i + j) % 2;
                long d = d1[i + j] + d2[i - j + N - 1] - a[i][j];
                if (b[k] < d) {
                    b[k] = d;
                    c[k] = new long[] {i + 1, j + 1};
                }
            }
        }
        out.println(b[0] + b[1]);
        out.println(c[0][0] + " " + c[0][1] + " " + c[1][0] + " " + c[1][1]);
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
    /*
    public int DONTUSEnextInt() {
	return Integer.parseInt(next());
        }*/
}
