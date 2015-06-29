import java.util.*;
import java.io.*;
public class A {
    static void solve() {
        int N = in.nextInt();
        int tot = 0;
        int M = in.nextInt();
        int L = 1;
        for (int i = 0; i < M; i++) {
            int K = in.nextInt();
            int first = in.nextInt();
            int j = 1;
            if (first == 1) {
                while (j < K) {
                    int a = in.nextInt();
                    j++;
                    if (a == L + 1) L = a;
                    else break;
                }
            }
            while (j < K) {
                in.nextInt();
                j++;
            }
            /*
            for (int j = 0; j < K; j++) {
                int a = in.nextInt();
                if (pre + 1 != a) {
                    if (pre != -1) {
                        cur++;
                    }
                    pre = a;
                } else {
                    pre = a;
                }
            }
            cur++;*/
            //out.println(cur);
            /*
            tot += cur - 1;
            tot += cur;
            */
            tot += K - 1;
        }
        tot -= L - 1;
        tot += N - L;
        out.println(tot);
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
