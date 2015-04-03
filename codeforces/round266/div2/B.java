import java.util.*;
import java.io.*;
public class B {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        long N, A, B;
        N = in.nextLong();
        A = in.nextLong();
        B = in.nextLong();
        long need = 6L * N;
        if (need <= A * B) {
            print(A, B, false);
        }
        boolean swapped = false;
        if (A > B) {
            long tmp = A;
            A = B;
            B = tmp;
            swapped = true;
        }
        long bestI, bestJ, best;
        bestI = bestJ = best = -1;
        for (long i = A; i * i <= need; i++) {
            long j = need / i;
            while (j * i < need) j++;
            if (j < B) continue;
            if (best == -1 || best > j * i) {
                best = i * j;
                bestI = i;
                bestJ = j;
            }
        }
        print(bestI, bestJ, swapped);
    }
    static void print(long a, long b, boolean swapped) {
        if (swapped) {
            long tmp = a;
            a = b;
            b = tmp;
        }
        out.println(a * b);
        out.println(a + " " + b);
        out.close();
        System.exit(0);
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
}
