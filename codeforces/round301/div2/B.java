import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        int N, K, P, X, Y;
        N = in.nextInt();
        K = in.nextInt();
        P = in.nextInt();
        X = in.nextInt();
        Y = in.nextInt();
        int[] a = new int[N];
        int[] cnt = new int[2];
        int tot = 0;
        for (int i = 0; i < K; i++) {
            a[i] = in.nextInt();
            if (a[i] < Y) cnt[0]++;
            else cnt[1]++;
            tot += a[i];
        }
        int[] ans = new int[N - K];
        for (int i = 0; i < N - K;) {
            for (int j = 0; j < 2; j++) {
                if (j == 1 && (cnt[0] + cnt[1]) % 2 == 1) break;
                if (cnt[0] + 1 <= cnt[1]) {
                    ans[i] = 1;
                    cnt[0]++;
                    tot += 1;
                } else {
                    ans[i] = Y;
                    cnt[1]++;
                    tot += Y;
                }
                i++;
            }
        }
        if (tot > X || cnt[0] > cnt[1]) out.println("-1");
        else {
            for (int i = 0; i < N - K; i++) {
                if (i != 0) out.print(" ");
                out.print(ans[i]);
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
