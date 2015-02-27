import java.util.*;
import java.io.*;
import java.math.BigInteger;
public class C {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            long N = sc.nextLong();
            long cnt = 0;
            if (N == 1) cnt = 0;
            else {
                for (long i = 2; i * i <= N; i++) {
                    if (isPrime((int) i)) {
                        long cnt2 = (long) (Math.log(N) / Math.log(i));
                        long val = (long) Math.pow(i, cnt2);
                        if (val > N) cnt2--;
                        else if (val * i <= N) cnt2++;
                        // System.out.println(" " + N + " " + 1.0/i + "  " + ret.get(ret.size() - 1));
                        cnt += cnt2 - 1;
                    }
                }
                cnt++;
            }
            String ans = "" + cnt;
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
    static boolean[] cache = null;
    public static boolean isPrime(int n) {
        if (cache == null) {
            cache = new boolean[1000001];
            Arrays.fill(cache, true);
            for (int i = 2; i * i < cache.length; i++) {
                if (cache[i]) {
                    for (int j = i * i; j < cache.length; j += i) {
                        cache[j] = false;
                    }
                }
            }
        }
        return cache[n];
    }
}
