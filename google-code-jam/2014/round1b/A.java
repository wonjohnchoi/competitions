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
        int MAXS = 101;
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            char[] sym = new char[MAXS];
            int[][] cnt = new int[N][MAXS];
            int SIZE = 0;
            boolean lost = false;
            for (int i = 0; i < N; i++) {
                String s = sc.next();
                char prev = ' ';
                int idx = -1;
                for (char c : s.toCharArray()) {
                    if (prev == c) {
                        cnt[i][idx]++;
                    } else {
                        prev = c;
                        idx++;
                        cnt[i][idx]++;
                        if (i == 0) {
                            sym[idx] = c;
                            SIZE = idx + 1;
                        } else if (sym[idx] != c) {
                            lost = true;
                        }
                    }
                }
                if (i != 0 && idx + 1 != SIZE) {
                    lost = true;
                }
            }
            int tot = 0;
            for (int i = 0; i < SIZE; i++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < 200; k++) {
                    int tmp = 0;
                    for (int j = 0; j < N; j++) {
                        tmp += Math.abs(cnt[j][i] - k);
                    }
                    min = Math.min(min, tmp);
                }
                tot += min;
            }
            String ans;
            if (lost) {
                ans = "Fegla Won";
            } else {
                ans = "" + tot;
            }
            System.out.printf("Case #%d: %s\n", tc, ans);
        }
    }
}
