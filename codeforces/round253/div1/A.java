import java.util.*;
import java.io.*;
public class A {
    static int N;
    static char[] syms = new char[] {'R', 'G', 'B', 'Y', 'W', '1', '2', '3', '4', '5'};
    /*static String pos(String card) {
        char c = card.charAt(0);
        int i = 0; while (syms[i] != c) i++;
        int j = card.charAt(1) - '1';
        return 5 * i + j;
        }*/
    static int idx(char c) {
        int i = 0; while (syms[i] != c) i++;
        return i % 5;
    }
    static void solve() {
        N = in.nextInt();
        String[] a = new String[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.next();
        }
        int min = 10;
        for (int i = 0; i < 1024; i++) {
            if (ok(a, i)) {
                min = Math.min(min, Integer.bitCount(i));
            }
        }
        out.println(min);
    }
    static boolean ok(String[] a, int mask) {
        boolean[][] marked = new boolean[5][5];
        for (int i = 0; i < a.length; i++) {
            int j = idx(a[i].charAt(0));
            int k = idx(a[i].charAt(1));
            marked[j][k] = true;
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 5; j < 10; j++) {
                if ((mask & (1 << i)) > 0)
                    if ((mask & (1 << j)) > 0)
                        marked[i][j - 5] = false;
            }
        }
        int cnt = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (marked[i][j]) cnt++;
            }
        }
        boolean done = false;
        while (!done) {
            done = true;
            for (int i = 0; i < 10; i++) {
                if ((mask & (1 << i)) > 0) {
                    int cnt1 = 0;
                    if (i < 5) {
                        for (int j = 0; j < 5; j++) {
                            if (marked[i][j]) {
                                cnt1++;
                            }
                        }
                    } else {
                        for (int j = 0; j < 5; j++) {
                            if (marked[j][i - 5]) {
                                cnt1++;
                            }
                        }
                    }
                    if (cnt1 == 1) {
                        cnt--;
                        done = false;
                        if (i < 5) {
                            for (int j = 0; j < 5; j++) {
                                marked[i][j] = false;
                            }
                        } else {
                            for (int j = 0; j < 5; j++) {
                                marked[j][i - 5] = false;
                            }
                        }
                    }
                }
            }
        }
        return cnt <= 1;
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
