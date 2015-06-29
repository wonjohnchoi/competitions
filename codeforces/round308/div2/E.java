import java.util.*;
import java.io.*;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class E {
    static long[][] cache;
    static int N;
    static String expr;
    static long eval(int l, int r) {
        if (l == r) return (int) (expr.charAt(l) - '0');
        if (l > r) return 0;
        if (cache[l][r] == 0) {
            int oi = -1;
            outer : for (char oper : new char[] { '+', '*' }) {
                oi = l + 1;
                while (oi < r) {
                    if (expr.charAt(oi) == oper) {
                        break outer;
                    }
                    oi += 2;
                }
            }
            long v1 = eval(l, oi - 1);
            long v2 = eval(oi + 1, r);
            long v;
            if (expr.charAt(oi) == '+') {
                v = v1 + v2;
            } else {
                v = v1 * v2;
            }
            cache[l][r] = v;
        }
        out.println(expr.substring(l, r + 1) + " " + cache[l][r]);
        return cache[l][r];
    }
    static void solve() {
        expr = in.next();
        N = expr.length();
        cache = new long[N][N];
        long best = 0;
        for (int i = 0; i < N; i += 2) {
            for (int j = i; j < N; j += 2) {
                long v1 = eval(0, i - 2);
                long v2 = eval(i, j);
                long v3 = eval(j + 2, N - 1);
                char o1 = i - 1 >= 0 ? expr.charAt(i - 1) : '?';
                char o2 = j + 1 < N ? expr.charAt(j + 1) : '?';
                long cur = 0;
                if (o1 == '?') {
                    if (o2 == '?') {
                        cur = v2;
                    } else if (o2 == '+') {
                        cur = v2 + v3;
                    } else {
                        cur = v2 * v3;
                    }
                } else if (o1 == '+') {
                    if (o2 == '?') {
                        cur = v1 + v2;
                    } else if (o2 == '+') {
                        cur = v1 + v2 + v3;
                    } else {
                        cur = v1 + v2 * v3;
                    }
                } else {
                    if (o2 == '?') {
                        cur = v1 * v2;
                    } else if (o2 == '+') {
                        cur = v1 * v2 + v3;
                    } else {
                        cur = v1 * v2 * v3;
                    }
                }
                out.println(i + " " + j + " " + cur + " " + v1 + " " + v2 + " " + v3);
                best = Math.max(best, cur);
            }
        }
        out.println(best);
    }

    // a wrong attempt
    static void solve2() {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        String expr = "1*" + in.next() + "*1";
        int si = 1;
        int sj;
        long best = get(engine, expr);

        outer : for ( ; ; ) {
            sj = si + 2;
            for (; ; sj += 2) {
                if (sj >= expr.length()) {
                    break outer;
                }
                if (expr.charAt(sj) == '*') {
                    break;
                }
            }
            // si ~ sj
            String nexpr = expr.substring(0, si + 1)
                + "(" + expr.substring(si + 1, sj) + ")"
                + expr.substring(sj);
            // out.println(nexpr);
            best = Math.max(best, get(engine, nexpr));
            si = sj;
        }
        out.println(best);
    }
    static long get(ScriptEngine engine, String nexpr) {
        try {
            Object res = engine.eval(nexpr);
            // out.println(res);
            return (long) (double) ((Double) res);
        } catch (Exception ex) {
            out.println(ex);
        }
        return -1;
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
