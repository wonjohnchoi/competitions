import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int C = sc.nextInt();
            double D = sc.nextDouble();
            double t = 0.0;
            double b = -1;
            for (int i = 0; i < C; i++) {
                int P, V;
                P = sc.nextInt();
                V = sc.nextInt();
                double ct = (V - 1) / 2.0 * D;
                double e = P + ct;
                double s = P - ct;
                if (i != 0 && b > s) {
                    double used;
                    if (ct > t) {
                        used = Math.min(ct - t, b - s);
                        b -= used;
                        t += used;
                    } else if (ct < t) {
                        used = Math.min(t - ct, b - s);
                        s += used;
                        e += used;
                        ct += used;
                    }
                    if (b > s) {
                        double need = (b - s) / 2.0;
                        ct = t + need;
                        s += need;
                        e += need;
                        b -= need;
                    }
                }
                t = Math.max(t, ct);
                b = e + D;
            }
            out.printf("Case #%d: %f\n", tc, t);
        }
    }
}
