import java.util.*;
import java.io.*;
public class Template {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static TYPE NAME() {

    }
    public static void main(String args[]) {
        int tc = 1;
        while (in.hasNext()) {
            out.println("Case #" + tc + ": " + NAME());
            tc++;
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
