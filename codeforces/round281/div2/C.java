import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N = in.nextInt();
        double[] a = new double[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        int M = in.nextInt();
        double[] b = new double[M];
        for (int i = 0; i < M; i++) {
            b[i] = in.nextInt();
        }
        Arrays.sort(b);
        Arrays.sort(a);
        int valA = -600000;
        int valB = 600000;
        for (int i = 0; i <= a.length + 1; i++) {
            double cut;
            if (i == a.length) {
                cut = a[i - 1] + 0.5;
            } else if (i < a.length) {
                cut = a[i] - 0.5;
            } else {
                cut = Double.MAX_VALUE;
            }
            int bi = Arrays.binarySearch(b, cut);
            bi = -(bi + 1); // bi, bi+1 ... greater or equal of b
            int threeB = M - 1 - bi + 1;
            int twoB = bi - 1 + 1;

            int ai = Arrays.binarySearch(a, cut);
            ai = -(ai + 1);
            int threeA = N - 1 - ai + 1;
            int twoA = ai - 1 + 1;

            int curA = twoA * 2 + threeA * 3;
            int curB = twoB * 2 + threeB * 3;
            int curDiff = curA - curB;
            if (valA - valB < curDiff || (valA - valB == curDiff && valA < curA)) {
                valA = curA;
                valB = curB;
            }
        }
        out.println(valA + ":" + valB);
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
