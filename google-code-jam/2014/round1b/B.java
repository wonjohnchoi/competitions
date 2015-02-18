import java.util.*;
import java.io.*;
// TIME_USED:
public class B {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            long A, B, K;
            A = sc.nextLong();
            B = sc.nextLong();
            K = sc.nextLong();
            cache = new long[40][2][2][2];
            cached = new boolean[40][2][2][2];
            long ans = find(39, false, false, false, A, B, K);
            out.printf("Case #%d: %d\n", tc, ans);
        }
    }
    public static long[][][][] cache;
    public static boolean[][][][] cached;
    public static int getBit(int i, long a) {
        return (int) ((a >> i) & 1);
    }
    public static long find(int i, boolean lessA, boolean lessB, boolean lessK, long a, long b, long k) {
        if (i == -1) {
            return (lessA && lessB && lessK) ? 1 : 0;
        }
        int j, h, l;
        j = lessA ? 1 : 0;
        h = lessB ? 1 : 0;
        l = lessK ? 1 : 0;
        if (!cached[i][j][h][l]) {
            boolean maxA = getBit(i, a) == 1 || lessA;
            boolean maxB = getBit(i, b) == 1 || lessB;
            boolean maxK = getBit(i, k) == 1 || lessK;
            long ret = 0;
            ret += find(i - 1, maxA, maxB, maxK, a, b, k);
            if (maxA) {
                ret += find(i - 1, lessA, maxB, maxK, a, b, k);
            }
            if (maxB) {
                ret += find(i - 1, maxA, lessB, maxK, a, b, k);
            }
            if (maxA && maxB && maxK) {
                ret += find(i - 1, lessA, lessB, lessK, a, b, k);
            }
            cache[i][j][h][l] = ret;
            cached[i][j][h][l] = true;
        }
        return cache[i][j][h][l];
    }
}
