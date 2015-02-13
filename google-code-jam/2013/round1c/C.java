import java.util.*;
import java.io.*;
public class C {
    public static class Attack implements Comparable<Attack> {
        int d, w, e, s;
        public Attack(int d, int w, int e, int s) {
            this.d = d;
            this.w = w;
            this.e = e;
            this.s = s;
        }
        public String toString() {
            return String.format("d(%d), w(%d), e(%d), s(%d)", d, w, e, s);
        }
        @Override
        public int compareTo(Attack o) {
            return d - o.d;
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            ArrayList<Attack> attacks = new ArrayList<Attack>();
            for (int i = 0; i < N; i++) {
                int d, n, w, e, s, delta_d, delta_p, delta_s;
                d = sc.nextInt();
                n = sc.nextInt();
                w = sc.nextInt();
                e = sc.nextInt();
                s = sc.nextInt();
                delta_d = sc.nextInt();
                delta_p = sc.nextInt();
                delta_s = sc.nextInt();
                for (int j = 0; j < n; j++) {
                    attacks.add(new Attack(d + j * delta_d, w + j * delta_p, e + j * delta_p, s + j * delta_s));
                }
            }
            Collections.sort(attacks);
            // System.out.println("# attacks: " + attacks.size());
            int[] hs = new int[401]; // hs[i] is the height of i - 200.
            int ans = 0;
            while (!attacks.isEmpty()) {
                Attack a = attacks.remove(0);
                ArrayList<Attack> attacksToday = new ArrayList<Attack>();
                attacksToday.add(a);
                while (!attacks.isEmpty() && attacks.get(0).d == a.d) {
                        attacksToday.add(attacks.remove(0));
                }
                // System.out.println("# attack rem: " + attacks.size() + " # attack today: " + attacksToday.size());
                int[] newHs = new int[hs.length];
                System.arraycopy(hs, 0, newHs, 0, hs.length);
                while (!attacksToday.isEmpty()) {
                    a = attacksToday.remove(0);
                    // System.out.println(a);
                    boolean success = false;
                    for (int i = a.w + 200; i < a.e + 200; i++) {
                        if (a.s > hs[i]) {
                            newHs[i] = a.s;
                            success = true;
                        }
                    }
                    if (success) {
                        ans += 1;
                    }
                    // System.out.println(success);
                }
                hs = newHs;
            }
            System.out.printf("Case #%d: %d\n", tc, ans);
        }
    }
}
