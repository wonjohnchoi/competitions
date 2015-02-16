import java.util.*;
import java.io.*;
// TIME_USED:
public class A {
    public static boolean DEBUG = true;
    public static void d(Object o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(int o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(long o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(double o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(float o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(boolean o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int[] tot = new int[101];
            char[] sym = new char[tot.length];
            int[][] cnt = new int[N][tot.length];
            boolean lost = false;
            for (int i = 0; i < N; i++) {
                String s = sc.next();
                char prev = ' ';
                int idx = -1;
                for (char c : s.toCharArray()) {
                    if (prev == c) {
                        cnt[i][idx]++;
                        tot[idx]++;
                    } else {
                        prev = c;
                        idx++;
                        cnt[i][idx]++;
                        tot[idx]++;
                        if (i == 0) {
                            sym[idx] = c;
                        } else if (sym[idx] != c) {
                            lost = true;
                        }
                    }
                }
            }
            int[] avg = new int[tot.length];
            for (int i = 0; i < tot.length; i++) {
                avg[i] = (int) Math.round(((double) tot[i]) / N);
            }
            int numChanges = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < tot.length; j++) {
                    if (tot[j] > 0 && cnt[i][j] == 0) {
                        lost = true;
                    }
                    numChanges += Math.abs(cnt[i][j] - avg[j]);
                }
            }
            String ans;
            if (lost) {
                ans = "Fegla Won";
            } else {
                ans = "" + numChanges;
            }
            System.out.printf("Case #%d: %s\n", tc, ans);
        }
    }
}
