import java.util.*;
import java.io.*;
public class IOPC15A {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int T = in.nextInt();
        for (int tc = 0; tc < T; tc++) {
            double r, x, y, x0, y0, v;
            r = in.nextDouble();
            x = in.nextDouble();
            y = in.nextDouble();
            x0 = in.nextDouble();
            y0 = in.nextDouble();
            v = in.nextDouble();
            double dist1 = Math.sqrt((x0 - x) * (x0 - x) + (y0 - y) * (y0 - y));
            double dist2 = r - dist1;
            double time = dist2 / v;
            out.println(Math.max(0, time));
        }
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
