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
            // 0 0 ... 0 0 (length L)
            // N lines.
            ArrayList<ArrayList<String>> from, to;
            from = new ArrayList<ArrayList<String>>();
            to = new ArrayList<ArrayList<String>>();
            from.add(new ArrayList<String>());
            to.add(new ArrayList<String>());

            for (int i = 0; i < N; i++) {
                from.get(0).add(sc.next());
            }
            for (int i = 0; i < N; i++) {
                to.get(0).add(sc.next());
            }
            int ans = find(from, to, 0);
            String output;
            if (ans == Integer.MAX_VALUE) {
                output = "NOT POSSIBLE";
            } else {
                output = "" + ans;
            }
            System.out.printf("Case #%d: %s\n", tc, output);
        }
    }
    public static int find(ArrayList<ArrayList<String>> from, ArrayList<ArrayList<String>> to, int i) {
        int ans = Integer.MAX_VALUE;
        if (i == L) return 0;
        for (int k = 0; k < 2; k++) {
            System.gc();
            ArrayList<ArrayList<String>> newFrom = new ArrayList<ArrayList<String>>();
            ArrayList<ArrayList<String>> newTo = new ArrayList<ArrayList<String>>();
            boolean fail = false;
            for (int j = 0; j < from.size(); j++) {
                ArrayList<String> from0 = new ArrayList<String>();
                ArrayList<String> from1 = new ArrayList<String>();
                ArrayList<String> to0 = new ArrayList<String>();
                ArrayList<String> to1 = new ArrayList<String>();
                for (String s : from.get(j)) {
                    if (s.charAt(i) == '0') from0.add(s);
                    else from1.add(s);
                }
                for (String s : to.get(j)) {
                    if (s.charAt(i) == '0') to0.add(s);
                    else to1.add(s);
                }
                if (k == 0) {
                    if (from0.size() == to0.size()) {
                        newFrom.add(from0);
                        newTo.add(to0);
                        newFrom.add(from1);
                        newTo.add(to1);
                    } else {
                        fail = true;
                        break;
                    }
                } else {
                    if (from0.size() == to1.size()) {
                        newFrom.add(from0);
                        newTo.add(to1);
                        newFrom.add(from1);
                        newTo.add(to0);
                    } else {
                        fail = true;
                        break;
                    }
                }
            }
            if (!fail) {
                int val = find(newFrom, newTo, i + 1);
                if (val != Integer.MAX_VALUE) {
                    ans = Math.min(ans, val + k);
                }
            }
        }
        return ans;
    }

}
