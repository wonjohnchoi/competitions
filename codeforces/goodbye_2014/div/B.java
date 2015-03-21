import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        N = in.nextInt();
        a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt() - 1;
        }
        con = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String s = in.next();
            for (int j = 0; j < N; j++) {
                con[i][j] = s.charAt(j) == '1';
            }
        }
        for (int i = 0; i < N - 1; i++) {
            minIdx = -1;
            dfs(i, i, new boolean[N]);
            if (minIdx < i || a[minIdx] > a[i]) continue;
            // out.println(i + " " + minIdx);
            int tmp = a[i];
            a[i] = a[minIdx];
            a[minIdx] = tmp;
        }
        for (int i = 0; i < N; i++) {
            if (i != 0) out.print(" ");
            out.print(a[i] + 1);
        }
        out.println();
    }
    static int[] a;
    static boolean[][] con;
    static void dfs(int s, int cur, boolean[] marked) {
        if (marked[cur]) return;
        marked[cur] = true;
        if (s < cur && (minIdx == -1 || a[minIdx] > a[cur])) {
            minIdx = cur;
        }
        for (int i = 0; i < N; i++) {
            if (con[cur][i]) dfs(s, i, marked);
        }
    }
    static int minIdx;
    static int N;
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
