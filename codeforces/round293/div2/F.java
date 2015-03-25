import java.util.*;
import java.io.*;
public class F {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    static char[][] board;
    static int N, M;
    public static void main(String args[]) {
        // char[2] turns, i, j. max two turns. l then r. r then l. l. r. none.
        N = in.nextInt();
        M = in.nextInt();
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = in.next().toCharArray();
        }
        int tot = 0;
        for (int i = 1; i < N - 1; i++) {
            tot += f(new char[2], 0, i, 0, 0, 1, 0);
            tot += f(new char[2], 0, i, M - 1, 0, -1, 0);
        }
        for (int i = 1; i < M - 1; i++) {
            tot += f(new char[2], 0, 0, i, 1, 0, 0);
            tot += f(new char[2], 0, N - 1, i, -1, 0, 0);
        }
        out.println(tot / 2);
        out.close();
    }
    static int f(char[] turns, int nturns, int r, int c, int dr, int dc, int cnt) {
        // out.println(r + " " + c);
        if (!range(r, c)) return 0;
        if (board[r][c] != '.') return 0;
        int ntouch = 0;
        if (r == 0) ntouch++;
        if (r == N - 1) ntouch++;
        if (c == 0) ntouch++;
        if (c == M - 1) ntouch++;
        if (ntouch >= 2) return 0; // touched corner
        if (cnt != 0 && ntouch == 1) { // touched edge second time
            out.println("DONE : " + r + " " + c);
            return 1;
        }
        int near = 0;
        for (int[] d : new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (range(nr, nc) && board[nr][nc] == '*') near++;
        }
        if (cnt != 0 && !(ntouch == 0 && near == 1)) {
            return 0;
        }
        out.println(r + " " + c + " " + dr + " " + dc);
        board[r][c] = '*';
        int tot = 0;
        tot += f(turns, nturns, r + dr, c + dc, dr, dc, cnt + 1);
        if (nturns < 2 && cnt > 0) {
            if (nturns == 0) {
                for (char cc : new char[] {'L', 'R'}) {
                    turns[0] = cc;
                    int ndr = dc;
                    int ndc = dr;
                    if (cc == 'L') ndr = -ndr;
                    if (cc == 'R') ndc = -ndc;
                    tot += f(turns, nturns + 1, r + ndr, c + ndc, ndr, ndc, cnt + 1);
                }
            } else {
                char cc = turns[0] == 'L' ? 'R' : 'L';
                turns[1] = cc;
                int ndr = dc;
                int ndc = dr;
                if (cc == 'L') ndr = -ndr;
                if (cc == 'R') ndc = -ndc;
                tot += f(turns, nturns + 1, r + ndr, c + ndc, ndr, ndc, cnt + 1);
            }
        }
        board[r][c] = '.';
        return tot;
    }
    static boolean range(int r, int c) {
        return !(r < 0 || c < 0 || r >= N || c >= M);
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
