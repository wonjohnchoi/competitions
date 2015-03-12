import java.util.*;
import java.io.*;
public class E {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        long N, M;
        N = in.nextLong();
        M = in.nextLong();
        int B, G;
        HashSet<Long> NN = new HashSet<Long>();
        HashSet<Long> MM = new HashSet<Long>();
        B = in.nextInt();
        for (int i = 0; i < B; i++) {
            NN.add(in.nextLong());
        }
        G = in.nextInt();
        for (int i = 0; i < G; i++) {
            MM.add(in.nextLong());
        }
        /*
        if (N > M) {
            long tmp = N;
            N = M;
            M = tmp;
        } // N <= M
        */
        long maxIdx = Math.max(maxIdx(N, M, NN, MM), maxIdx(M, N, MM, NN));
        out.println(maxIdx);
    }
    static long maxIdx(long N, long M, HashSet<Long> NN, HashSet<Long> MM) {
        long maxIdx = -2;
        long gcd = gcd(N, M);
        for (int i = 0; i < gcd; i++) {
            long j = i;
            long idx = -1;
            while (true) {
                if (NN.contains(j) || MM.contains(j)) {
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
                if (NN.contains(l) || MM.contains(l)) {
                    j = Math.min(j, l);
                }
                // out.println(j);
                if (!(MM.contains(l) && l == j)) {
                    maxIdx = Math.max(maxIdx, j);
                }
                j = j + N;
            }
        }
        return maxIdx;
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
