import java.util.*;
import java.io.*;
public class H {
    static double min = -1;
    static void update(double x, int mask, double[] ret) {
        min = x;
        out.println(Arrays.toString(ret) + " " + mask + " " + x);
    }
    static void solve() {
        double[] a = new double[4];
        for (int i = 0; i < 4; i++) {
            a[i] = in.nextDouble();
        }
        min = Double.MAX_VALUE;
        for (int mask = 0; mask < 8; mask++) {
            double[] e = new double[] {1, 0};
            double[] f, g, h;
            f = new double[2];
            g = new double[2];
            h = new double[2];
            f[0] = zero(mask, 0);
            g[0] = zero(mask, 1);
            h[0] = zero(mask, 2);
            f[1] = -zero(mask, 0) * a[0] + a[1];
            g[1] = -zero(mask, 1) * a[0] + a[2];
            h[1] = -zero(mask, 2) * a[0] + a[3];
            double[] eh = mult(e, h);
            double[] fg = mult(f, g);
            double[] ret = sub(eh, fg);
            out.println(Arrays.toString(eh) + " " + Arrays.toString(fg));
            out.println(mask + " " + Arrays.toString(e) + " " + Arrays.toString(f) + " " + Arrays.toString(g) + " " + Arrays.toString(h));
            if (ret[0] == 0) {
                if (ret[1] == 0) {
                    if (ret[2] == 0) {
                        update(0, mask, ret);
                    } else {
                    }
                } else {
                    double x = -ret[2] / ret[1];
                    update(Math.abs(a[0] - x), mask, ret);
                }
            } else {
                double det = ret[1] * ret[1] - 4 * ret[0] * ret[2];
                if (det >= 0) {
                    for (int k : new int[] {-1, 1}) {
                        double x = (-ret[1] + k * Math.sqrt(det)) / 2 / ret[0];
                        update(Math.abs(a[0] - x), mask, ret);
                    }
                }
            }
        }
        out.println(min);
    }
    static double[] sub(double[] a, double[] b) {
        double[] ret = new double[3];
        for (int i = 0; i < 3; i++) {
            ret[i] = a[i] - b[i];
        }
        return ret;
    }
    static double[] mult(double[] a, double[] b) {
        return new double[] {a[0] * b[0], a[0] * b[1] +  a[1] * b[0], a[1] * b[1]};
    }
    static int zero(int mask, int i) {
        if (get(mask, i)) return 1;
        return -1;
    }
    static boolean get(int mask, int i) {
        return (mask & (1 << i)) > 0;
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
