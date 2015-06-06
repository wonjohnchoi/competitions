import java.util.*;
import java.io.*;
public class C {
    static void solve() {
        int N = in.nextInt();
        int K = in.nextInt();
        int e = 0;
        int o = 0;
        for (int i = 0; i < N; i++) {
            if (in.nextInt() % 2 == 0) e++;
            else o++;
        }
        int min = Math.min(e, o);
        if (N - K == 0) {
            win(wins(o));
        } else if ((N - K) % 2 == 0) {
            if (min > (N - K) / 2) {
                win(DARIUS);
            } else if (o == (N - K) / 2 && e == (N - K) / 2){
                win(DARIUS);
            } else {
                if (min == o) {
                    win(DARIUS);
                } else {
                    win(wins(o - (N - K - e)));
                }
            }
        } else {
            if (min > (N - K - 1) / 2) win(!DARIUS);
            else {
                if (min == o) win(DARIUS);
                else win(wins(o - (N - K - e)));
            }
        }
    }
    static boolean DARIUS = true;
    static void win(boolean winner) {
        if (winner == DARIUS) {
            out.println("Daenerys");
        } else {
            out.println("Stannis");
        }
        out.close();
        System.exit(0);
    }
    static boolean wins(int o) {
        if (o % 2 == 0) return DARIUS;
        return !DARIUS;
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
