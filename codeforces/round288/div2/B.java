import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String S = sc.next();
        int last = (int) (S.charAt(S.length() - 1) - '0');

        for (int i = 0; i < S.length(); i++) {
            int cur = (int) (S.charAt(i) - '0');
            if (cur % 2 == 1) continue;
            if (cur < last) {
                out.println(ns(S, i));
                System.exit(0);
            }
        }
        for (int i = 0; i < S.length(); i++) {
            int cur = (int) (S.charAt(i) - '0');
            if (cur % 2 == 1) continue;
            if (cur == last) {
                out.println(ns(S, i));
                System.exit(0);
            }
        }
        for (int i = S.length() - 1; i >= 0; i--) {
            int cur = (int) (S.charAt(i) - '0');
            if (cur % 2 == 1) continue;
            if (cur > last) {
                out.println(ns(S, i));
                System.exit(0);
            }
        }
        out.println(-1);
    }
    static String ns(String S, int i) {
        // out.println(S + " " + i);
        String ret = S;
        ret = ns(ret, S.length() - 1, S.charAt(i));
        ret = ns(ret, i, S.charAt(S.length() - 1));
        return ret;
    }
    static String ns(String S, int i, char nc) {
        // out.println(S + " " + i + " " + nc);
        String left = i == 0 ? "" : S.substring(0, i);
        String right = i == S.length() - 1 ? "" : S.substring(i + 1, S.length());
        return left + nc + right;
    }
}
