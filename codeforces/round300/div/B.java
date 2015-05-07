import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        String N = in.next();
        int maxDigit = 0;
        for (int i = 0; i < N.length(); i++) {
            int digit = N.charAt(i) - '0';
            maxDigit = Math.max(digit, maxDigit);
        }
        int[] ans = new int[maxDigit];
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < N.length(); j++) {
                int digit = N.charAt(j) - '0';
                if (i < digit) {
                    ans[i] += (int) Math.pow(10, N.length() - j - 1);
                }
            }
        }
        out.println(maxDigit);
        for (int i = 0; i < maxDigit; i++) {
            if (i != 0) out.print(" ");
            out.print(ans[i]);
        }
        out.println();
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
