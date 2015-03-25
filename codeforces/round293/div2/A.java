import java.util.*;
import java.io.*;
public class A {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        String S, T;
        S = in.next();
        T = in.next();
        char[] S2 = new char[S.length()];
        for (int i = S.length() - 1, carry = 1; i >= 0; i--) {
            int nc = S.charAt(i) + carry;
            carry = 0;
            if (nc == 'z' + 1) {
                nc = 'a';
                carry = 1;
            }
            S2[i] = (char) nc;
        }
        String S3 = new String(S2);
        if (S3.equals(T)) out.println("No such string");
        else out.println(S3);
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
