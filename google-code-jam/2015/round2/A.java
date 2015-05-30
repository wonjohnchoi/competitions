import java.util.*;
import java.io.*;
public class A {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    static int TC;
    static String solve(int tc) {
        int R = in.nextInt();
        int C = in.nextInt();
        char[][] b = new char[R][C];
        for (int i = 0; i < R; i++) {
            b[i] = in.next().toCharArray();
        }
        int[][] dirs = new int[][] {
            {1, 0}, {0, 1}, {-1, 0}, {0, -1}
        };
        int cnt = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int[] dir1 = new int[2];
                if (b[i][j] == '^') {
                    dir1[0] = -1;
                    dir1[1] = 0;
                } else if (b[i][j] == '<') {
                    dir1[0] = 0;
                    dir1[1] = -1;
                } else if (b[i][j] == 'v') {
                    dir1[0] = 1;
                    dir1[1] = 0;
                } else if (b[i][j] == '>') {
                    dir1[0] = 0;
                    dir1[1] = 1;
                } else {
                    continue;
                }
                int ni = i + dir1[0];
                int nj = j + dir1[1];
                boolean good = false;
                while (0 <= ni && ni < R && 0 <= nj && nj < C) {
                    if (b[ni][nj] != '.') {
                        good = true; break;
                    }
                    ni += dir1[0];
                    nj += dir1[1];
                }
                if (good) continue;
                good = false;
                for (int[] dir : dirs) {
                    ni = i + dir[0];
                    nj = j + dir[1];
                    while (0 <= ni && ni < R && 0 <= nj && nj < C) {
                        if (b[ni][nj] != '.') {
                            good = true; break;
                        }
                        ni += dir[0];
                        nj += dir[1];
                    }
                    if (good) break;
                }
                if (good) cnt++;
                else {
                    return "IMPOSSIBLE";
                }
                // check if all dot
            }
        }
        return cnt + "";
    }
    public static void main(String args[]) {
        TC = in.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            out.printf("Case #%d: %s\n", tc, solve(tc));
        }
        out.close();
    }
    static void d(Object o) {
        out.println(o);
        out.flush();
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
