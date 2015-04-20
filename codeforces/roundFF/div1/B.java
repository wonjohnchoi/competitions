import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        int N = in.nextInt();
        int M = in.nextInt();
        int K = in.nextInt();
        long P = in.nextLong();
        long[][] a = new long[N][M];
        long[] rs = new long[N];
        long[] cs = new long[M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                a[i][j] = in.nextLong();
                rs[i] += a[i][j];
                cs[j] += a[i][j];
            }
        }
        PriorityQueue<Long> rpq = new PriorityQueue<Long>(N, Collections.reverseOrder());
        PriorityQueue<Long> cpq = new PriorityQueue<Long>(M, Collections.reverseOrder());
        for (int i = 0; i < N; i++) {
            rpq.add(rs[i]);
        }
        for (int i = 0; i < M; i++) {
            cpq.add(cs[i]);
        }
        long[] rt = new long[K + 1];
        long[] ct = new long[K + 1];
        long rp = 0;
        long cp = 0;
        for (int i = 1; i <= K; i++) {
            long rm = rpq.poll();
            rpq.add(rm - P * M);
            rt[i] = rt[i - 1] + rm;
            long cm = cpq.poll();
            cpq.add(cm - P * N);
            ct[i] = ct[i - 1] + cm;
        }
        long best = Long.MIN_VALUE;
        for (int i = 0; i <= K; i++) {
            best = Math.max(best, rt[i] + ct[K - i] - P * i * (K - i));
        }
        out.println(best);
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
