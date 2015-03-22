import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N = in.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        int cnt = 0;
        int[] x = new int[N];
        int[] y = new int[N];
        for (int i = 0; i < N; i++) {
            int bestJ = i;
            for (int j = i + 1; j < N; j++) {
                if (a[bestJ] > a[j]) bestJ = j;
            }
            int tmp = a[i];
            a[i] = a[bestJ];
            a[bestJ] = tmp;
            x[cnt] = i;
            y[cnt++] = bestJ;
        }
        out.println(N);
        for (int i = 0; i < N; i++) {
            out.println(x[i] + " " + y[i]);
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
