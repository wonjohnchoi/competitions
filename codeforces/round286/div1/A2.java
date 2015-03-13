import java.util.*;
import java.io.*;
public class A2 {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N = in.nextInt();
        int J = in.nextInt();
        int minJ = Math.max(0, J - 256);
        int maxJ = J + 256;
        int[][] cache = new int[30001][maxJ - minJ + 1];
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }
        int[] dia = new int[30001];
        for (int i = 0; i < N; i++) {
            int p = in.nextInt();
            dia[p]++;
        }
        cache[J][J - minJ] = dia[J];
        int best = dia[J];
        for (int i = 0; i < cache.length; i++) {
            for (int j = 1; j < cache[0].length; j++) {
                if (cache[i][j] != -1) {
                    for (int dj = -1; dj <= 1; dj++) {
                        int nj = j + dj;
                        int nd = i + j + dj + minJ;
                        if (nd < cache.length && nj >= 1) {
                            cache[nd][nj] = Math.max(cache[nd][nj], cache[i][j] + dia[nd]);
                            best = Math.max(cache[nd][nj], best);
                        }
                    }
                }
            }
        }
        out.println(best);
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
