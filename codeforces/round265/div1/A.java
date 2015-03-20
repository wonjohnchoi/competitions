import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N, P;
        N = in.nextInt();
        P = in.nextInt();
        String S = in.next();
        char[] c = S.toCharArray();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = (int) (c[i] - 'a');
        }
        int i = N - 1;
        while (true) {
            if (i == -1) {
                out.println("NO");
                System.exit(0);
            }
            a[i]++;
            outer : while (true) {
                if (a[i] >= P) break;
                if ((i >= 2 && a[i - 2] == a[i])
                    || (i >= 1 && a[i - 1] == a[i])) {
                        a[i]++;
                        continue outer;
                }
                break;
            }
            if (a[i] >= P) i--;
            else break;
        }
        for (int j = i + 1; j < N; j++) {
            for (int k = 0; k < P; k++) {
                if (!(j >= 2 && a[j - 2] == k)
                    && !(j >= 1 && a[j - 1] == k)) {
                    a[j] = k;
                    break;
                }
            }
        }
        char[] fc = new char[N];
        for (i = 0; i < N; i++) {
            fc[i] = (char) (a[i] + 'a');
        }
        out.println(new String(fc));
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
