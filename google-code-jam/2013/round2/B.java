import java.util.*;
import java.io.*;
// TIME_USED:
public class A {
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

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        long pow = 1;
        long[] twoPow = new long[51];
        for (int k = 0; k < twoPow.length; k++) {
            twoPow[k] = pow;
            pow *= 2;
        }
        for (int tc = 1; tc <= T; tc++) {
            int N;
            long P;
            N = sc.nextInt();
            P = sc.nextLong();
            int i = 0;
            while (N - i >= 0 && twoPow[N] - twoPow[N - i] + 1 <= P) {
                i++;
            }
            i--;
            long y;
            if (i != N) {
                y = twoPow[i + 1] - 2;
            } else {
                y = twoPow[N] - 1;
            }

            i = 0;
            while (twoPow[N - i] > P) {
                i++;
            }
            long z = twoPow[N] - twoPow[i];
            System.out.printf("Case #%d: %d %d\n", tc, y, z);
        }
    }
}
