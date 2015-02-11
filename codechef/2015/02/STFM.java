// F(x) = 1 * (1! + x) + 2 * (2! + x) + .. + x * (x! + x).
import java.util.*;
import java.io.*;
import java.math.BigInteger;
public class STFM {
    static long m;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        m = sc.nextLong();
        int[] g = new int[(int) m];
        int fac = 1;
        for (int i = 1; i < m; i++) {
            fac = (int) (((long) fac * i) % m);
            g[i] = ((long) g[i - 1] + i * fac) % m;
            //f[i] = (f[i - 1] + (i + 1) * i / 2 + fac * i) % m;
            // System.out.println("f " + i + ": " + f[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            long p = sc.nextLong();
            long a = p;
            long b = p + 1;
            if (a % 2 == 0) a /= 2;
            else b /= 2;
            int f = (a * b / 2) % m;
            f = ((long) f * p) % m;
            f = ((long)f + g[(int) Math.max(p, m - 1)]) % m;
            ans = ((long) ans + f) % m;
        }
        System.out.println(ans);
    }
}
