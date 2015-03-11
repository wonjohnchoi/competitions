import java.util.*;
import java.io.*;
public class F {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static        List<List<Integer>> adj;

    public static void main(String args[]) {
        int N = in.nextInt();
        r = new int[N];
        for (int i = 0; i < N; i++) {
            r[i] = in.nextInt();
        }
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < N - 1; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        int max = 0;
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            max = Math.max(f(i, -1, r[i]), max);
        }
        out.println(max);
    }
    static int[] r;
    static int f(int v, int p, int val) {
        // out.println(v + " " + p + " " + val);
        visited[v] = true;
        int ret = 0;
        for (int w : adj.get(v)) {
            if (!visited[w] && w != p && val < r[w]) {
                // out.println(w + " " + r[w]);
                ret = Math.max(f(w, v, r[w]), ret);
            }
        }
        visited[v] = false;
        return ret + 1;
    }
    static boolean[] visited;
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
