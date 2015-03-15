import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N = in.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = in.nextInt() - 1;
        }
        int m = N;
        int pref = 0;
        int suf = 0;
        for (int i = 0; i < N / 2; i++) {
            if (nums[i] != nums[N - i - 1]) break;
            else m -= 2;
        }
        List<Integer> left = new LinkedList<Integer>();
        List<Integer> right = new LinkedList<Integer>();
        for (int i = (n - m) / 2; i < (n - m) / 2 + m; i++) {
            int l = nums[i];
            int r = nums[n - i - 1];
            if (l != r) {
                int li = Collections.binarySearch(left, r);
                if (li != -1) left.remove(li);
                int ri = Collections.binarySearch(right, l);
                if (ri != -1) right.remove(ri);

            }
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
