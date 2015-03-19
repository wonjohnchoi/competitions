import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int[] a = new int[5];
        int s = 0;
        for (int i = 0; i < 5; i++) {
            a[i] = in.nextInt();
            s += a[i];
        }
        if (s % 5 == 0 && s >= 5) out.println(s / 5);
        else out.println(-1);
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
