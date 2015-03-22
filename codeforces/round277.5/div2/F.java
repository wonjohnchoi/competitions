import java.util.*;
import java.io.*;
public class F {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N, M;
        long MOD;
        N = in.nextInt();
        M = in.nextInt();
        MOD = in.nextLong();
        int[] cnt = new int[N];
        for (int i = 0; i < M; i++) {
            String s = in.next();
            for (int j = 0; j < N; j++) {
                if (s.charAt(j) == '1') cnt[j]++;
            }
        }
        for (int i = 0; i < N; i++) {
            cnt[i] = 2 - cnt[i];
        }
        long[] ways = new long[N - M + 1]; // key = numTwos
        // int j = N - M;
        ways[N - M] = 1;
        int rem = (N - M) * 2;
        for (int i = N - 1; i >= 0; i--) {
            long[] nways = new long[N - M + 1];
            for (int k = 0; k <= N - M; k++) {
                if (ways[k] > 0) {
                    // k twos
                    int numOne = rem - 2 * k;
                    //out.println(i + " " + k + " " + numOne + " : " + ways[k]);
                    if (cnt[i] == 1) {
                        if (k >= 1) nways[k - 1] += k * ways[k];
                        if (numOne >= 1) nways[k] += numOne * ways[k];
                    } else if (cnt[i] == 2) {
                        if (k >= 1 && numOne >= 1)
                            nways[k - 1] += k * numOne * ways[k];
                        if (k >= 2)
                            nways[k - 2] += k * (k - 1) / 2 * ways[k];
                        if (numOne >= 2) nways[k] += numOne * (numOne - 1) / 2 * ways[k];
                    } else {
                        nways[k] = ways[k];
                    }
                }
                nways[k] %= MOD;
                if (k >= 1) nways[k - 1] %= MOD;
                if (k >= 2) nways[k - 2] %= MOD;
            }
            rem -= cnt[i];
            // j = Math.max(j - 2, 0);
            ways = nways;
        }
        out.println(ways[0]);
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
