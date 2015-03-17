import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N, K;
        N = in.nextInt();
        K = in.nextInt();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < N - K; i++) {
            res.add(i + 1);
        }
        int a = N;
        int b = res.get(res.size() - 1) + 1;
        for (int i = 0; i < K; i++) {
            res.add(a);
            a--;
            i++;
            if (i == K) break;
            res.add(b);
            b++;
        }
        for (int i = 0; i < res.size(); i++) {
            if (i != 0) out.print(" ");
            out.print(res.get(i));
        }
        out.println();
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
