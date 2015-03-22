import java.util.*;
import java.io.*;
public class B2 {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static int N, M;
    static int[] a, b;
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
        KuhnMatchingGraph g = new KuhnMatchingGraph(N, M);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (Math.abs(a[i] - b[j]) <= 1) {
                    g.addEdge(i, j);
                }
            }
        }
        out.println(g.getMaximalMatching());
    }
}
class KuhnMatchingGraph {
    int n;
    int m;
    List<Integer>[] edges;
    int[] p1;
    int[] p2;
    int[] was;
    int VER;

    @SuppressWarnings("unchecked")
    public KuhnMatchingGraph(int n, int m) {
        this.n = n;
        this.m = m;
        edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Integer>(2);
        }
    }

    public void addEdge(int from, int to) {
        edges[from].add(to);
    }

    public int[] getPaired1() {
        return p1;
    }

    public int[] getPaired2() {
        return p2;
    }

    public int getMaximalMatching() {
        p1 = new int[n];
        p2 = new int[m];
        was = new int[n];
        VER = 0;
        Arrays.fill(p1, -1);
        Arrays.fill(p2, -1);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j : edges[i]) {
                if (p2[j] < 0) {
                    p2[j] = i;
                    p1[i] = j;
                    answer++;
                    break;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (p1[i] >= 0) {
                continue;
            }
            VER++;
            if (dfs(i)) {
                answer++;
            }
        }
        return answer;
    }

    public List<Integer>[] getEdges() {
        return edges;
    }

    boolean dfs(int v) {
        if (was[v] == VER) {
            return false;
        }
        was[v] = VER;
        for (int i = 0; i < edges[v].size(); i++) {
            int e = edges[v].get(i);
            if (p2[e] < 0 || dfs(p2[e])) {
                p2[e] = v;
                p1[v] = e;
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
