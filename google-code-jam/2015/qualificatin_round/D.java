import java.util.*;
import java.io.*;
public class D {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    static int X, R, C;
    public static void main(String args[]) {
        int TC = in.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            String ans = "";
            X = in.nextInt();
            R = in.nextInt();
            C = in.nextInt();
            if (R > C) {
                int tmp = R;
                R = C;
                C = tmp;
            }
            boolean win = false;
            if ((R * C) % X != 0) win = true;
            for (int i = 1; i <= X; i++) {
                int j = X - i + 1;
                if (i > j) break;
                if (R < i || C < j) {
                    win = true;
                } else if (R == i && C == j) {
                } else if (R == i) {
                    // out.println(R + " " + C + " " + j + " " + f(R, C, j));
                    if (f(R, C, j)) win = true;
                } else if (C == j) {
                    // out.println(C + " " + R + " " + i + " " + f(C, R, i));
                    if (f(C, R, i)) win = true;
                }
            }
            if (win) ans = "RICHARD";
            else ans = "GABRIEL";
            out.printf("Case #%d: %s\n", tc, ans);
        }
        out.close();
    }
    static boolean f(int same, int R, int i) {
        for (int lb = 0; lb <= (i - 1) * (same - 1); lb++) {
            boolean win = true;
            for (int j = 0; j <= R - i; j++) {
                if (j == 0) {
                    if ((j * same + lb) % X == 0) win = false;
                } else if (j == R - i) {
                    if ((j * same + lb) % X == 0) win = false;
                } else {
                    if ((j * same + lb) % X == 0) win = false;
                }
            }
            if (win) return true;
        }
        return false;
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
