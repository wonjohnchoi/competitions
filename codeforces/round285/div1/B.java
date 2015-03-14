import java.util.*;
import java.io.*;
// Copied solution from http://codeforces.com/contest/504/submission/9677009
public class B {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static int[] encode(int[] perm) {
        BinaryIndexedTree bit = new BinaryIndexedTree(perm.length);
        int[] encoded = new int[perm.length];
        for (int i = 0; i < perm.length; i++) {
            encoded[i] = perm[i] - bit.sum(perm[i]);
            bit.insert(perm[i] + 1);
        }
        return encoded;
    }
    public static int[] decode(int[] encoded) {
        BinaryIndexedTree bit = new BinaryIndexedTree(encoded.length);
        int[] perm = new int[encoded.length];
        for (int i = 0; i < encoded.length; i++) {
            bit.insert(i + 1);
        }
        for (int i = 0; i < encoded.length; i++) {
            perm[i] = bit.select(encoded[i] + 1) - 1;
            bit.remove(perm[i] + 1);
        }
        return perm;
    }
    public static void main(String args[]) {
        int N = in.nextInt();
        int[] perm1 = new int[N];
        for (int i = 0; i < N; i++) {
            perm1[i] = in.nextInt();
        }
        int[] perm2 = new int[N];
        for (int i = 0; i < N; i++) {
            perm2[i] = in.nextInt();
        }
        int[] a = encode(perm1);
        int[] b = encode(perm2);;
        int[] c = new int[N];
        for (int i = N - 1; i >= 0 ; i--) {
            int base = N - i;
            c[i] += a[i] + b[i];
            if (i > 0) c[i - 1] = c[i] / base;
            c[i] %= base;
        }
        // out.println(Arrays.toString(c));
        int[] ret = decode(c);
        for (int i = 0; i < N; i++) {
            if (i != 0) out.print(" ");
            out.print(ret[i]);
        }
        out.println();
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
