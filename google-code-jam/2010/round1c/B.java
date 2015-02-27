import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            int L, P, C;
            L = sc.nextInt();
            P = sc.nextInt();
            C = sc.nextInt();
            String ans = "" + find(L, P, C);
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
    static int find(int L, int P, int C) {
        // System.out.println(L + " " + P + " " + C);
        if ((long) L * C >= P) return 0;
        double ratio = (double) P / L;
        double sqrtRatio = Math.sqrt(ratio);
        int M = (int) (L * sqrtRatio);
        double r1;
        int l1, p1;
        if ((double) M / L > (double) P / M) {
            r1 = (double) M / L;
            l1 = L;
            p1 = M;
        } else {
            r1 = (double) P / M;
            l1 = M;
            p1 = P;
        }
        double r2;
        int l2, p2;
        if ((double) (M + 1) / L > (double) P / (M + 1)) {
            r2 = (double) (M + 1) / L;
            l2 = L;
            p2 = M + 1;
        } else {
            r2 = (double) P / (M + 1);
            l2 = M + 1;
            p2 = P;
        }
        if (r1 > r2) {
            return find(l2, p2, C) + 1;
        } else {
            return find(l1, p1, C) + 1;
        }
    }
}
