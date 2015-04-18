import java.util.*;
import java.io.*;
public class B {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    static int TC;
    static int B;
    static long N;
    static long[] b;
    static String solve(int tc) {
        B = in.nextInt();
        N = in.nextLong();
        b = new long[B];
        for (int i = 0; i < B; i++) {
            b[i] = in.nextLong();
        }
        long l = -1, r = (long) 1e15;
        while (l < r) {
            long m = (l + r) / 2;
            if (l + 1 == r) m = r;
            long cnt = cntStartedAfterMin(m);
            // out.println(m + ":"+cnt + " " + N);
            // out.println(l + " " + r);
            if (cnt < N) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        long[] a = simulateAfterMin(l);
        long cnt = cntStartedAfterMin(l);
        long ans = -1;
        // d("minute: " + l + " cur: " + cnt + " state: " + Arrays.toString(a));
        for (long j = cnt + 1; j <= N; j++) {
            long min = Long.MAX_VALUE;
            for (int i = 0; i < B; i++) {
                min = Math.min(min, a[i]);
            }
            int bestI = B;
            for (int i = 0; i < B; i++) {
                a[i] -= min;
                if (a[i] == 0) {
                    bestI = Math.min(bestI, i);
                }
            }
            a[bestI] += b[bestI];
            if (j == N) {
                ans = bestI + 1;
                break;
            }
        }
        return ans + "";
    }
    static long cntStartedAfterMin(long m) {
        if (m == -1) return 0;
        // long[] a = simulateAfterMin(m);
        long tot = 0;
        for (int i = 0; i < B; i++) {
            tot += m / b[i] + 1;
        }
        return tot;
    }
    static long[] simulateAfterMin(long m) {
        long[] a = new long[B];
        for (int i = 0; i < B; i++) {
            a[i] = b[i] - m % b[i];
        }
        return a;
    }
    public static void main(String args[]) {
        TC = in.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            out.printf("Case #%d: %s\n", tc, solve(tc));
        }
        out.close();
    }
    static void d(Object o) {
        out.println(o);
        out.flush();
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
