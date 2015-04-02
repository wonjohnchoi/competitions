import java.util.*;
import java.io.*;
public class C {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N = in.nextInt();
        long[] accum = new long[N - 1];
        for (int i = 0; i < N - 1; i++) {
            int a = in.nextInt();
            if (i == 0) accum[0] = a;
            else accum[i] = a + accum[i - 1];
        }
        long last = accum[N - 2] + in.nextInt();
        long tot = 0;
        out.println("last: " + last);
        if (last % 3 == 0) {
            long oneThird = 0;
            long twoThird = 0;
            for (int i = 0; i < N - 1; i++) {
                long a = accum[i];
                if (a == last / 3) oneThird++;
                if (a == last * 2 / 3) twoThird++;
            }
            if (last == 0) {
                out.println(oneThird * (oneThird - 1) / 2);
            } else {
                out.println(oneThird * twoThird);
            }
        } else {
            out.println(0);
        }
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
