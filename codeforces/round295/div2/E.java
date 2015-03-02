import java.util.*;
import java.io.*;
import java.math.BigInteger;
public class E {
    public static PrintStream out = System.out;
    static long[] fac, invFac;
    static long MOD;
    static long choose(int n, int k) {
        return (((fac[n] * invFac[n - k]) % MOD) * invFac[k]) % MOD;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String ans = "";
        int N, K;
        N = sc.nextInt();
        K = sc.nextInt();
        String s = sc.next();
        MOD = (long) 1e9 + 7;
        fac = new long[N + 1];
        fac[0] = 1;
        invFac = new long[N + 1];
        for (int i = 1; i < fac.length; i++) {
            fac[i] = (fac[i - 1] * i) % MOD;
        }
        invFac[N] = new BigInteger(fac[N] + "").modPow(new BigInteger((MOD - 2) + ""), new BigInteger(MOD + "")).longValue();
        for (int i = N - 1; i >= 0; i--) {
            invFac[i] = (invFac[i + 1] * (i + 1)) % MOD;
        }
        if (invFac[0] != 1 || invFac[1] != 1) throw new RuntimeException(invFac[0] + " " + invFac[1]);
        long lineSum = 0;
        for (int i = N - 1; i >= 0; i--) {
            char c = s.charAt(i);
            long j = (long) (c - '0');
            lineSum = lineSum + j;
        }
        long mult = 1;
        long tot = 0;
        long partialLineSum = 0;
        // out.println("LINESUM: " + lineSum);
        for (int i = N - 1; i >= 0; i--) {
            char c = s.charAt(i);
            long j = (long) (c - '0');
            if (i - 1 < K - 1) break;
            partialLineSum = (partialLineSum + j) % MOD;
            long partial;
            if (K == 0) partial = 0;
            else partial = ((lineSum - partialLineSum) * choose(i - 1, K - 1)) % MOD;
            partial = (((partial + j * choose(i, K)) % MOD) * mult) % MOD;
            mult = (mult * 10) % MOD;
            tot = (tot + partial) % MOD;
        }
        ans = tot + "";
        out.printf("%s\n", ans);
    }
}
