import java.util.*;
import java.io.*;
public class B {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int TC = in.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            String ans = "";
            int D = in.nextInt();
            PriorityQueue<Integer> pq = new PriorityQueue<>(D, Collections.reverseOrder());
            int[] p = new int[D];
            for (int i = 0; i < D; i++) {
                p[i] = in.nextInt();
                pq.add(p[i]);
            }
            int tot = Integer.MAX_VALUE;
            int time = 0;
            while (true) {
                int max = pq.poll();
                // out.println(max);
                tot = Math.min(time + max, tot);
                if (max == 1) break;
                time++;
                int a = max / 2;
                int b = max - a;
                pq.add(a);
                pq.add(b);
                if (a < 0 || b < 0) throw new RuntimeException("WTF");
            }
            ans = tot + "";
            // out.println("INPUT: " + Arrays.toString(p));
            out.printf("Case #%d: %s\n", tc, ans);
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
