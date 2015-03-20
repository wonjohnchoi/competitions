import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        long N = in.nextLong();
        List<Long>[] starts = new List[3];
        for (int i = 0; i < 3; i++) {
            starts[i] = new ArrayList<>();
        }
        long i = 2;
        long di = 5;
        while (i <= N) {
            starts[(int) (i % 3)].add(i);
            i += di;
            di += 3;
        }
        List<Long> starts2 = starts[(int) (N % 3)];
        int k = Collections.binarySearch(starts2, N);
        if (k >= 0) k++;
        else k = -(k + 1);
        out.println(k);
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
