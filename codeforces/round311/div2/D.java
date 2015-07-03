import java.util.*;
import java.io.*;
public class D {
    static void solve() {
        int N, M;
        N = in.nextInt();
        M = in.nextInt();
        List<Integer>[] adj = new List[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < M; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            adj[a].add(b);
            adj[b].add(a);
        }
        boolean[] marked = new boolean[N];
        int maxLen = 0;
        List<Integer> ss = new ArrayList<>();
        int cntTwo = 0;
        int cntOne = 0;
        for (int i = 0; i < N; i++) {
            if (!marked[i]) {
                int cur = getMaxLen(adj, marked, i);
                if (cur == 2) cntTwo++;
                else if (cur == 1) cntOne++;
                else ss.add(i);
                maxLen = Math.max(maxLen, cur);
            }
        }
        if (maxLen == 1) {
            out.println("3 " + cntOne);
            return;
        }
        if (maxLen == 2) {
            out.println("2 " + cntTwo);
            return;
        }
        long cnt = 0;
        for (int start : ss) {
            List<Counter> a = get(start, adj);
            int[][] dp = new int[a.size() + 1][2];
            dp[0][0] = 1;
            for (int i = 1; i < a.size() + 1; i++) {
                dp[i][0] += dp[i - 1][0] * a.get(i - 1).even;
                dp[i][0] += dp[i - 1][1] * a.get(i - 1).odd;
                dp[i][1] += dp[i - 1][0] * a.get(i - 1).odd;
                dp[i][1] += dp[i - 1][1] * a.get(i - 1).even;
            }
            cnt += dp[a.size()][0] - 1;
        }
        out.println("1 " + cnt);
    }
    static List<Counter> get(int start, List<Integer>[] adj) {
        List<Counter> ret = new ArrayList<>();
        for (int next : adj[start]) {
            ret.add(get2(start, next, adj));
        }
        return ret;
    }
    static Counter get2(int v, int w, List<Integer>[] adj) {
        Counter cnter = new Counter();
        cnter.even = 1;
        cnter.odd = 0;
        for (int next : adj[w]) {
            if (next != v) {
                Counter nc = get2(w, next, adj);
                cnter.even += nc.odd;
                cnter.odd += nc.even;
            }
        }
        return cnter;
    }
    static int getMaxLen(List<Integer>[] adj, boolean[] marked, int i) {
        marked[i] = true;
        int len = 1;
        for (int next : adj[i]) {
            if (!marked[next]) {
                len += getMaxLen(adj, marked, next);
            }
        }
        return len;
    }
    static class Counter {
        int even = 0;
        int odd = 0;
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
