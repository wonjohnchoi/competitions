import java.util.*;
import java.io.*;
public class D {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static int[] preprocess(long[] ptrn) { // O(M)
	int i = 0, j = -1;
	int[] b = new int[ptrn.length + 1];
	b[i] = j;
	while (i < ptrn.length) {
	    while (j >= 0 && ptrn[i] != ptrn[j]) {
		j = b[j];
	    }
	    i++;
	    j++;
	    b[i] = j;
	}
	return b;
    }
    static long search(long[] text, long[] ptrn) { // O(N)
        long cnt = 0;
	int i = 0, j = 0;
	int[] b = preprocess(ptrn);
	while (i < text.length) {
	    while (j >= 0 && text[i] != ptrn[j]) {
		j = b[j];
	    }
	    i++;
	    j++;
	    if (j == ptrn.length) {
                // out.println(i - ptrn.length);
                cnt++;
		j = b[j];
	    }
	}
        return cnt;
    }
    public static void main(String args[]) {
        int N, W;
        N = in.nextInt();
        W = in.nextInt();
        long[] text = new long[N - 1];
        long[] ptrn = new long[W - 1];
        long s = in.nextLong();
        for (int i = 0; i < N - 1; i++) {
            long s2 = in.nextLong();
            text[i] = s2 - s;
            s = s2;
        }
        s = in.nextLong();
        for (int i = 0; i < W - 1; i++) {
            long s2 = in.nextLong();
            ptrn[i] = s2 - s;
            s = s2;
        }
        if (W == 1) {
            out.println(N);
        } else
            out.println(search(text, ptrn));
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
