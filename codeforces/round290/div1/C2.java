import java.util.*;
import java.io.*;
public class C2 {
    // Copied from solution: http://codeforces.com/contest/512/submission/9688144
    static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = sc.nextInt();
        }
        boolean[] prime = new boolean[20001];
        Arrays.fill(prime, true);
        for (int i = 2; i < prime.length; i++) {
            if (prime[i]) {
                for (int j = i * i; j < prime.length; j += i) {
                    prime[j] = false;
                }
            }
        }
        int[][] c = new int[N + 2][N + 2];
        int s = N; int t = N + 1;
        for (int i = 0; i < N; i++) {
            if (a[i] % 2 == 0) {
                c[s][i] = 2;
            } else {
                c[i][t] = 2;
            }
            for (int j = i + 1; j < N; j++) {
                if (prime[a[i] + a[j]] ) {
                    if (a[i] % 2 == 0) {
                        c[i][j] = 1;
                    } else {
                        c[j][i] = 1;
                    }
                }
            }
        }
        int[][] f = new int[N + 2][N + 2];
        if (flow(c, f, s, t) != N) {
            out.println("Impossible");
        } else {
            List<List<Integer>> cycles = new ArrayList<>();
            boolean[] mark = new boolean[N];
            for (int i = 0; i < N; i++) {
                if (!mark[i]) {
                    List<Integer> cycle = new ArrayList<>();
                    int k = i;
                    outer : while (true) {
                        mark[k] = true;
                        cycle.add(k + 1);
                        // out.print(a[k] + " ");
                        for (int j = 0; j < N; j++) {
                            if (!mark[j]
                                && ((a[k] % 2 == 0 && f[k][j] > 0)
                                    || (a[k] % 2 == 1 && f[j][k] > 0))) {
                                k = j;
                                continue outer;
                            }
                        }
                        break;
                    }
                    // out.println();
                    cycles.add(cycle);
                }
            }
            out.println(cycles.size());
            for (List<Integer> cycle : cycles) {
                out.print(cycle.size());
                for (int x : cycle) {
                    out.print(" " + x);
                }
                out.println();
            }
        }
    }
    static int flow(int[][] c, int[][] f, int s, int t) {
        boolean[] mark = new boolean[c.length];
        int res = 0;
        while (dfs(s, t, c, f, mark)) {
            Arrays.fill(mark, false);
            res++;
        }
        // out.println(res + " " + c.length);
        return res;
    }
    static boolean dfs(int at, int t, int[][] c, int[][] f, boolean[] mark) {
        // out.println(at + " " + t);
        if (at == t) return true;
        if (mark[at]) return false;
        mark[at] = true;
        for (int i = 0; i < c.length; i++) {
            if (f[at][i] < c[at][i] && dfs(i, t, c, f, mark)) {
                f[at][i]++;
                f[i][at]--;
                return true;
            }
        }
        return false;
    }
}
