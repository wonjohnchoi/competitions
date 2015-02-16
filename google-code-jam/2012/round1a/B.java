import java.util.*;
import java.io.*;
// TIME_USED:
public class B {
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
            int[][] needed = new int[N][2];
            for (int i = 0; i < N; i++) {
                needed[i][0] = sc.nextInt();
                needed[i][1] = sc.nextInt();
            }
            int totCleared = 0;
            int totPlayed = 0;
            int stars = 0;
            while (totCleared < 2 * N) {
                boolean found = false;
                for (int i = 0; i < N; i++) {
                    if (needed[i][1] != -1 && needed[i][1] <= stars) {
                        needed[i][1] = -1;
                        stars++;
                        totCleared++;
                        if (needed[i][0] != -1) {
                            needed[i][0] = -1;
                            stars++;
                            totCleared++;
                        }
                        totPlayed++;
                        found = true;
                    }
                }
                if (found) continue;
                int bestI = -1;
                for (int i = 0; i < N; i++) {
                    if (needed[i][0] != -1 && needed[i][0] <= stars) {
                        if (bestI == -1) {
                            bestI = i;
                        } else if (needed[bestI][1] < needed[i][1]) {
                            bestI = i;
                        }
                    }
                }
                if (bestI != -1) {
                    needed[bestI][0] = -1;
                    stars++;
                    totCleared++;
                    totPlayed++;
                } else {
                    stars = -1;
                    break;
                }
            }
            String ans;
            if (stars == -1) {
                ans = "Too Bad";
            } else {
                ans = totPlayed + "";
            }
            System.out.printf("Case #%d: %s\n", tc, ans);
        }
    }
}
