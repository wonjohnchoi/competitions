import java.util.*;
import java.io.*;
public class MissingLCM {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static int getMin(int N){
        boolean[] prime = new boolean[N + 1];
        Arrays.fill(prime, true);
        for (int i = 2; i * i < prime.length; i++) {
            for (int j = i * i; j < prime.length; j += i) {
                prime[j] = false;
            }
        }
        long ret = -1;
        for (int i = 2; i < prime.length; i++) {
            if (prime[i]) {
                long b = (int) (Math.log(N) / Math.log(i));
                ret = Math.max(ret, min(N, i, b));
            }
        }
        if (ret == -1) ret = N + 1;
        return (int) ret;
    }
    static long min(long n, long p, long b) {
        double kk = (double) n / Math.pow(p, b);
        long ret = -1;
        if (close(kk, Math.round(kk))) {
            ret = (long) Math.round(kk) + 1;
        } else {
            ret = (long) Math.ceil(kk);
        }
        ret *= n;
        out.println(n + " " + p + " " + b + " " + ret);
        return ret;
    }
    static boolean close(double k, double k2) {
        double diff = Math.abs(k - k2);
        return diff <= ep;
    }
    static double ep = 0.000000001;
    public static void main(String args[]) {
        out.println(getMin(42));
    }
}
