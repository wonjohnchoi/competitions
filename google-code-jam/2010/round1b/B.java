import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static class C {
        long X, V;
        double t;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            int N;
            long K, B, T;
            N = sc.nextInt();
            K = sc.nextLong();
            B = sc.nextLong();
            T = sc.nextLong();
            C[] c = new C[N];
            for (int i = 0; i < N; i++) {
                c[i] = new C();
                c[i].X = sc.nextLong();
            }
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                c[i].V = sc.nextLong();
                double t = ((double) B - c[i].X) / c[i].V;
                if (T < t) c[i].t = Double.MAX_VALUE;
                else {
                    c[i].t = t;
                    cnt++;
                }
            }
            String ans;
            if (cnt < K) ans = "IMPOSSIBLE";
            else {
                ArrayList<Integer> selected = new ArrayList<Integer>();
                for (int i = N - 1; i >= 0 && selected.size() < K; i--) {
                    if (c[i].t != Double.MAX_VALUE) {
                        selected.add(i);
                    }
                }
                int cnt2 = 0;
                for (Integer i : selected) {
                    for (int j = i + 1; j < N; j++) {
                        if (c[i].t < c[j].t && !selected.contains(j)) {
                            cnt2++;
                        }
                    }
                }
                ans = cnt2 + "";
            }
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
}
