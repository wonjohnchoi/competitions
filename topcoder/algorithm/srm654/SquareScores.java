import java.util.*;
import java.io.*;
public class SquareScores {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static double calcexpectation(int[] p, String s) {
        int N = s.length();
        double tot = 0.0;
        double[][][] dp = new double[N + 1][N + 1][26];
        // sp[0][s.charAt(0)] or sp[0] avg.
        /*
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                for (int k = 0; k < 26; k++) {
                    dp[i][j][k] = -1;
                }
            }
            }*/
        /*
        for (int i = 0; i < 26; i++) {
            dp[N][0][i] = 1;
            }*/
        dp[N][0][0] = 1;
        for (int i = N - 1; i >= 0 ; i--) {
            int cur = s.charAt(i) == '?' ? -1 : (int) (s.charAt(i) - 'a');
            for (int j = 0; j < N + 1; j++) {
                for (int k = 0; k < 26; k++) {
                    if (dp[i + 1][j][k] > 0) {
                        // out.println((i + 1) + " " + j + " " + k + " " + dp[i + 1][j][k]);
                        if (cur != -1) {
                            if (cur != k) {
                                dp[i][1][cur] += dp[i + 1][j][k];
                                tot += dp[i + 1][j][k];
                            } else {
                                dp[i][j + 1][k] += dp[i + 1][j][k];
                                tot += dp[i + 1][j][k] * (j + 1);
                            }
                        } else {
                            for (int l = 0; l < p.length; l++) {
                                cur = l;
                                if (cur != k) {
                                    dp[i][1][cur] += p[l] * dp[i + 1][j][k] / 100.0;
                                    tot += dp[i + 1][j][k] * p[l] * 1 / 100.0;
                                } else {
                                    dp[i][j + 1][k] += p[l] * dp[i + 1][j][k] / 100.0;
                                    tot += dp[i + 1][j][k] * p[l] * (j + 1) / 100.0;
                                }
                            }
                        }
                    }
                }
            }
        }
        /*
        double tot = 0;
        //for (int k = 0; k < N; k++) {
            for (int i = 0; i < N + 1; i++) {
                for (int j = 0; j < 26; j++) {
                    if (dp[N][i][j] > 0) tot += dp[N][i][j] * i;
                }
                }*/
            //}
        // ans dp[0][0]
        // /100.0
        return tot;
    }
    public static void main(String args[]) {
        out.println(calcexpectation(new int[] {1, 99}, "aaaba"));
        out.println(calcexpectation(new int[] {10, 20, 70}, "aa?bbbb"));
        out.println(calcexpectation(new int[] {10, 20, 30, 40}, "a??c?dc?b"));
        out.println(calcexpectation(new int[] {25, 25, 21, 2, 2, 25}, "a??b???????ff??e"));
    }
}
