import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            data = new char[N][];
            for (int i = 0; i < N; i++) {
                data[i] = sc.next().toCharArray();
            }
            String ans = "";
            for (int i = 0; i < N; i++) {
                // System.out.println(WP(i) + " " + OWP(i) + " " + OOWP(i));
                ans += "\n" + (0.25 * WP(i) + 0.50 * OWP(i) + 0.25 * OOWP(i));
            }
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
    public static char[][] data;
    public static double WP(int i, int j) {
        int tot = 0;
        int win = 0;
        for (int k = 0; k < data.length; k++) {
            if (k == j) continue;
            if (data[i][k] != '.') tot++;
            if (data[i][k] == '1') win++;
        }
        // System.out.println("i: " + i + " j: " + j + " " + win + "/" + tot);
        return ((double) win) / tot;
    }
    public static double WP(int i) {
        return WP(i, -1);
    }
    public static double OWP(int i) {
        double totWP = 0.0;
        int tot = 0;
        for (int j = 0; j < data.length; j++) {
            if (data[i][j] != '.') {
                tot++;
                totWP += WP(j, i);
            }
        }
        return totWP / tot;
    }
    public static double OOWP(int i) {
        double totOWP = 0.0;
        int tot = 0;
        for (int j = 0; j < data.length; j++) {
            if (data[i][j] != '.') {
                tot++;
                totOWP += OWP(j);
            }
        }
        return totOWP / tot;
    }
}
