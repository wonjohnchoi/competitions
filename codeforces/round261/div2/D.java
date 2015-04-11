import java.util.*;
import java.io.*;
public class D {
    static void solve() {
        int N = in.nextInt();
        int[] a = new int[N];
        int[][] f = new int[2][N];
        HashMap<Integer, Integer> cnt1 = new HashMap<>(N);
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
            put(cnt1, a[i]);
            f[0][i] = cnt1.get(a[i]);
        }
        HashMap<Integer, Integer> cnt2 = new HashMap<>(N);
        for (int i = N - 1; i >= 0; i--) {
            put(cnt2, a[i]);
            f[1][i] = cnt2.get(a[i]);
        }
        long tot = 0;
        BinaryIndexedTree bit = new BinaryIndexedTree(N);
        for (int i = 0; i < N; i++) {
            if (i != 0) bit.insert(f[0][i - 1]);
            tot += i - bit.sum(f[1][i]);
        }
        out.println(tot);
    }
    static void put(Map<Integer, Integer> map, int val) {
        if (map.containsKey(val)) map.put(val, map.get(val) + 1);
        else map.put(val, 1);
    }
    static PrintWriter out = new PrintWriter(System.out);
    static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        solve();
        out.close();
    }
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
