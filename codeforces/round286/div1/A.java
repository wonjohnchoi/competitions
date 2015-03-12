import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static HashMap<Long, Integer> cache;
    static long i(int a, int b) {
        return a * 30001 + b;
    }
    static int best;
    public static void main(String args[]) {
        int N = in.nextInt();
        int J = in.nextInt();
        cache = new HashMap<Long, Integer>();
        best = Integer.MIN_VALUE;
        dia = new int[30001];
        MAX_DIA = -1;
        for (int i = 0; i < N; i++) {
            int p = in.nextInt();
            dia[p]++;
            MAX_DIA = Math.max(MAX_DIA, p);
        }
        out.println(f(0, J));
    }
    static int[] dia;
    static int MAX_DIA;
    static int f(int d, int j) {
        // out.println(d + " " + j);
        d += j;
        if (d > MAX_DIA) return 0;
        if (j < 1) return 0;
        long i = i(d, j);
        Integer cached = cache.get(i);
        //        out.println("CACHED: " + d + " " + j + " " + cached);
        if (cached == null) {
            cached = 0;
            for (int dj = -1; dj <= 1; dj++) {
                cached = Math.max(cached, f(d, j + dj));
            }
            //if (dia[d] != 0)
            // out.println(cached + "=>" + (cached + dia[d]) + " at " + d);
            cached += dia[d];
            cache.put(i, cached);
            best = Math.max(best, cached);
        }
        // out.println(d + " " + j + " " + cached);
        // out.println(dia[d]);
        return cached;
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
