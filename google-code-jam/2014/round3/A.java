import java.util.*;
import java.io.*;

public class A {
    public static double[] trans;
    public static double[] sums;
    public static int N;
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(new FileReader("A-large.in"));
        int t = sc.nextInt();
        for (int t2 = 1; t2 <= t; t2 ++) {
            double ans = 0.0;

            double p, q, r, s;
            N = sc.nextInt();
            p = sc.nextDouble();
            q = sc.nextDouble();
            r = sc.nextDouble();
            s = sc.nextDouble();
            trans = new double[N];

            sums = new double[N]; // sums[i] is the sum up to ith one
            double sum = 0.0;
            for (int i = 0; i < N; i++) {
                trans[i] = (i * p + q) % r + s;
                sum += trans[i];
                sums[i] = sum;
                // System.out.print(sum + " ");
            }
            // System.out.println();
            
            double best = 0.0;
            for (int a = 0; a < N; a++) {
                double v0 = range(0, a - 1);
                double v1v2 = range(0, N - 1) - v0;
                int idx = Arrays.binarySearch(sums, v1v2 / 2 + v0);
                if (idx < 0) idx = -(idx + 1);
                
                double v1, v2;
                // b = idx or b = idx + 1
                v1 = range(a, idx);
                v2 = range(idx + 1, N - 1);
                // System.out.printf("(a, b) = (%d, %d)\n", a, idx);
                // System.out.printf("(v0, v1, v2) = (%f, %f, %f)\n", v0, v1, v2);
                best = Math.max(best, prob(v0, v1, v2));
                // System.out.println(prob(v0, v1, v2));

                v1 = range(a, idx + 1);
                v2 = range(idx + 2, N - 1);
                // System.out.printf("(a, b) = (%d, %d)\n", a, idx + 1);
                // System.out.printf("(v0, v1, v2) = (%f, %f, %f)\n", v0, v1, v2);
                best = Math.max(best, prob(v0, v1, v2));
                // System.out.println(prob(v0, v1, v2));
            }

            System.out.printf("Case #%d: %f\n", t2, best);
        }
    }

    public static double prob(double v1, double v2, double v3) {
        double max = Math.max(Math.max(v1, v2), v3);
        double tot = v1 + v2 + v3;
        return (tot - max) / tot;
    }

    public static double range(int a, int b) {
        if (a > b) return 0;
        if (a < 0) a = 0;
        if (b >= N) b = N - 1;
        return sums[b] - sums[a] + trans[a];
    }
}
