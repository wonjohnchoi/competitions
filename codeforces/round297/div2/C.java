import java.util.*;
import java.io.*;
public class C {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N = in.nextInt();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            a.add(in.nextInt());
        }
        Collections.sort(a);
        List<Integer> b = new ArrayList<>();
        for (int i = a.size() - 1; i >= 0;) {
            if (i - 1 < 0) break;
            int a2 = a.get(i);
            int a3 = a.get(i - 1);
            if (a2 == a3 || (a2 - 1) == a3) {
                b.add(a3);
                i -= 2;
            } else {
                i--;
            }
        }
        long tot = 0;
        for (int i = 0; i < b.size(); ) {
            if (i + 1 >= b.size()) break;
            int b2 = b.get(i);
            int b3 = b.get(i + 1);
            tot += (long) b2 * b3;
            i += 2;
        }
        out.println(tot);
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
