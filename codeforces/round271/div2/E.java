import java.util.*;
import java.io.*;
public class E {
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
        long D = in.nextLong();
        long[] h = new long[N];
        for (int i = 0; i < N; i++) {
            h[i] = in.nextLong();
        }
        int[] f = new int[N];
        BinaryIndexedTree bit = new BinaryIndexedTree(N);
        f[N - 1] = 1;
        for (int i = N - 2; i >= 0; i--) {
            f[i] = f[i + 1];
            int low = h[i] - D ;
            int high = h[i] + D;
            f[i] += bit.sum(
        }
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
