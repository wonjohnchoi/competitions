import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String ans = "";
        long N = sc.nextLong();
        String S = sc.next();
        char[] cc = new char[] {'A', 'G', 'C', 'T'};
        int[] cnts = new int[4];
        for (int i = 0; i < N; i++) {
            char c = S.charAt(i);
            for (int j = 0; j < 4; j++) {
                if (cc[j] == c) {
                    cnts[j]++;
                }
            }
        }
        int maxCnt = 0;
        for (int i = 0; i < 4; i++) {
            maxCnt = Math.max(maxCnt, cnts[i]);
        }
        long numEquals = 0;
        for (int i = 0; i < 4; i++) {
            if (cnts[i] == maxCnt) numEquals++;
        }
        long tot = 1;
        long MOD = (long) Math.pow(10, 9) + 7;
        for (int i = 0; i < N; i++) {
            tot = (tot * numEquals) % MOD;
        }
        ans = tot + "";
        out.printf("%s\n", ans);
    }
}
