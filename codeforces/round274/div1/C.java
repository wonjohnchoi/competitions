import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N, a, b, K;
        N = in.nextInt();
        a = in.nextInt() - 1;
        b = in.nextInt() - 1;
        K = in.nextInt();
        if (a > b) {
            a = N - 1 - a;
            b = N - 1 - b;
        }
        // N - 1 >= b > a >= 0
        long MOD = (long) 1e9 + 7;
        long[] cnt = new long[N];
        cnt[a] = 1;
        for (int i = 0; i < K; i++) {
            long[] ncnt = new long[N];
            int x = 0;
            long sum = 0;
            for (int y = 0; y < b; y++) {
                for (; Math.abs(x - y) < Math.abs(x - b); x++) {
                    sum += cnt[x];
                    sum %= MOD;
                }
                ncnt[y] = (sum - cnt[y] + MOD) % MOD;
            }
            cnt = ncnt;
        }
        long tot = 0;
        for (int i = 0; i < b; i++) {
            tot += cnt[i];
            tot %= MOD;
        }
        out.println(tot);
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
