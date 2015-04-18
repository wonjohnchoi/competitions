import java.util.*;
import java.io.*;
public class Template {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    static int TC;
    static String solve(int tc) {

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
