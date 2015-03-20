import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int[] a = new int[10];
        for (int i = 0; i < 6; i++) {
            a[in.nextInt()]++;
        }
        for (int i = 0; i < 10; i++) {
            if (a[i] >= 4) {
                a[i] -= 4;
                for (int j = 0; j < 10; j++) {
                    if (a[j] == 2) {
                        out.println("Elephant");
                        System.exit(0);
                    }
                }
                out.println("Bear");
                System.exit(0);
            }
        }
        out.println("Alien");
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
