import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            String line = sc.next();
            String[] tokens = line.split("/");
            long[] frac = new long[2];
            frac[0] = Long.parseLong(tokens[0]);
            frac[1] = Long.parseLong(tokens[1]);
            frac = reduce(frac);
            // System.out.println(frac[0] + " " + frac[1]);
            String ans;
            int cnt = 0;
            while (frac[0] * 2 < frac[1] && frac[1] % 2 == 0) {
                frac[1] /= 2;
                cnt++;
            }
            if (frac[0] > frac[1]) {
                ans = "impossible";
            } else if (frac[0] * 2 < frac[1]) {
                ans = "impossible";
            } else if (!powOfTwo(frac[1])) {
                ans = "impossible";
            } else {
                cnt++;
                ans = cnt + "";
            }
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
    public static boolean powOfTwo(long a) {
        while (a % 2 == 0) {
            a /= 2;
        }
        return a == 1;
    }
    public static long[] reduce(long[] frac) {
        long gcd = gcd(frac[0], frac[1]);
        return new long[] {frac[0] / gcd, frac[1] / gcd};
    }
    public static long gcd(long a, long b) {
        if (a < b) gcd(b, a);
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
