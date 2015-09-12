import java.util.*;
import java.io.*;
public class WorldCupTeamFormation {
    static void solve() {
        int NUM_P = 10;
        boolean[] used = new boolean[NUM_P];
        int[] scores = new int[NUM_P];
        for (int i = 0; i < NUM_P; i++) {
            scores[i] = in.nextInt();
        }
        int[] sums = new int[2];
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < sums.length; i++) {
                int bestK = -1;
                for (int k = 0; k < NUM_P; k++) {
                    if (!used[k]) {
                        if (bestK == -1 || scores[bestK] < scores[k])
                            bestK = k;
                    }
                }
                // out.println("Adding " + scores[bestK] + " to " + i);
                sums[i] += scores[bestK];
                used[bestK] = true;
            }
        }
        out.println(Math.max(sums[0], sums[1]));
    }
    static PrintWriter out = new PrintWriter(System.out);
    static InputReader in = new InputReader(System.in);
    static void d(Object o) {
        out.println(o);
        out.flush();
    }
    public static void main(String args[]) {
        solve();
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
