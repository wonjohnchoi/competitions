import java.util.*;
import java.io.*;
public class A {
    static void solve() {
        int N = in.nextInt();
        int[][] se = new int[N][2];
        int sei = 0;
        int pa = -1;
        se[0][0] = 0;
        int[] aa = new int[N];
        for (int i = 0; i < N; i++) {
            int a = in.nextInt();
            aa[i] = a;
            if (pa < a) {
                pa = a;
            } else {
                pa = a;
                se[sei++][1] = i - 1;
                se[sei][0] = i;
            }
        }
        se[sei++][1] = N - 1;
        boolean[] con = new boolean[sei - 1];
        for (int i = 0; i < sei - 1; i++) {
            boolean good = se[i + 1][1] - se[i + 1][0] + 1 == 1;
            good |= se[i][1] - se[i][0] + 1 == 1;
            if (good) {
                con[i] = true;
                //out.println(true);
                continue;
            }
            good |= aa[se[i][1] - 1] < aa[se[i + 1][0]] - 1;
            good |= aa[se[i][1]] + 1 < aa[se[i + 1][0] + 1];
            con[i] = good;
            //out.println(con[i]);
        }
        int max = -1;
        for (int i = 0; i < sei; i++) {
            int size = se[i][1] - se[i][0] + 1;
            if (size != N) size++;
            max = Math.max(max, size);
            //out.println(se[i][0] + " " + se[i][1]);
        }
        for (int i = 0; i < sei - 1; i++) {
            if (con[i])
                max = Math.max(max, se[i + 1][1] - se[i + 1][0] + 1 + se[i][1] - se[i][0] + 1);
        }
        out.println(max);
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
