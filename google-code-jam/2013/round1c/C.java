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
            ArrayList<Integer> points = new ArrayList<Integer>();
            for (Attack attack : attacks) {
                for (int p : new int[] {attack.w, attack.e}) {
                    points.add(p);
                }                
            }
            // System.out.println("# points deup before: " + points.size());
            
            int idx = 0;
            points = new ArrayList<Integer>(new HashSet<Integer>(points));
            Collections.sort(points);
            System.out.println("Size of points: " + points.size());
            points.add(Integer.MIN_VALUE); // ex) MIN_VALUE -3 0 3 5. each value denotes a start of range. We want a one to one match of each range to an index.
            points.add(Integer.MAX_VALUE); // so points.size() - 1 is # ranges.
            // range at idx : points.get(idx) ~ points.get(idx + 1)
            // idx of range starting with x : int idx = Collections.binarySearch(x)
            // idx of range ending with y : int idx = Collections.binarySearch(y) - 1
            int[] hs = new int[points.size() - 1];
            System.out.println("# attacks: " + attacks.size());
            
            int ans = 0;
            while (!attacks.isEmpty()) {
                Attack a = attacks.remove(0);
                ArrayList<Attack> attacksToday = new ArrayList<Attack>();
                attacksToday.add(a);
                while (!attacks.isEmpty() && attacks.get(0).d == a.d) {
                        attacksToday.add(attacks.remove(0));
                }
                // System.out.println("# attack rem: " + attacks.size() + " # attack today: " + attacksToday.size());
                // test success
                for (int ai = 0; ai < attacksToday.size(); ai++) {
                    a = attacksToday.get(ai);
                    // System.out.println(a);
                    boolean success = false;
                    for (int i = Collections.binarySearch(points, a.w);
                         points.get(i + 1) <= a.e; i++) {
                        if (hs[i] < a.s) {
                            success = true;
                            break;
                        }
                    }
                    if (success) {
                        ans += 1;
                    }
                    // System.out.println(success);
                }

                // apply
                for (int ai = 0; ai < attacksToday.size(); ai++) {
                    a = attacksToday.get(ai);
                    for (int i = Collections.binarySearch(points, a.w);
                         points.get(i + 1) <= a.e; i++) {
                        if (hs[i] < a.s) {
                            hs[i] = Math.max(a.s, hs[i]);
                        }
                    }
                }
            }
            System.out.printf("Case #%d: %d\n", tc, ans);
        }
    }
}
