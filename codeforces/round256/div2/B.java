import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        char[] s = in.next().toCharArray();
        char[] t = in.next().toCharArray();
        char[][] st = new char[][] {s, t};
        int[][] cnt = new int[2][26];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < st[i].length; j++) {
                cnt[i][(int) (st[i][j] - 'a')]++;
            }
        }
        if (s.length > t.length) {
            int si, ti;
            si = ti = 0;
            while (ti < t.length) {
                while (si < s.length && t[ti] != s[si]) si++;
                if (si == s.length) {
                    // if possible o(2) else o(3)
                    for (int i = 0; i < 26; i++) {
                        if (cnt[0][i] < cnt[1][i]) o(3);
                    }
                    o(2);
                }
                si++;
                ti++;
            }
            o(0);
        }
        if (s.length < t.length) o(3);
        // if array o(1) else o(3)
        for (int i = 0; i < 26; i++) {
            if (cnt[0][i] < cnt[1][i]) o(3);
        }
        o(1);
    }
    static void o(int i) {
        String[] res = new String[] {"automaton", "array", "both", "need tree"};
        out.println(res[i]);
        out.close();
        System.exit(0);
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
