import java.util.*;
import java.io.*;
public class C {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N, M;
        N = in.nextInt();
        M = in.nextInt();
        int ret = 0;
        for (int i = 0; i <= Math.min(N, M / 2); i++) {
            ret = Math.max(ret, Math.min((N - i) / 2, M - 2 * i) + i);
        }
        out.println(ret);
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
