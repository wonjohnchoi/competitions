import java.util.*;
import java.io.*;
public class C {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N, M, K;
        N = in.nextInt();
        M = in.nextInt();
        K = in.nextInt();
        int[] a = new int[N];
        int[] a2 = new int[N];
        for (int i = 0; i < N; i++) {
            int aa = in.nextInt() - 1;
            a[i] = aa;
            a2[aa] = i;
        }
        long tot = 0;
        int pid = 0;
        for (int i = 0; i < M; i++) {
            int b = in.nextInt() - 1;
            int j = a2[b];
            int npid = j / K;
            tot += Math.abs(pid - npid) + 1;
            pid = npid;
            if (j != 0) {
                // swap
                int tmp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = tmp;
                a2[a[j]] = j;
                a2[a[j - 1]] = j - 1;
            }
        }
        out.println(tot);
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
