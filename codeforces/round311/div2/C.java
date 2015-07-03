import java.util.*;
import java.io.*;
public class C {
    static class BinaryIndexedTree {
        int length;
        int logLength;
        int[] c;
        BinaryIndexedTree(int n) {
            logLength = Integer.SIZE - Integer.numberOfLeadingZeros(n);
            length = 1 << logLength;
            c = new int[length + 1];
        }
        void add(int x, int num) {
            if (!(x >= 1 && x <= length)) throw new AssertionError();
            for (; x <= length; x += Integer.lowestOneBit(x)) {
                c[x] += num;
            }
        }
        void insert(int x) {
            add(x, 1);
        }
        void remove(int x) {
            add(x, -1);
        }
        int sum(int x) {
            if (!(x >= 0 && x <= length)) throw new AssertionError();
            int sum = 0;
            for (; x > 0; x -= Integer.lowestOneBit(x)) {
                sum += c[x];
            }
            return sum;
        }
        int sum(int l, int r) {
            return sum(r) - sum(l - 1);
        }
        int rank(int x) {
            return sum(x - 1) + 1;
        }
        int select(int k) {
            int ans = 0;
            for (int i = logLength; i >= 0; i--) {
                ans += (1 << i);
                if (c[ans] < k) {
                    k -= c[ans];
                } else {
                    ans -= (1 << i);
                }
            }
            return ans + 1;
        }
    }
    static void solve() {
        int N = in.nextInt();
        int MAX = 100001;
        List<Integer>[] ds = new List[MAX];
        for (int i = 0; i < MAX; i++) {
            ds[i] = new ArrayList<Integer>();
        }
        int[] l = new int[N];
        for (int i = 0; i < N; i++) {
            l[i] = in.nextInt();
        }
        int[] d = new int[N];
        for (int i = 0; i < N; i++) {
            d[i] = in.nextInt();
            ds[l[i]].add(d[i]);
        }
        int[] bigger = new int[MAX];
        int[] smaller = new int[MAX];
        for (int i = 1; i < MAX; i++) {
            int cur = 0;
            cur = smaller[i - 1];
            for (int dd : ds[i - 1]) {
                cur += dd;
            }
            smaller[i] = cur;
        }
        for (int i = MAX - 2; i >= 0; i--) {
            int cur = 0;
            cur = bigger[i + 1];
            for (int dd : ds[i + 1]) {
                cur += dd;
            }
            bigger[i] = cur;
        }
        int[] smallerSurvive = new int[MAX];
        int[] da = new int[201];
        int scnt = 0;
        for (int i = 1; i < MAX; i++) {
            for (int dd : ds[i - 1]) {
                da[dd]++;
            }
            scnt += ds[i - 1].size();
            int ccnt = ds[i].size();
            int need = 0;
            if (ccnt <= scnt) {
                // scnt -> ccnt - 1
                need = ccnt - 1;
            } else {
                need = scnt;
            }
            // pick top need number of ds
            for (int j = da.length - 1; j >= 0 && need > 0; j--) {
                int use = Math.min(da[j], need);
                need -= use;
                smallerSurvive[i] += use * j;
            }
        }
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < MAX; i++) {
            if (ds[i].size() > 0) {
                int cur = bigger[i] + smaller[i];
                cur -= smallerSurvive[i];
                best = Math.min(best, cur);
            }
        }
        out.println(best);
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
