import java.util.*;
import java.io.*;
public class IOPC15O {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int T = in.nextInt();
        for (int tc = 0; tc < T; tc++) {
            double a, b, c;
            a = in.nextDouble();
            b = in.nextDouble();
            c = in.nextDouble();
            double max = Math.max(Math.max(a, b), c);
            double rest = a + b + c - max;
            out.println(Math.sqrt(max * max + rest * rest));
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
