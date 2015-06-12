import java.util.*;
import java.io.*;
public class C {
    static void solve() {
        int N, M;
        N = in.nextInt();
        M = in.nextInt();
        int[] a = new int[N];
        int last = -1;
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
            if (a[i] != 0) last = i;
        }
        if (last == -1) {
            out.println(0);
            return;
        }
        int tot = 1;
        int[] b = new int[N];
        b[0] = M;
        int[] c = new int[2];
        c[1] = -1;
        while (a[last] > 0) {
            //out.println(c[0] + " " + c[1] + " " + Arrays.toString(a) + " " + Arrays.toString(b));
            if (c[1] == -1) {
                tot += a[c[0]] / b[c[0]];
                a[c[0]] %= b[c[0]];
                if (a[last] == 0) break;
                if (c[0] + 1 < N) {
                    b[c[0] + 1] = b[c[0]] - a[c[0]];
                }
                b[c[0]] = a[c[0]];
                if (b[c[0]] > 0)
                    c[1] = c[0];
                a[c[0]] = 0;
                c[0]++;
                tot++;
            } else {
                tot++;
                int c1 = c[1];
                int save = b[c[1]];
                if (a[c[0]] > b[c[0]]) {
                    a[c[0]] -= b[c[0]];
                    b[c[0]] += save;
                    c[1] = -1;
                } else {
                    if (c[0] + 1 < N) {
                        b[c[0] + 1] = b[c[0]] - a[c[0]];
                    }
                    b[c[0]] = a[c[0]] + save;
                    c[1] = c[0];
                    a[c[0]] = 0;
                    c[0]++;
                }
                b[c1] = 0;
            }
        }
        //out.println(c[0] + " " + c[1] + " " + Arrays.toString(a) + " " + Arrays.toString(b));
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
