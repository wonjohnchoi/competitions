import java.util.*;
import java.io.*;
public class A {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int TC = in.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            String ans = "";
            int M = in.nextInt();
            String S = in.next();
            int tot = 0;
            int up = 0;
            for (int i = 0; i < S.length(); i++) {
                int val = S.charAt(i) - '0';
                int need = Math.max(i - up, 0);
                tot += need;
                up += need + val;
            }
            ans = "" + tot;
            out.printf("Case #%d: %s\n", tc, ans);
        }
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
