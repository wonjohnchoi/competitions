import java.util.*;
import java.io.*;
public class A {
    static void solve() {
        int M = in.nextInt();
        HashMap<String, Integer> chainLength = new HashMap<>();
        chainLength.put("POLYCARP", 1);
        int max = 1;
        for (int i = 0; i < M; i++) {
            String s1 = in.next().toUpperCase();
            in.next();
            String s2 = in.next().toUpperCase();
            int a = chainLength.get(s2) + 1;
            chainLength.put(s1, a);
            max = Math.max(max, a);
        }
        out.println(max);
    }
    static PrintWriter out = new PrintWriter(System.out);
    static InputReader in = new InputReader(System.in);
    static void d(Object o) {
        out.println(o);
        out.flush();
    }
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
