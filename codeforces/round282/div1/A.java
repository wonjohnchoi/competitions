import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        char[] b = in.next().toCharArray();
        int lb = 0;
        int rb = 0;
        int s = 0;
        for (int i = 0; i < b.length; i++) {
            if (b[i] == '(') lb++;
            else if (b[i] == ')') rb++;
            else {
                s++;
                rb++;
            }
            if (lb < rb) {
                NO();
            }
        }
        int[] res = new int[s];
        Arrays.fill(res, 1);
        if (lb != rb && s == 0) NO();
        res[s - 1] += lb - rb;
        lb = rb = s = 0;
        for (int i = 0; i < b.length; i++) {
            if (b[i] == '(') lb++;
            else if (b[i] == ')') rb++;
            else {
                rb += res[s];
                s++;
            }
            if (lb < rb) {
                NO();
            }
        }
        if (lb != rb) NO();
        for (int i = 0; i < res.length; i++) {
            out.println(res[i]);
        }
    }
    static void NO() {
        out.println(-1);
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
    public int nextInt() {
	return Integer.parseInt(next());
    }
}
