import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N, M;
        N = in.nextInt();
        M = in.nextInt();
        int[][] b = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                b[i][j] = in.nextInt();
            }
        }
        int[][] a = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                boolean ok = true;
                for (int k = 0; k < N; k++) {
                    if (b[k][j] != 1) ok = false;
                }
                for (int k = 0; k < M; k++) {
                    if (b[i][k] != 1) ok = false;
                }
                if (ok) a[i][j] = 1;
            }
        }
        boolean yes = true;
        int[][] c = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                boolean ok = false;
                for (int k = 0; k < N; k++) {
                    if (a[k][j] == 1) ok = true;
                }
                for (int k = 0; k < M; k++) {
                    if (a[i][k] == 1) ok = true;
                }
                if (ok) c[i][j] = 1;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (c[i][j] != b[i][j]) {
                    yes = false;
                }
            }
        }
        if (!yes) {
            out.println("NO");
        } else {
            out.println("YES");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (j != 0) out.print(" ");
                    out.print(a[i][j]);
                }
                out.println();
            }
        }
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
