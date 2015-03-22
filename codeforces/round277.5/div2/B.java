import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static int N, M;
    static int[] a, b;
    // static boolean[][] conn; // N x M
    static int[][] f, c; // 2 + N + M, 2 + N + M
    static class Node implements Comparable<Node> {
        int v, i;
        Node(int v, int i) {
            this.v = v;
            this.i = i;
        }
        public int compareTo(Node n) {
            int r = v - n.v;
            if (r == 0) r = i - n.i;
            return r;
        }
    }
    public static void main(String args[]) {
        N = in.nextInt();
        a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        M = in.nextInt();
        b = new int[M];
        for (int i = 0; i < M; i++) {
            b[i] = in.nextInt();
        }
        Arrays.sort(a); Arrays.sort(b);
        int cnt = 0;
        int j = 0;
        TreeSet<Node> t = new TreeSet<Node>();
        for (int i = 0; i < N; i++) {
            while (j < M && b[j] <= a[i] + 1){
                t.add(new Node(b[j], j));
                j++;
            }
            t.headSet(new Node(a[i] - 1, -1)).clear();
            if (t.pollFirst() != null) cnt++;
        }
        out.println(cnt);

        /* wrong on input #37. maxflow..
        f = new int[N + M + 2][N + M + 2];
        c = new int[N + M + 2][N + M + 2]; // 0 = source, 1 = sink
        for (int i = 0; i < N; i++) {
            c[i + 2][0] = 1;
            c[0][i + 2] = 1;
        }
        for (int i = 0; i < M; i++) {
            c[i + N + 2][1] = 1;
            c[1][i + N + 2] = 1;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (Math.abs(a[i] - b[j]) <= 1) {
                    c[i + 2][j + N + 2] = 1;
                    c[j + N + 2][i + 2] = 1;
                }
            }
        }
        out.println(maxFlow());*/
    }
    static int maxFlow() {
        int cnt = 0;
        while (dfs(0, new boolean[2 + N + M])) cnt++;
        return cnt;
    }
    static boolean dfs(int cur, boolean[] marked) {
        if (cur == 1) return true;
        // out.println(cur);
        marked[cur] = true;
        for (int i = 0; i < N + M + 2; i++) {
            if (!marked[i] && f[cur][i] < c[cur][i] && dfs(i, marked)) {
                // out.println(cur + " " + i);
                f[cur][i]++;
                f[i][cur]--;
                return true;
            }
        }
        return false;
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
