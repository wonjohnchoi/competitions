import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int A1, A2, B1, B2;
            A1 = sc.nextInt();
            A2 = sc.nextInt();
            B1 = sc.nextInt();
            B2 = sc.nextInt();
            long cnt = 0;
            double ratio = (1 + Math.sqrt(5)) / 2.0;
            for (int a = A1; a <= A2; a++) {
                int largeB = (int) (a * ratio) + 1;
                int smallB = (int) (a / ratio);
                if (B2 >= largeB) cnt += (B2 - Math.max(B1, largeB) + 1);
                if (B1 <= smallB) cnt += (Math.min(B2, smallB) - B1 + 1);
            }
            String ans = cnt + "";
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
}
