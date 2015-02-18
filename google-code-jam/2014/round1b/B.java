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
    static long[] THREE_POW;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        TWO_POW = new long[20];
        THREE_POW = new long[20];
        TWO_POW[0] = 1;
        THREE_POW[0] = 1;
        for (int i = 1; i < TWO_POW.length; i++) {
            TWO_POW[i] = TWO_POW[i - 1] * 2;
            THREE_POW[i] = THREE_POW[i - 1] * 3;
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
    public static int numOne(String s) {
        int ret = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ret++;
        }
        return ret;
    }
    public static long find(long a, long b, long k) {
        if (a < b) {
            long tmp = a;
            a = b;
            b = tmp;
        } // guarantee a >= b
        if (a == 0 || b == 0 || k == 0) return 0;
        if (a <= k || b <= k) return a * b;
        // guarantee a >= b > k
        d(a + " " + b + " " + k);
        long ret = 0;
        int aI = Arrays.binarySearch(TWO_POW, a);
        int bI = Arrays.binarySearch(TWO_POW, b);
        int kI = Arrays.binarySearch(TWO_POW, k);
        if (aI >= 0 && bI >= 0 && kI >= 0) {
            ret = TWO_POW[aI - bI] * THREE_POW[bI - kI] * TWO_POW[kI] * TWO_POW[kI];
            return ret;
        }
        // 4 3 2
        int aLen = len(a);
        int bLen = len(b);
        int kLen = len(k);
        // 3 2 2
        if (aI < 0) {
            ret = find(a - TWO_POW[aLen - 1], b, k) + find(TWO_POW[aLen - 1], b, k);
        } else if (bI < 0) {
            ret = find(a, b - TWO_POW[bLen - 1], k) + find(a, TWO_POW[bLen - 1], k);
        } else if (kI < 0) {
            
        }
        if (aLen > bLen) {
            if (aI < 0) {
                ret = find(a - TWO_POW[aLen - 1], b, k) + find(TWO_POW[aLen - 1], b, k);
            } else {
                
            }
            // find(4 - 4, 3, 2) + find(4, 3, 2)
        } else {
            if (aI >= 0 && bI >= 0) {
                ret = 3 * find(TWO_POW[aLen - 1], TWO_POW[bLen - 1], k);
            } else if (aI >= 0) {
                ret = 2 * find(TWO_POW[aLen - 1], TWO_POW[aLen - 1], k)
                    + find(TWO_POW[aLen - 1], b - TWO_POW[bLen - 1], k);
            } else {
                long x = find(a - TWO_POW[aLen - 1], TWO_POW[bLen - 1], k);
                long y = find(TWO_POW[aLen - 1], b - TWO_POW[bLen - 1], k);
                ret = x + y + find(TWO_POW[aLen - 1], TWO_POW[bLen - 1], k);
            }
        }
        return ret;
    }
}
