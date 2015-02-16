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
    static int L;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N;
            N = sc.nextInt();
            L = sc.nextInt();
            Long[][] vals = new Long[2][N];
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < N; i++) {
                    vals[j][i] = Long.parseLong(sc.next(), 2);
                }
            }
            int minFlip = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                long key = vals[0][0] ^ vals[1][i];
                boolean fail = false;
                for (int j = 0; j < N; j++) {
                    if (j == 0) continue;
                    long search = vals[0][j] ^ key;
                    boolean found = false;
                    for (int k = 0; k < N; k++) {
                        if (search == vals[1][k]) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        fail = true;
                        break;
                    }
                }
                if (!fail) {
                    int flip = 0;
                    for (char c : Long.toBinaryString(key).toCharArray()) {
                        if (c == '1') {
                            flip++;
                        }
                    }
                    minFlip = Math.min(flip, minFlip);
                }
            }

            String output;
            if (minFlip == Integer.MAX_VALUE) {
                output = "NOT POSSIBLE";
            } else {
                output = "" + minFlip;
            }
            System.out.printf("Case #%d: %s\n", tc, output);
        }
    }
}
