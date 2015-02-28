import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            int N = sc.nextInt();
            int[] best = new int[N - 1];
            for (int i = 0; i < N - 1; i++) {
                best[i] = sc.nextInt() - 1;
            }
            double[] hs = new double[N];
            Arrays.fill(hs, 1);
            int repeat;
            boolean possible = false;
            for (repeat = 0; repeat < N * 10; repeat++) {
                boolean done = true;
                for (int i = 0; i < N - 2; i++) {
                    double bestSlope = -10e9;
                    double bestH = 0;
                    int bestJ = -1;
                    for (int j = i + 1; j < N; j++) {
                        double slope = slope(i, j, hs);
                        if (slope > bestSlope) {
                            bestSlope = slope;
                            if (hs[j] > bestH) {
                                bestH = hs[j];
                                bestJ = j;
                            }
                        }
                    }
                    /*
                    out.println(bestJ + " " + best[i] + " " + i);
                    out.println(N);
                    out.println(bestJ);
                    out.println(best[i]);*/
                    if (bestJ != best[i]) {
                        double h = slope(i, bestJ, hs) * (best[i] - i) + hs[i];
                        int h2;
                        if (h == (int) h) {
                            h2 = (int)  h + 1;
                        } else {
                            h2 = (int) Math.ceil(h);
                        }
                        hs[best[i]] = Math.max(hs[bestJ] + 1, h2);
                        done = false;
                        /*for (int j = 0; j < hs.length; j++) {
                            System.out.print(hs[j] + " ");
                        }
                        out.println();*/
                        // break;
                    }
                }
                if (done) {
                    possible = true;
                    break;
                }
            }
            String ans = "";
            if (!possible) {
                ans = "Impossible";
            } else {
                for (int i = 0; i < hs.length; i++) {
                    ans += (int) hs[i];
                    if (i != hs.length - 1) ans += " ";
                }
            }
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
    static double slope(int i, int j, double[] hs) {
        return (hs[j] - hs[i]) / (j - i);
    }
}
