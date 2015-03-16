import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N, M;
        N = in.nextInt();
        M = in.nextInt();
        char[][] b = new char[N][M];
        for (int i = 0; i < N; i++) {
            b[i] = in.next().toCharArray();
        }
        int[] r = new int[N];
        int cnt = 0;
        line : for (int i = 0; i < M; i++) {
            int[] r2 = new int[N];
            for (int j = 0; j < N; j++) {
                r2[j] = r[j];
            }
            // out.println("line: " + i);
            for (int j = 0; j < N; j++) {
                if (j != 0 && r[j] == r[j - 1]) {
                    if (b[j][i] < b[j - 1][i]) {
                        // out.println("break at " + j);
                        cnt++;
                        continue line;
                    } else if (b[j][i] > b[j - 1][i]) {
                        // out.println("rank up at " + j);
                        for (int k = j; k < N; k++) {
                            r2[k]++;
                        }
                    }
                }
            }
            r = r2;
        }
        out.println(cnt);
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
