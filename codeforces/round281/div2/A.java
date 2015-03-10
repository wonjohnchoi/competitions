import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        String[] names = new String[]{in.next(), in.next()};
        int N = in.nextInt();
        boolean[][] valid = new boolean[2][100];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 100; j++) {
                valid[i][j] = true;
            }
        }
        int[][] ss = new int[2][100];
        for (int i = 0; i < N; i++) {
            int t = in.nextInt();
            int ti = in.next().equals("h") ? 0 : 1;
            int p = in.nextInt();
            int s = in.next().equals("y") ? 1 : 2;
            ss[ti][p] += s;
            if (ss[ti][p] >= 2 && valid[ti][p]) {
                valid[ti][p] = false;
                out.println(names[ti] + " " + p + " " + t);
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
