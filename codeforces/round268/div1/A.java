import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N = in.nextInt();
        if (N <= 3) out.println("NO");
        else {
            out.println("YES");
            if (N % 2 == 0) {
                for (int i = 5; i < N; i += 2) {
                    out.println((i + 1) + " - " + i + " = " + 1);
                }
                for (int i = 0; i < N / 2 - 2; i++) {
                    out.println("1 * 1 = 1");
                }
                int tot = 1;
                for (int i = 2; i <= 4; i++) {
                    out.println(tot + " * " + i + " = " + tot * i);
                    tot *= i;
                }
            } else {
                for (int i = 6; i < N; i += 2) {
                    out.println((i + 1) + " - " + i + " = " + 1);
                }
                for (int i = 0; i < (N - 1) / 2 - 2; i++) {
                    out.println("1 * 1 = 1");
                }
                out.println("5 * 4 = 20");
                out.println("20 + 3 = 23");
                out.println("23 + 2 = 25");
                out.println("25 - 1 = 24");
            }
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
