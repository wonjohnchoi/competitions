import java.util.*;
import java.io.*;
public class D {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N, M;
        N = in.nextInt();
        M = in.nextInt();
        // boolean[][] conn = new boolean[N][N];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < M; i++) {
            int a, b;
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;
            // conn[a][b] = true;
            adj.get(a).add(b);
        }
        int[][] cnt = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j : adj.get(i)) {
                for (int k : adj.get(j)) {
                    cnt[i][k]++;
                }
            }
        }
        long tot = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    tot += cnt[i][j] * (cnt[i][j] - 1) / 2;
                }
            }
        }
        out.println(tot);
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
