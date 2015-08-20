import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        char[] c1, c2;
        c1 = in.next().toCharArray();
        c2 = in.next().toCharArray();
        int SZ = c1.length;
        int cur = SZ;
        String[] s1, s2;
        while (cur % 2 == 0) cur /= 2;
        s1 = new String[SZ / cur];
        s2 = new String[SZ / cur];
        for (int i = 0; i < s1.length; i++) {
            s1[i] = new String(c1, i * cur, cur);
            s2[i] = new String(c2, i * cur, cur);
        }
        int sc = 1;
        while (cur <= SZ) {
            int num = SZ / cur;
            String[] g1 = new String[num];
            String[] g2 = new String[num];

            for (int start = 0; start < s1.length; start += sc) {
                int end = start + sc;
                Arrays.sort(s1, start, end);
                Arrays.sort(s2, start, end);
                StringBuilder sb1 = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                for (int i = start; i < end; i++) {
                    sb1.append(s1[i]);
                    sb2.append(s2[i]);
                }
                g1[start / sc] = sb1.toString();
                g2[start / sc] = sb2.toString();
            }
            Arrays.sort(g1);
            Arrays.sort(g2);
            /*
            out.println(Arrays.toString(g1));
            out.println(Arrays.toString(g2));
            out.flush();*/
            //if (sc == 2)System.exit(0);
            for (int i = 0; i < g1.length; i++) {
                if (!g1[i].equals(g2[i])) {
                    out.println("NO");
                    return;
                }
            }
            sc *= 2; cur *= 2;
        }
        out.println("YES");
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
