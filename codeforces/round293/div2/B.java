import java.util.*;
import java.io.*;
public class B {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        String[] ST = new String[] { in.next(), in.next() };
        int[][][] vals = new int[2][2][26]; // s or t, small or big, alphas
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < ST[i].length(); j++) {
                char c = ST[i].charAt(j);
                if ('a' <= c && c <= 'z') vals[i][0][c - 'a']++;
                else vals[i][1][c - 'A']++;
            }
        }
        int t0, t1;
        t0 = t1 = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 2; j++) {
                int a = vals[0][j][i];
                int b = vals[1][j][i];
                int c = Math.min(a, b);
                t0 += c;
                vals[0][j][i] -= c;
                vals[1][j][i] -= c;
            }
        }
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 2; j++) {
                int a = vals[0][j][i];
                int b = vals[1][(j + 1) % 2][i];
                int c = Math.min(a, b);
                t1 += c;
                vals[0][j][i] -= c;
                vals[1][(j + 1) % 2][i] -= c;
            }
        }
        out.println(t0 + " " + t1);
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
