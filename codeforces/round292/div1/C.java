import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    static int N;
    static long[] H, SN, SP; // height, sum negative, sum positive (all of size 2N)
    static int[][][] ST; // segment tree ({{max SN i, 2nd SN i}, {max SP i, 2nd SP i}})
    static int[][] query(int a, int b, int l, int r, int idx) {
        if (b < l || a > r) return new int[][] {{-1, -1}, {-1, -1}};
        if (a <= l && r <= b) return ST[idx];
        int h = (l + r) / 2;
        int[][] st1 = query(a, b, l, h, 2 * idx + 1);
        int[][] st2 = query(a, b, h + 1, r, 2 * idx + 2);
        return merge(st1, st2);
    }
    static void set(int l, int r, int idx) {
        if (l == r) {
            ST[idx] = new int[][] {{l, -1}, {l, -1}};
            return;
        }
        int h = (l + r) / 2;
        set(l, h, 2 * idx + 1);
        set(h + 1, r, 2 * idx + 2);
        ST[idx] = merge(ST[2 * idx + 1], ST[2 * idx + 2]);
    }
    static int[][] merge(int[][] st1, int[][] st2) {
        int[][] st = {
            {st1[0][0], st1[0][1], st2[0][0], st2[0][1]},
            {st1[1][0], st1[1][1], st2[1][0], st2[1][1]}
        };
        // out.println("BEFORE: " + Arrays.toString(st[0]) + " " + Arrays.toString(st[1]));
        long[][] SNP = new long[][] {SN, SP};
        int[][] res = new int[2][];
        for (int i = 0; i < 2; i++) {
            int best = -1;
            int best2 = -1;
            for (int j = 0; j < 4; j++) {
                if (st[i][j] == -1) continue;
                long val = SNP[i][st[i][j]];
                if (best == -1) best = st[i][j];
                else if (SNP[i][best] < val) {
                    best2 = best;
                    best = st[i][j];
                } else if (best2 == -1) best2 = st[i][j];
                else if (SNP[i][best2] < val) {
                    best2 = st[i][j];
                }
            }
            res[i] = new int[] {best, best2};
        }
        // out.println("AFTER: " + Arrays.toString(res[0]) + " " + Arrays.toString(res[1]));
        return res;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int M = sc.nextInt();
        H = new long[2 * N];
        SN = new long[2 * N];
        SP = new long[2 * N];
        ST = new int[(int) Math.pow(2, (int) (Math.log(2 * N) / Math.log(2)) + 4)][2][2];
        for (int i = 0; i < ST.length; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(ST[i][j], -1);
            }
        }
        long[] D = new long[2 * N];
        for (int i = 0; i < N; i++) {
            D[i] = sc.nextLong();
            D[i + N] = D[i];
        }
        for (int i = 0; i < N; i++) {
            H[i] = sc.nextLong();
            H[i + N] = H[i];
        }
        long S = 0;
        for (int i = 0; i < SN.length; i++) {
            SN[i] = 2 * H[i] - S;
            SP[i] = 2 * H[i] + S;
            S += D[i];
        }
        set(0, 2 * N - 1, 0);
        // out.println("H: " + Arrays.toString(H));
        // out.println("D: " + Arrays.toString(D));
        // out.println("SN: " + Arrays.toString(SN));
        // out.println("SP: " + Arrays.toString(SP));
        for (int i = 0; i < 32; i++) {
            // out.println("ST: " + Arrays.toString(ST[i]));
        }
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int a2, b2;
            if (a <= b) {
                a2 = b + 1;
                b2 = N + a - 1;
            } else {
                a2 = b + 1;
                b2 = a - 1;
            }
            int[][] res = query(a2, b2, 0, 2 * N - 1, 0);
            // out.println(Arrays.toString(res[0]) + " " + Arrays.toString(res[1]));
            if (res[0][0] != res[1][0]) {
                out.println(SN[res[0][0]] + SP[res[1][0]]);
            } else {
                long best = -1;
                if (res[0][1] != -1) best = Math.max(best, SN[res[0][1]] + SP[res[1][0]]);
                if (res[1][1] != -1) best = Math.max(best, SN[res[0][0]] + SP[res[1][1]]);
                out.println(best);
            }
        }
    }
}
