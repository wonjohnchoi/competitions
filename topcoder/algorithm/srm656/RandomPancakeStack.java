import java.util.*;
import java.io.*;
public class RandomPancakeStack {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static double expectedDeliciousness(int[] d) {
	int N = d.length;
	double[][] dp = new double[N][N];
	for (int i = 0; i < N; i++) {
	    dp[0][i] = d[0] / (double) (i + 1);
	}
	for (int i = 1; i < N; i++) {
	    for (int k = N - i - 1; k >= 0; k--) {
		for (int j = 0; j <= i; j++) {
		    dp[i][k] += d[j];
		    if (j > 0) dp[i][k] += dp[j - 1][i - j + k];
		}
		dp[i][k] /= i + k + 1;
	    }
	}
	//out.println(dp[0] + " " + dp[1] + " " + dp[2]);
	return dp[N - 1][0];
    }
    public static void main(String args[]) {

    }
}
