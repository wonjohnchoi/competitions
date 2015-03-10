import java.util.*;
import java.io.*;
public class E {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N, M, dx, dy;
        N = in.nextInt();
        M = in.nextInt();
        dx = in.nextInt();
        dy = in.nextInt();
        int[] xx = new int[N];
        int[] yy = new int[N];
        int x, y;
        x = y = 0;
        for (int i = 0; i < N; i++) {
            xx[x] = i;
            yy[y] = i;
            x += dx; x %= N;
            y += dy; y %= N;
        }

        int[] sums = new int[N];
        int best = -1;
        int ansX, ansY;
        ansX = ansY = -1;
        for (int i = 0; i < M; i++) {
            x = in.nextInt();
            y = in.nextInt();
            sums[(xx[x] - yy[y] + N) % N]++;
            if (best == -1 || sums[best] < sums[(xx[x] - yy[y] + N) % N]) {
                best = (xx[x] - yy[y] + N) % N;
                ansX = x;
                ansY = y;
            }
        }
        out.println(ansX + " " + ansY);
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
