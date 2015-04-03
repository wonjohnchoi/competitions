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
        outer : while (true) {
            if (need <= A * B) {
                out.println(A * B);
                out.println(A + " " + B);
                break;
            }
            for (long i = 1; i * i <= need ; i++) {
                long j = need / i;
                boolean a = i >= A && j >= B;
                boolean b = i >= B && j >= A;
                if (need % i == 0 && (a || b)) {
                    out.println(need);
                    if (a) out.println(i + " " + j);
                    else out.println(j + " " + i);
                    break outer;
                }
            }
            need++;
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
}
