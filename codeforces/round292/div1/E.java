import java.util.*;
import java.io.*;
public class E {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N, M;
        N = in.nextInt();
        M = in.nextInt();
        long gcd = gcd(N, M);
        int B, G;
        long[] n = new long[N];
        Arrays.fill(n, -1);
        long[] m = new long[M];
        Arrays.fill(m, -1);
        B = in.nextInt();
        for (int i = 0; i < B; i++) {
            n[i] = in.nextLong();
        }
        G = in.nextInt();
        for (int i = 0; i < G; i++) {
            m[i] = in.nextLong();
        }
        int lastUnfilled = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (n[i] == -1) {
                lastUnfilled = i;
                break;
            }
        }
        for (int i = M - 1; i >= 0; i--) {
            if (m[i] == -1) {
                lastUnfilled = Math.max(lastUnfilled, i);
                break;
            }
        }
        if (N > M) {
            int tmp = N;
            N = M;
            M = tmp;
        } // N <= M

        long maxIdx = -1;
        for (int i = 0; i < gcd; i++) {
            int j = i;
            int idx = -1;
            while (true) {
                if (contains(n, m, j)) {
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
                int l = (int) (k % M);
                if (contains(n, m, l)) {
                    j = Math.min(j, l);
                }
                // out.println(j);
                maxIdx = Math.max(maxIdx, j);
                j = j + N;
            }
        }
        out.println(Math.max(lastUnfilled, maxIdx));
    }
    static boolean contains(long[] n, long[] m, int i) {
        return (i < n.length && n[i] != -1)
            || (i < m.length && m[i] != -1);
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
