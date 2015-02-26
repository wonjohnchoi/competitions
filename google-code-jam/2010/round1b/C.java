import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        cache = new long[501][501];
        cache2 = new long[501][501];
        for (int i = 0; i < cache.length; i++) Arrays.fill(cache[i], -1);
        for (int i = 0; i < cache2.length; i++) Arrays.fill(cache2[i], -1);
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            long cnt = 0;
            for (int i = 1; i <= N - 1; i++) {
                cnt += f(N, i);
                cnt %= MOD;
            }
            String ans = "" + cnt;
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
    public static long MOD = 100003;
    public static long[][] cache;
    public static long f(int n, int k) {
        if (k == 1) return 1;
        if (cache[n][k] == -1) {
            long val = 0;
            for (int i = Math.max(0, 2 * k - n - 1); i <= k - 2; i++) {
                val += f(k, i + 1) * choose(n - k - 1, k - 2 - i);
                val %= MOD;
            }
            cache[n][k] = val;
        }
        return cache[n][k];
    }
    public static long[][] cache2;
    public static long choose(int n, int k) {
        if (k == 0 || k == n) return 1;
        if (cache2[n][k] == -1) {
            long val = 0;
            val = choose(n - 1, k) + choose(n - 1, k - 1);
            val %= MOD;
            cache2[n][k] = val;
        }
        return cache2[n][k];
    }
}
