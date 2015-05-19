import java.util.*;
import java.io.*;
public class C {
    static double EPSILON = (double) 1e-9;
    static boolean eq(double a, double b) {
	return Math.abs(a - b) <= EPSILON;
    }
    static boolean c(double[][] aa, int ii) {
	if (ii == 4) {
	    for (int i = 0; i < 4; i++) {
		int j = (i + 1) % 4;
		int k = (j + 1) % 4;
		double x, y, x2, y2;
		x = aa[j][0] - aa[i][0];
		x2 = aa[k][0] - aa[j][0];
		y = aa[j][1] - aa[i][1];
		y2 = aa[k][1] - aa[j][1];
		if (eq(x, 0) && eq(y, 0)) return false;
		if (eq(x2, 0) && eq(y2, 0)) return false;
		if (!eq(x * x2, y * y2)) return false;
		if (!eq(x * x + y * y, x2 * x2 + y2 * y2)) return false;
	    }
	    return true;
	}
	for (int j = ii; j < 4; j++) {
	    double[] bb = aa[ii];
	    aa[ii] = aa[j];
	    aa[j] = bb;
	    if (c(aa, ii + 1)) return true;
	    aa[j] = aa[ii];
	    aa[ii] = bb;
	}
	return false;
    }
    static int f(double[][] aa, int i, int moves) {
	if (i == 4) {
	    // if square, moves
	    // else max int
	    if (c(aa, 0)) {
		return moves;
	    }
	    return Integer.MAX_VALUE;
	}
	int min = Integer.MAX_VALUE;
	for (int j = 0; j < 4; j++) {
	    min = Math.min(min, f(aa, i + 1, moves + j));
	    double x, y, a, b;
	    x = aa[i][0];
	    y = aa[i][1];
	    a = aa[i][2];
	    b = aa[i][3];
	    aa[i][0] = -(y - b) + a;
	    aa[i][1] = (x - a) + b;
	}
	return min;
    }
    static void solve() {
	int N = in.nextInt();
	for (int i = 0; i < N; i++) {
	    double[][] a = new double[4][4];
	    for (int j = 0; j < 4; j++) {
		for (int k = 0; k < 4; k++) {
		    a[j][k] = in.nextDouble();
		}
	    }
	    int ans = f(a, 0, 0);
	    out.println(ans == Integer.MAX_VALUE ? -1 : ans);
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
