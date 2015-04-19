import java.util.*;
import java.io.*;
public class C {
    static void solve() {
        int N = in.nextInt();
        boolean[] used = new boolean[N + 1];
        int[][] r = new int[N][2];
        boolean left = true;
        int ri = 0;
        for (int i = 3; i * 2 <= N; i++) {
            if (i % 2 == 1 && !used[i]) {
                for (int j = i; j <= N; j += i) {
                    if (j == i * 2) continue;
                    if (!used[j]) {
                        used[j] = true;
                        if (left) {
                            r[ri][0] = j; left = false;
                        } else {
                            r[ri][1] = j; left = true; ri++;
                        }
                    }
                }
                if (!left) {
                    used[i * 2] = true; left = true; r[ri][1] = i * 2; ri++;
                }
            }
        }
        for (int i = 2; i <= N; i += 2) {
            if (!used[i]) {
                if (left) {
                    r[ri][0] = i; left = false;
                } else {
                    r[ri][1] = i; left = true; ri++;
                }
            }
        }
        out.println(ri);
        for (int i = 0; i < ri; i++) {
            out.println(r[i][0] + " " + r[i][1]);
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
