import java.util.*;
import java.io.*;
public class B {
    static void solve() {
        int[] mh = new int[2];
        int tw = 0;
        int N = in.nextInt();
        int[] ww, hh;
        ww = new int[N];
        hh = new int[N];
        for (int i = 0; i < N; i++) {
            int w = in.nextInt();
            int h = in.nextInt();
            ww[i] = w;
            hh[i] = h;
            tw += w;
            if (mh[0] < h) {
                mh[1] = mh[0];
                mh[0] = h;
            } else if (mh[1] < h) {
                mh[1] = h;
            }
        }
        for (int i = 0; i < N; i++) {
            int w = ww[i];
            int h = hh[i];
            int nw = tw - w;
            int nh = mh[0] == hh[i] ? mh[1] : mh[0];
            if (i != 0) out.print(" ");
            out.print(nw * nh);
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
