import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int[][] a = new int[8][3];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = in.nextInt();
            }
        }
        find(a, new int[8][3], 0);
        out.println("NO");
    }
    static void find2(int[][] cands, int ii, int[] c, int[][] b, boolean[] marked) {
        // cands : 3 by 2
        if (ii == 3) {
            outer : for (int i = 0; i < 8; i++) {
                if (!marked[i]) {
                    for (int j = 0; j < 3; j++) {
                        if (c[j] != b[i][j]) continue outer;
                    }
                    marked[i] = true;
                    break;
                }
            }
            return;
        }
        for (int i = 0; i < 2; i++) {
            c[ii] = cands[ii][i];
            find2(cands, ii + 1, c, b, marked);
        }
    }
    static void find(int[][] a, int[][] b, int ii) {
        if (ii == 8) {
            int[] cnt = new int[3];
            int[][] cands = new int[3][8];
            for (int i = 0; i < 3; i++) {
                outer : for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < cnt[i]; k++) {
                        if (cands[i][k] == b[j][i]) continue outer;
                    }
                    cands[i][cnt[i]++] = b[j][i];
                }
            }
            for (int i = 0; i < 3; i++) {
                if (cnt[i] != 2) return;
            }
            boolean[] marked = new boolean[8];
            find2(cands, 0, new int[3], b, marked);
            for (int i = 0; i < 8; i++) {
                if (!marked[i]) return;
            }
            out.println("YES");
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 3; j++) {
                    if (j != 0) out.print(" ");
                    out.print(b[i][j]);
                }
                out.println();
            }
            System.exit(0);
            return;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j != i) {
                    for (int k = 0; k < 3; k++) {
                        if (k != i && k != j) {
                            b[ii] = new int[] {a[ii][i], a[ii][j], a[ii][k]};
                            find(a, b, ii + 1);
                        }
                    }
                }
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
