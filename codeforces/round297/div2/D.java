import java.util.*;
import java.io.*;
public class D {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    static class Dim implements Comparable<Dim> {
        boolean visited = false;
        List<Dim> near = new ArrayList<Dim>();
        int minX, minY, maxX, maxY;
        Dim(int minX, int minY, int maxX, int maxY) {
            this.minX = minX;
            this.minY = minY;
            this.maxX = maxX;
            this.maxY = maxY;
        }
        boolean intersect(Dim d) {
            return (in(minX, d.minX, d.maxX) || in(maxX, d.minX, d.maxX))
                && (in(minY, d.minY, d.maxY) || in(maxY, d.minY, d.maxY));
        }
        void merge(Dim d) {
            minX = Math.min(minX, d.minX);
            maxX = Math.max(maxX, d.maxX);
            minY = Math.min(minY, d.minY);
            maxY = Math.max(maxY, d.maxY);
        }
        static boolean in(int x, int x2, int x3) {
            return x2 <= x && x <= x3;
        }
        public int compareTo(Dim d) {
            return minX - d.minX;
        }
    }
    public static void main(String args[]) {
        int N, M;
        N = in.nextInt();
        M = in.nextInt();
        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = in.next().toCharArray();
        }
        Dim[][] dim = new Dim[N][M];

        List<Dim> dims = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            outer : for (int j = 0; j < M; j++) {
                if (board[i][j] == '.' && dim[i][j] == null) {
                    Dim cdim = new Dim(j, i, j, i);
                    // out.println(i + " " + j + " " + cdim.minX + " " +cdim.maxX + " " + cdim.minY + " " + cdim.maxY);
                    dfs(i, j, dim, board, cdim);
                    //out.println(i + " " + j + " " + cdim.minX + " " +cdim.maxX + " " + cdim.minY + " " + cdim.maxY);
                    dims.add(cdim);
                }
            }
        }
        for (int i = 0; i < dims.size(); i++) {
            for (int j = 0; j < dims.size(); j++) {
                if (dims.get(i).intersect(dims.get(j))) {
                    dims.get(i).near.add(dims.get(j));
                }
            }
        }
        for (int i = 0; i < dims.size(); i++) {
            Dim d = dims.get(i);
            if (!d.visited) {
                dfs2(d);
                for (int x = d.minX; x <= d.maxX ; x++) {
                    for (int y = d.minY; y <= d.maxY ; y++) {
                        board[y][x] = '.';
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            out.println(new String(board[i]));
        }
        out.close();
    }
    static Dim dfs2(Dim cur) {
        cur.visited = true;
        for (Dim nd : cur.near) {
            if (!nd.visited) {
                Dim nr = dfs2(nd);
                if (cur.intersect(nr)) {
                    cur.merge(nr);
                }
            }
        }
        return cur;
    }
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static void dfs(int i, int j, Dim[][] dim, char[][] board, Dim cdim) {
        // out.println(i + " " + j);
        dim[i][j] = cdim;
        cdim.minY = Math.min(cdim.minY, i);
        cdim.maxY = Math.max(cdim.maxY, i);
        cdim.minX = Math.min(cdim.minX, j);
        cdim.maxX = Math.max(cdim.maxX, j);
        for (int[] delta : deltas) {
            int ni = delta[0] + i;
            int nj = delta[1] + j;
            if (inRange(ni, nj, board) && board[ni][nj] == '.' && dim[ni][nj] == null) {
                dfs(ni, nj, dim, board, cdim);
            }
        }
    }
    static boolean inRange(int i, int j, char[][] board) {
        return i >= 0 && j >= 0 && i < board.length && j < board[0].length;
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
