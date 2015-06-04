import java.util.*;
import java.io.*;
public class C {
    static void solve() {
        int N = in.nextInt();
        HashSet<String> seen = new HashSet<>();
        String[] state = new String[2];
        for (int i = 0; i < 2; i++) {
            String s = "";
            int k = in.nextInt();
            for (int j = 0; j < k; j++) {
                s += in.nextInt() - 1;
            }
            state[i] = s;
        }
        int cnt = 0;
        while (!seen.contains(state[0] + "," + state[1])) {
            // out.println(Arrays.toString(state));
            seen.add(state[0] + "," + state[1]);
            // fight
            char[] heads = new char[2];
            for (int i = 0; i < 2; i++) {
                heads[i] = state[i].charAt(0);
                state[i] = state[i].substring(1);
            }
            int p = heads[0] > heads[1] ? 0 : 1;
            state[p] += heads[(p + 1) % 2] + "" + heads[p];
            cnt++;
            int win = 0;
            if (state[0].length() == 0) win = 2;
            if (state[1].length() == 0) win = 1;
            if (win > 0) {
                out.println(cnt + " " + win);
                return;
            }
        }
        out.println("-1");
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
