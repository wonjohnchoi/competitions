import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        long A, B, C;
        A = in.nextLong();
        B = in.nextLong();
        C = in.nextLong();
        List<Long> sol = new ArrayList<>();
        for (long i = 1; i <= 81; i++) {
            long x = B * (long) Math.pow(i, A) + C;
            if (x <= 0 || x >= (long) 1e9) continue;
            long digitSum = 0;
            String s = x + "";
            for (int j = 0; j < s.length(); j++) {
                digitSum += s.charAt(j) - '0';
            }
            if (digitSum == i) sol.add(x);
        }
        Collections.sort(sol);
        out.println(sol.size());
        if (sol.size() == 0) return;
        for (int j = 0; j < sol.size(); j++) {
            if (j != 0) out.print(" ");
            out.print(sol.get(j));
        }
        out.println();
    }
    static PrintWriter out = new PrintWriter(System.out);
    static InputReader in = new InputReader(System.in);
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
    /*
    public int DONTUSEnextInt() {
	return Integer.parseInt(next());
        }*/
}
