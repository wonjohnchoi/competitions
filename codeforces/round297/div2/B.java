import java.util.*;
import java.io.*;
public class B {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
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
    public static void main(String args[]) {
        String S= in.next();
        int N = in.nextInt();
        BinaryIndexedTree bit = new BinaryIndexedTree(S.length());
        for (int i = 0; i < N; i++) {
            int a = in.nextInt();

            // out.println(a + " " + (S.length() - a + 1));
            // out.flush();
            if (a > S.length() / 2) a = S.length() - a + 1;
            bit.insert(a);
        }
        for (int i = 0; i < S.length(); i++) {
            int j = (i + 1);
            if (j > S.length() / 2) j = S.length() - j + 1;
            if (bit.sum(j) % 2 == 0) {
                out.print(S.charAt(i));
            } else {
                out.print(S.charAt(S.length() - i - 1));
            }
        }
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
