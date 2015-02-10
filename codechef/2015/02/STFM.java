// F(x) = 1 * (1! + x) + 2 * (2! + x) + .. + x * (x! + x).
import java.util.*;
import java.io.*;
import java.math.BigInteger;
public class STFM {
    static long[] f;
    static int m;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        m = sc.nextInt();
        f = new long[m];
        f[0] = 0;
        long fac = 1;
        for (int i = 1; i < m; i++) {
            fac = (fac * i) % m;
            f[i] = (f[i - 1] + (i + 1) * i / 2 + fac * i) % m;
            // System.out.println("f " + i + ": " + f[i]);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = (ans + f(sc.nextLong())) % m;
        }
        System.out.println(ans);
    }
    public static int f(long p) {
        if (p < m) return (int) f[(int) p];
        long k = p - m;
        BigInteger a = new BigInteger("" + m).multiply(new BigInteger("" + 2))
            .add(new BigInteger("" + k))
            .multiply(new BigInteger("" + k).add(BigInteger.ONE)).divide(new BigInteger("" + 2))
            .add(ssq(k + m)).subtract(ssq(m - 1)).divide(new BigInteger("" + 2))
            .add(new BigInteger("" + f[m - 1]))
            .mod(new BigInteger("" + m));
        return a.intValue();
    }
    public static BigInteger ssq(long p) {
        BigInteger a = new BigInteger("" + p);
        BigInteger b = a.add(BigInteger.ONE);
        BigInteger c = a.multiply(new BigInteger("" + 2)).multiply(BigInteger.ONE);
        return a.multiply(b).multiply(c);
    }
}
