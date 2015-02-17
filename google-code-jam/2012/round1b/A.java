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
    public static class Candidate {
        int s;
        int i;
        double m;
        public Candidate(int s, int i) {
            this.s = s;
            this.i = i;
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            // value, vote => finalScore
            // J+X*Y (J judge points, X all judge points, Y vote fraction
            Candidate[] candidates = new Candidate[N];
            int X = 0;
            for (int i = 0; i < N; i++) {
                candidates[i] = new Candidate(sc.nextInt(), i);
                X += candidates[i].s;
            }
            Arrays.sort(candidates, new Comparator<Candidate>() {
                    public int compare(Candidate c1, Candidate c2) {
                        return c2.s - c1.s;
                    }
                });
            int used = 0;
            double needed = -1;
            for (int i = 0; i < N; i++) {
                if (needed == -1) {
                    int rem = 2 * X - used;
                    double approxNeeded = ((double) rem) / (N - i);
                    // System.out.println(rem + " " + (N - i) + " " + approxNeeded);
                    if (approxNeeded <= candidates[i].s) {
                        candidates[i].m = 0.0;
                        used += candidates[i].s;
                    } else {
                        needed = approxNeeded;
                    }
                }
                if (needed != -1){
                    // d("needed: " + needed + " ith: " + candidates[i].s + " X: " + X);
                    candidates[i].m = ((double) (needed - candidates[i].s)) / X * 100;
                }
            }
            Arrays.sort(candidates, new Comparator<Candidate>() {
                    public int compare(Candidate c1, Candidate c2) {
                        return c1.i - c2.i;
                    }
                });
            String ans = "";
            for (Candidate c : candidates) {
                if (ans.equals("")) {
                    ans += c.m;
                } else {
                    ans += " " + c.m;
                }
            }
            System.out.printf("Case #%d: %s\n", tc, ans);
        }
    }
}
