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
        for (int tc = 1; tc <= T; tc++) {
            int A, B;
            A = sc.nextInt();
            B = sc.nextInt();
            double[] P = new double[A];
            for (int i = 0; i < P.length; i++) P[i] = sc.nextDouble();
            double[] c = new double[A + 1];
            c[0] = B + 1;
            for (int i = 1; i < c.length; i++) c[i] = c[i - 1] * P[i - 1];
            double ans = Double.MAX_VALUE;
            for (int numBack = 0; numBack <= A; numBack++) {
                ans = Math.min(ans, 2 * B - A + 2 * (numBack + 1) - c[A - numBack]);
            }
            ans = Math.min(ans, B + 2);
            System.out.printf("Case #%d: %.7f\n", tc, ans);
        }
    }
}
