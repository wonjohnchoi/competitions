import java.util.*;
import java.io.*;
public class C {
    public static PrintWriter out = new PrintWriter(System.out);
    public static Scanner in = new Scanner(System.in);
    static int TC;
    static String solve(int tc) {
        int N = Integer.parseInt(in.nextLine());
        HashSet<String>[] words = new HashSet[N];
        for (int i = 0; i < N; i++) {
            words[i] = new HashSet<String>();
        }
        for (int i = 0; i < N; i++) {
            String line = in.nextLine();
            for (String word : line.split(" "))
                words[i].add(word);
        }
        int both = Integer.MAX_VALUE;
        for (int mask = 0; mask < (1 << (N - 2)); mask++) {
            HashSet<String> en = new HashSet<>(words[0]);
            HashSet<String> fr = new HashSet<>(words[1]);
            for (int i = 0; i < N - 2; i++) {
                HashSet<String> cur;
                if ((mask & (1 << i)) > 0) {
                    cur = en;
                } else {
                    cur = fr;
                }
                cur.addAll(words[i + 2]);
            }
            en.retainAll(fr);
            both = Math.min(both, en.size());
        }

        return both + "";
    }
    public static void main(String args[]) {
        TC = Integer.parseInt(in.nextLine());
        for (int tc = 1; tc <= TC; tc++) {
            out.printf("Case #%d: %s\n", tc, solve(tc));
        }
        out.close();
    }
    static void d(Object o) {
        out.println(o);
        out.flush();
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
    public String nextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
