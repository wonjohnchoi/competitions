import java.util.*;
import java.io.*;
// TIME_USED:
public class B {
    public static boolean DEBUG = true;
    public static void d(Object o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(int o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(long o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(double o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(float o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(boolean o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    static long[] TWO_POW;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        TWO_POW = new long[40];
        TWO_POW[0] = 1;
        for (int i = 1; i < TWO_POW.length; i++) {
            TWO_POW[i] = TWO_POW[i - 1] * 2;
        }
        for (int tc = 1; tc <= T; tc++) {
            long A, B, K;
            A = sc.nextLong();
            B = sc.nextLong();
            K = sc.nextLong();
            long ans = find(A, B, K);
            System.out.printf("Case #%d: %d\n", tc, ans);
        }
    }
    public static String bin(long a) {
        return Long.toBinaryString(a);
    }
    public static int len(long a) {
        return bin(a).length();
    }
    public static long find(long a, long b, long k) {
        if (a <= K || b <= k) {
            return a * b;
        }
        int as, bs, ks;
        as = len(a);
        bs = len(b):
        ks = len(k);
        if (as == bs && bs == ks) {
            return TWO_POW[as - 1] * (a + b) - TWO_POW[as - 1] * TWO_POW[as - 1]
                + find(a - TWO_POW[as - 1], b - TWO_POW[as - 1], k - TWO_POW[as - 1]);
        }
        /*
        // d("idx : " + idx + " a: " + a + " b: " + b + " k: " + k);
        if (idx == -1) {
            return 1;
        }
        if (a < 0 || b < 0) {
            return 0;
        }
        if (k <= TWO_POW[idx]) {
            return find(idx - 1, a, b, k)
                + find(idx - 1, a - TWO_POW[idx], b, k)
                + find(idx - 1, a, b - TWO_POW[idx], k);
        }
        return find(idx - 1, a - TWO_POW[idx], b - TWO_POW[idx], k - TWO_POW[idx]);
        */
    }
}
