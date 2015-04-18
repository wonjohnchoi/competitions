import java.util.*;
import java.io.*;
public class B {
    static HashSet<Integer> preprocess(char[] ptrn) { // O(M)
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
        HashSet<Integer> c = new HashSet<>();
        i = ptrn.length;
        while (b[i] != -1) {
            c.add(b[i]);
            // out.println(b[i]);
            i = b[i];
        }
	return c;
    }
    static void solve() {
        int N, M;
        N = in.nextInt();
        M = in.nextInt();
        String P = in.next();
        int[] a = new int[M];
        for (int i = 0; i < M; i++) {
            a[i] = in.nextInt() - 1;
        }
        long MOD = (long) 1e9 + 7;
        HashSet<Integer> processed = preprocess(P.toCharArray());
        int ai = 0;
        int b = 0;
        long tot = 1;
        for (int i = 0; i < N; i++) {
            if (ai < M && a[ai] == i) {
                if (b > a[ai] && !processed.contains(b - a[ai])) {
                    out.println(0);
                    return;
                }
                b = a[ai] + P.length();
                ai++;
            }
            if (i >= b) {
                tot = (tot * 26) % MOD;
            }
        }
        out.println(tot);
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
