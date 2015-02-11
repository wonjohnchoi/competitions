import java.util.*;
import java.io.*;
public class A {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            double r, t;
            r = sc.nextDouble();
            t = sc.nextDouble();
            long numEst = (long) ((-2 * r + 1 + Math.sqrt((2 * r - 1) * (2 * r - 1) + 8 * t)) / 4.0);
            long ans = -1;
            for (long num = numEst + 2; num >= 1; num--) {
                long tUsed = (long) (2 * num * num + (2 * r - 1) * num);
                if (tUsed <= t) {

                    break;
                }
            }
            System.out.printf("Case #%d: %d\n", tc, ans);
        }
    }
}
