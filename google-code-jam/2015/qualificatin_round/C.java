import java.util.*;
import java.io.*;
public class C {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    static class Qua {
        String q;
        Qua(String q) {
            this.q = q;
        }
        static String[][] res = new String[][] {
            {"1", "i", "j", "k"},
            {"i", "-1", "k", "-j"},
            {"j", "-k", "-1", "i"},
            {"k", "j", "-i", "-1"}
        };
        int val() {
            char q2 = q.length() == 2 ? q.charAt(1) : q.charAt(0);
            if (q2 == '1') return 0;
            if (q2 == 'i') return 1;
            if (q2 == 'j') return 2;
            if (q2 == 'k') return 3;
            return -1;
        }
        Qua mult(Qua qua) {
            // out.println(q + " " + qua.q);
            // out.println(val() + " " + qua.val());
            // out.flush();
            int minus = 1;
            if (q.length() >= 2) minus *= -1;
            if (qua.q.length() >= 2) minus *= -1;
            String ret = res[val()][qua.val()];
            if (minus == -1 && ret.length() >= 2) return new Qua(ret.substring(1));
            if (minus == -1) return new Qua("-" + ret);
            return new Qua(ret);
        }
        public String toString() {
            return q;
        }
    }
    public static void main(String args[]) {
        int TC = in.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            String ans = "";
            int L = in.nextInt();
            long X = in.nextLong();
            String LS = in.next();
            Qua[] quas = new Qua[L];
            Qua tot = new Qua("1");
            for (int i = 0; i < L; i++) {
                quas[i] = new Qua(LS.charAt(i) + "");
                tot = tot.mult(quas[i]);
                // out.println(tot);
            }
            Qua tot2 = new Qua(tot.q);
            for (int i = 0; i < (X - 1) % 4; i++) {
                tot = tot.mult(tot2);
            }
            long cnt = 0;
            Qua q1 = null;
            Qua q2 = null;
            Qua q = new Qua("1");
            outer : for (int i = 0; i < 100; i++) {
                for (int j = 0; j < L; j++) {
                    q = q.mult(quas[j]);
                    cnt++;
                    if (q1 == null && q.q.equals("i")) {
                        q1 = q;
                        q = new Qua("1");
                    }
                    if (q1 != null && q2 == null && q.q.equals("j")) {
                        q2 = q;
                        break outer;
                    }
                }
            }
            // out.println(q1 + " " + q2 + " " + tot + " " + cnt);
            if (q1 != null && q2 != null && tot.q.equals("-1") && cnt < L * X) {
                ans = "YES";
            } else {
                ans = "NO";
            }
            out.printf("Case #%d: %s\n", tc, ans);
        }
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
