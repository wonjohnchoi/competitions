import java.util.*;
import java.io.*;
public class D {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        long N, M;
        N = sc.nextLong();
        int K = sc.nextInt();
        M = sc.nextLong();
        long[] cnt = new long[K];
        cnt[0] = 1;
        long[] rems = new long[10];
        for (int i = 0; i < rems.length; i++) {
            rems[i] = i % K;
        }
        long tot = 1;
        for (int i = 0; i < N; i++) {
            tot = (tot * (i == N - 1 ? 9 : 10)) % M;
            long[] ncnt = new long[K];
            for (int j = (i == N - 1 ? 1 : 0); j < 10; j++) {
                for (int k = 0; k < cnt.length; k++) {
                    int nk = (int) ((k + rems[j]) % K);
                    if (nk != 0) {
                        if (nk == 1)
                        out.println(k + " " + j + " " + rems[j] + " " + cnt[k]);
                        ncnt[nk] += cnt[k];
                        ncnt[nk] %= M;
                    }
                }
            }
            cnt = ncnt;
            for (int j = 0; j < cnt.length; j++) {
                out.print(j + " " + cnt[j]);
                out.println();
            }
            out.println(Arrays.toString(rems) + " " + K);
            for (int j = 0; j < rems.length; j++) {
                rems[j] = (rems[j] * 10) % K;
            }
            out.println(Arrays.toString(rems));
        }
        out.println(tot);
        for (int i = 0; i < cnt.length; i++) {
            tot = (tot - cnt[i] + M) % M;
        }
        out.println(tot);
    }
}
