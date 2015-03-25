import java.util.*;
import java.io.*;
public class D {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N = in.nextInt();
        double P = in.nextDouble();
        int T = in.nextInt();
        double[][] dp = new double[N + 1][T + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= T; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
                else dp[i][j] = P * (1 + dp[i - 1][j - 1]) + (1 - P) * dp[i][j - 1];
            }
        }
        out.println(dp[N][T]);
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
