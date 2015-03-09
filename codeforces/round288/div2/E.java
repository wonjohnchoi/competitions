import java.util.*;
import java.io.*;
public class E {
    public static PrintStream out = System.out;
    static int[] l, r;
    static int N;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        char[] b = new char[2 * N];
        Arrays.fill(b, ' ');
        l = new int[N];
        r = new int[N];
        cache = new boolean[N + 1][N + 1];
        cached = new boolean[N + 1][N + 1];
        for (int i = 0; i < N; i++) {
            l[i] = sc.nextInt();
            r[i] = sc.nextInt();
        }
        if (!find(0, N)) {
            out.println("IMPOSSIBLE");
        } else {
            find2(0, 0, N, b);
            out.println(new String(b));
        }
    }
    static boolean[][] cache;
    static boolean[][] cached;
    static boolean find(int cur, int use) {
        if (use == 0) return true;
        if (cur >= N) return false;
        if (!cached[cur][use]) {
            boolean good = false;
            for (int rb = l[cur]; rb <= Math.min(r[cur], 2 * use - 1); rb++) {
                if (rb % 2 == 0) continue;
                good |= find(cur + 1, (rb - 1) / 2) && find(cur + 1 + (rb - 1) / 2, use - (rb - 1) / 2 - 1);
            }
            cache[cur][use] = good;
            cached[cur][use] = true;
        }
        return cache[cur][use];
    }
    static void find2(int i, int cur, int use, char[] b) {
        if (use == 0) return;
        for (int rb = l[cur]; rb <= Math.min(r[cur], 2 * use - 1); rb++) {
            if (rb % 2 == 0) continue;
            if (find(cur + 1, (rb - 1) / 2) && find(cur + 1 + (rb - 1) / 2, use - (rb - 1) / 2 - 1)) {
                // out.println(i + " " + (i + rb));
                b[i] = '(';
                b[i + rb] = ')';
                find2(i + 1, cur + 1, (rb - 1) / 2, b);
                find2(i + rb + 1, cur + 1 + (rb - 1) / 2, use - (rb - 1) / 2 - 1, b);
                return;
            }
        }
    }

}
