import java.util.*;
import java.io.*;
public class A {
    static void solve() {
        int N = in.nextInt();
        int M = in.nextInt();
        List<List<Integer>> adj = new ArrayList<List<Integer>>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Integer>());
        }
        int[] aa = new int[M];
        for (int i = 0; i < M; i++) {
            aa[i] = in.nextInt() - 1;
            // aa[i] = (int) (Math.random() * N);
        }
        long[] cost = new long[N];
        long tot = 0;
        for (int i = 0; i < M; i++) {
            if (i >= 1 && aa[i] != aa[i - 1]) {
                adj.get(aa[i]).add(aa[i - 1]);
                cost[aa[i]] += Math.abs(aa[i] - aa[i - 1]);
            }
            if (i <= M - 2 && aa[i] != aa[i + 1]) {
                adj.get(aa[i]).add(aa[i + 1]);
                cost[aa[i]] += Math.abs(aa[i] - aa[i + 1]);
            }
        }
        for (int i = 0; i < N; i++) {
            tot += cost[i];
        }
        for (List<Integer> adj1 : adj) {
            Collections.sort(adj1);
        }
        long maxSave = 0;
        for (int i = 0; i < N; i++) {
            // i => j
            long ncost = 0;
            List<Integer> adj1 = adj.get(i);
            if (adj1.size() == 0) continue;
            int j = adj1.get(adj1.size() / 2);
            for (int adj2 : adj1) {
                ncost += Math.abs(j - adj2);
            }
            maxSave = Math.max(maxSave, cost[i] - ncost);
        }
        // out.println(tot);
        // out.println(Arrays.toString(cost));
        long ans = tot / 2 - maxSave;
        /*
        // test
        long min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tot = 0;
                for (int k = 0; k < M - 1; k++) {
                    int x = aa[k];
                    int y = aa[k + 1];
                    if (x == i) x = j;
                    if (y == i) y = j;
                    tot += Math.abs(x - y);
                }
                min = Math.min(min, tot);
            }
        }
        out.println(Arrays.toString(aa));
        if (min != ans) {
            out.println("REAL ANS: " + min);
            out.println("WRONG ANS: " + ans);
            }*/
        out.println(ans);
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
