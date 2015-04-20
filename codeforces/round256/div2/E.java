import java.util.*;
import java.io.*;
public class E {
    static HashMap<Long, List<Long>> divMap;
    static List<Long> divs;
    static long x, k;
    static void solve() {
        x = in.nextLong();
        k = in.nextLong();
        if (x == 1) {
            out.println(1);
            return;
        }
        divMap = new HashMap<>();
        divs = new ArrayList<>();
        for (long i = 1; i * i <= x; i++) {
            if (x % i == 0) {
                divs.add(i);
                if (i * i != x)
                    divs.add(x / i);
            }
        }
        Collections.sort(divs);
        for (long div : divs) {
            List<Long> divs2 = new ArrayList<>();
            for (long j = 1; j * j <= div; j++) {
                if (div % j == 0) {
                    divs2.add(j);
                    if (j * j != div)
                        divs2.add(div / j);
                }
            }
            Collections.sort(divs2);
            divMap.put(div, divs2);
        }
        // d(divs.size() + "");
        List<Long> res = new ArrayList<>();
        o(x, 0, res);
        p(res);
    }
    static void o(long cur, long depth, List<Long> res) {
        if (res.size() == 100000) return;
        if (depth == k || cur == 1) {
            res.add(cur);
            return;
        }
        for (long div : divMap.get(cur)) {
            o(div, depth + 1, res);
        }
    }
    static void p(List<Long> res) {
        for (int i = 0; i < res.size(); i++) {
            if (i != 0) out.print(" ");
            out.print(res.get(i));
        }
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
