import java.util.*;
import java.io.*;
public class D {
    static int get(int num, int[] dp, int[] div) {
        if (num < 2) return 0;
        if (dp[num] > 0) return dp[num];
        dp[num] = dp[num / div[num]] + 1;
        return dp[num];
    }
    static void solve() {
        int T = in.nextInt();
        List<Integer> primes = new ArrayList<>();
        boolean[] prime = new boolean[5000001];
        int[] div = new int[prime.length];
        int[] dp = new int[prime.length];
        Arrays.fill(prime, true);
        for (int i = 2; i < prime.length; i++) {
            div[i] = i;
        }
        for (int i = 2; i * i < prime.length; i++) {
            if (prime[i]) {
                for (int j = i * i; j < prime.length; j += i) {
                    prime[j] = false;
                    div[j] = i;
                }
            }
            if (prime[i]) {
                primes.add(i);
            }
        }
        int[] adp = new int[prime.length];
        for (int i = 0; i < dp.length; i++) {
            adp[i] = get(i, dp, div);
            if (i > 0) adp[i] += adp[i - 1];
        }
        for (int t = 0; t < T; t++) {
            int a, b;
            a = in.nextInt();
            b = in.nextInt();
            // out.println(a + " " + get(a, dp, div));
            // out.println(b + " " + get(b, dp, div));
            out.println(adp[a] - adp[b]);
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
