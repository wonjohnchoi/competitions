import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int h1, a1, d1, h2, a2, d2;
        h1 = in.nextInt();
        a1 = in.nextInt();
        d1 = in.nextInt();
        h2 = in.nextInt();
        a2 = in.nextInt();
        d2 = in.nextInt();
        int hp, ap, dp;
        hp = in.nextInt();
        ap = in.nextInt();
        dp = in.nextInt();
        int minCost = Integer.MAX_VALUE;
        for (int h = 0; h < 301; h++) {
            for (int a = 0; a < 201; a++) {
                for (int d = 0; d < 101; d++) {
                    boolean win = false;
                    int hit1 = 0;
                    if (a2 <= d1 + d) {
                        hit1 = Integer.MAX_VALUE;
                    } else {
                        hit1 = (h1 + h) / (a2 - d1 - d);
                        while (hit1 * (a2 - d1 - d) < h1 + h) hit1++;
                    }
                    int hit2 = 0;
                    if (a1 + a <= d2) {
                        hit2 = Integer.MAX_VALUE;
                    } else {
                        hit2 = h2 / (a1 + a - d2);
                        while (hit2 * (a1 + a - d2) < h2) hit2++;
                    }
                    win = hit1 > hit2;
                    if (win) {
                        minCost = Math.min(minCost, hp * h + ap * a + dp * d);
                    }
                }
            }
        }
        out.println(minCost);
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
