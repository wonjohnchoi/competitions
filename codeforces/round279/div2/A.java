import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N = in.nextInt();
        int[][] tt = new int[3][N];
        int[] ti = new int[3];
        for (int i = 0; i < N; i++) {
            int t = in.nextInt();
            tt[t - 1][ti[t - 1]++] = i + 1;
            // i + 1
        }
        int cnt = Math.min(ti[0], Math.min(ti[1], ti[2]));
        out.println(cnt);
        for (int i = 0; i < cnt; i++) {
            out.println(tt[0][i] + " " + tt[1][i] + " " + tt[2][i]);
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
