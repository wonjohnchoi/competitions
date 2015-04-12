import java.util.*;
import java.io.*;
public class A {
    static void solve() {
        int N = in.nextInt();
        List<Integer> res = new ArrayList<>();
        if (N == 1) res.add(1);
        else if (N == 2) res.add(1);
        else if (N == 3) {
            res.add(1); res.add(3);
        } else if (N >= 4) {
            res.add(3); res.add(1); res.add(4); res.add(2);
            for (int i = 5; i <= N; i++) {
                if (i % 2 == 1) res.add(i);
                else res.add(0, i);
            }
        }
        out.println(res.size());
        for (int i = 0; i < res.size(); i++) {
            if (i != 0) out.print(" ");
            out.print(res.get(i));
        }
        out.println();
    }
    static PrintWriter out = new PrintWriter(System.out);
    static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        solve();
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
