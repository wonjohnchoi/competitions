import java.util.*;
import java.io.*;
public class E {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        long N, M;
        N = in.nextLong();
        M = in.nextLong();
        if (N > M) {
            long tmp = N;
            N = M;
            M = tmp;
        } // N <= M
        long gcd = gcd(N, M);
        int B, G;
        HashSet<Long> starts = new HashSet<Long>();
        B = in.nextInt();
        for (int i = 0; i < B; i++) {
            starts.add(in.nextLong());
        }
        G = in.nextInt();
        for (int i = 0; i < G; i++) {
            starts.add(in.nextLong());
        }
        long maxIdx = -1;
        for (int i = 0; i < gcd; i++) {
            long j = i;
            long idx = -1;
            while (true) {
                if (starts.contains(j)) {
                    idx = j;
                    break;
                }
                j = (j + N) % M;
                if (i == j) break;
            }
            if (idx == -1) {
                out.println(-1);
                System.exit(0);
            }
            j = idx;
            // out.println(idx + " " + M + " " + N);
            for (long k = idx; k < M * N / gcd + idx; k += N) {
                long l = k % M;
                if (starts.contains(l)) {
                    j = Math.min(j, l);
                }
                // out.println(j);
                maxIdx = Math.max(maxIdx, j);
                j = j + N;
            }
        }
        out.println(maxIdx);
    }
    static long gcd(long n, long m) {
        if (n < m) return gcd(m, n);
        if (m == 0) return n;
        return gcd(m, n % m);
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
