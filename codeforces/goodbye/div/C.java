import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N, M;
        N = in.nextInt();
        M = in.nextInt();
        ArrayList<Integer> d = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        int[] w = new int[N];
        for (int i = 0; i < N; i++) {
            w[i] = in.nextInt();
        }
        for (int i = 0; i < M; i++) {
            int a = in.nextInt() - 1;
            d.add(a);
            if (!b.contains(a)) b.add(a);
        }
        long tot = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < b.size(); j++) {
                // out.println("on day " + i + " looking to read " + d.get(i));
                int book = b.get(j);
                if (book == d.get(i)) {
                    b.remove(j);
                    b.add(0, book);
                    break;
                } else {
                    // out.println("lifting.. on day " + i + " " + b.get(j));
                    tot += w[b.get(j)];
                }
            }
        }
        out.println(tot);
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
