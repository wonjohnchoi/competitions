import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int M, S;
        M = in.nextInt();
        S = in.nextInt();
        if ((S == 0 && M > 1) || 9 * M < S) {
            out.println("-1 -1");
        } else {
            String min = "";
            String max = "";
            int rem = S - 1;
            for (int i = 0; i < M - 1; i++) {
                int use = rem >= 9 ? 9 : rem;
                rem -= use;
                min = use + min;
            }
            min = (1 + rem) + min;
            rem = S;
            for (int i = 0; i < M; i++) {
                int use = rem >= 9 ? 9 : rem;
                rem -= use;
                max = max + use;
            }
            out.println(min + " " + max);
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
