import java.util.*;
import java.io.*;
public class D {
    static class Pair implements Comparable<Pair> {
        int x, y;
        Pair(int xx, int yy) {
            x = xx;
            y = yy;
        }
        public int compareTo(Pair p) {
            int r = Integer.compare(x, p.x);
            if (r == 0) r = Integer.compare(y, p.y);
            return r;
        }
    }
    static int N;
    static boolean rangeCheck(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
    static void solve() {
        N = in.nextInt();
        char[][] b = new char[N][N];
        for (int i = 0; i < N; i++) {
            b[i] = in.next().toCharArray();
        }
        TreeSet<Pair> ts = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (b[i][j] == 'o') {
                    if (ts == null) {
                        ts = new TreeSet<>();
                        for (int k = i - N + 1; k < i + N; k++) {
                            for (int l = j - N + 1; l < j + N; l++) {
                                if (!rangeCheck(k, l) || b[k][l] == 'x' || b[k][l] == 'o') {
                                    int dx = k - i;
                                    int dy = l - j;
                                    // out.println(i + " " + j + " " + dx + " " + dy);
                                    ts.add(new Pair(dx, dy));
                                }
                            }
                        }
                    } else {
                        for (Iterator<Pair> it = ts.iterator(); it.hasNext(); ) {
                            Pair p = it.next();
                            int x = i + p.x;
                            int y = j + p.y;
                            if (rangeCheck(x, y) && b[x][y] == '.') {
                                it.remove();
                            }
                        }
                    }
                }
            }
        }
        char[][] b3 = makeB(N);
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < N; l++) {
                if (b[k][l] == 'o') b3[k][l] = 'o';
            }
        }
        for (Pair p : ts) {
            // out.println(p.x + " " + p.y);
            int dx = p.x;
            int dy = p.y;
            fill(b3, dx, dy);
        }
        // out.println(dx + " " + dy);
        // printB(b3);
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < N; l++) {
                if (b[k][l] != b3[k][l]) {
                    out.println("NO");
                    return;
                }
            }
        }
        char[][] b2 = makeB(2 * N - 1);
        b2[N - 1][N - 1] = 'o';
        for (Pair p : ts) {
            int dx = p.x;
            int dy = p.y;
            // out.println(dx + " " + dy);
            fill(b2, dx, dy);
        }
        out.println("YES");
        printB(b2);
    }
    static void printB(char[][] b) {
        for (int i = 0; i < b.length; i++) {
            out.println(new String(b[i]));
        }
    }
    static void put(char[][] b, int x, int y) {
        if (0 <= x && x < b.length && 0 <= y && y < b[0].length) {
            if (b[x][y] == '.') b[x][y] = 'x';
        }
    }
    static void fill(char[][] b, int dx, int dy) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (b[i][j] == 'o') {
                    put(b, i + dx, j + dy);
                    /*
                    put(b, i + dx, j - dy);
                    put(b, i - dx, j + dy);
                    put(b, i - dx, j - dy);
                    put(b, i + dy, j + dx);
                    put(b, i + dy, j - dx);
                    put(b, i - dy, j + dx);
                    put(b, i - dy, j - dx);*/
                }
            }
        }
    }
    static char[][] makeB(int n) {
        char[][] b = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(b[i], '.');
        }
        return b;
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
