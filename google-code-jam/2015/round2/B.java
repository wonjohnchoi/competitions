import java.util.*;
import java.io.*;
public class B {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    static int TC;
    static String solve(int tc) {
        int N = in.nextInt();
        double V = in.nextDouble();
        double C = in.nextDouble();
        double[] r = new double[N];
        double[] c = new double[N];
        double best = Double.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            r[i] = in.nextDouble();
            c[i] = in.nextDouble();
            if (c[i] == C) {
                best = Math.min(best, V / r[i]);
            }
        }
        if (N == 2 && c[0] == C && c[1] == C) {
            best = Math.min(best, V / (r[0] + r[1]));
        }
        for (int i = 0; i < N; i++) {
            if (c[i] <= C) continue;
            for (int j = 0; j < N; j++) {
                if (c[j] >= C) continue;
                // c[i] > C && c[j] < C
                // i = 1 j = 2
                double t1 = (C - c[j]) / (c[i] - c[j]) * V / r[i];
                double t2 = (c[i] - C) / (c[i] - c[j]) * V / r[j];
                best = Math.min(best, Math.max(t1, t2));
            }
        }
        if (best == Double.MAX_VALUE) return "IMPOSSIBLE";
        return best + "";
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
