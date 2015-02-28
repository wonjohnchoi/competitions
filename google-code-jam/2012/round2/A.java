import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    static class Vine {
        int d, i, power;
        public Vine(int d, int i) {
            this.d = d;
            this.i = i;
            power = -1;
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            int N = sc.nextInt();
            ArrayList<Vine> vines = new ArrayList<Vine>();
            for (int i = 0; i < N; i++) {
                vines.add(new Vine(sc.nextInt(), sc.nextInt()));
            }
            int D = sc.nextInt();
            vines.get(0).power = vines.get(0).d;
            vines.add(new Vine(D, 0));
            for (int i  = 0; i < vines.size(); i++) {
                if (vines.get(i).power == -1) continue;
                // out.println(vines.get(i).power + vines.get(i).d);
                int reach = vines.get(i).d + vines.get(i).power;
                for (int j = i + 1; j < vines.size(); j++) {
                    if (vines.get(j).d > reach) break;
                    int a = vines.get(j).d - vines.get(i).d;
                    int b = vines.get(j).i;
                    vines.get(j).power = Math.max(vines.get(j).power, (int) Math.min(a, b));
                }
            }
            String ans = "" + (vines.get(vines.size() - 1).power != -1 ? "YES" : "NO");
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
}
