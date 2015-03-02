import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    static double[] ts, xs;
    static double D;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            int N;
            double A;
            D = sc.nextDouble();
            N = sc.nextInt();
            A = sc.nextDouble();
            ts = new double[N];
            xs = new double[N];
            for (int i = 0; i < N; i++) {
                ts[i] = sc.nextDouble();
                xs[i] = sc.nextDouble();
                if (i != 0) {
                    if (xs[i - 1] >= D && xs[i] >= D) {
                        i--;
                        N--;
                    } else if (xs[i - 1] < D && xs[i] > D) {
                        ts[i] = (D - xs[i - 1]) / (xs[i] - xs[i - 1]) * (ts[i] - ts[i - 1]) + ts[i - 1];
                        xs[i] = D;
                    }
                }
            }
            String ans = "";
            for (int i = 0; i < A; i++) {
                double a = sc.nextDouble();
                double tWait = 0;
                for (int j = 0; j < N - 1; j++) {
                    tWait = Math.max(tDelay(j, a), tWait);
                }

                ans += "\n" + (tWait + Math.sqrt(2 * D / a));
            }
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
    static double tDelay(double minT, double maxT, int i, double a) {
        double t1 = tDelay(minT, i, a);
        double t2 = tDelay(maxT, i, a);
        if (maxT - minT < 1e-10) return t1;
        // out.println(minT + " " + i + " " + a + " " + t1);
        // out.println(maxT + " " + i + " " + a + " " + t2);
        double midT = (minT + maxT) / 2;
        if (t1 > t2) {
            return tDelay(minT, midT, i, a);
        } else {
            return tDelay(midT, maxT, i, a);
        }
    }
    static double tDelay(int i, double a) {
        double minT = ts[i];
        double maxT = ts[i + 1];
        return tDelay(ts[i], ts[i + 1], i, a);
    }
    static double tDelay(double t, int i, double a) {
        return t - Math.sqrt((2 * xs[i] + 2 * (t - ts[i]) / (ts[i + 1] - ts[i]) * (xs[i + 1] - xs[i])) / a);
    }
}
