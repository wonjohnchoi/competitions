import java.util.*;
import java.io.*;
public class A {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N = in.nextInt();
        int[] keys = new int[26];
        String S = in.next();
        int tot = 0;
        // out.println(N + " " + S);
        for (int i = 0; i < N - 1; i++) {
            // out.println(S + " " + i);
            int key = (int) S.charAt(i * 2) - 'a';
            keys[key]++;
            int door = (int) S.charAt(i * 2 + 1) - 'A';
            if (keys[door] > 0) {
                keys[door]--;
            } else tot++;
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
