import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    static int D, I, M, N;
    static int[] cache;
    static int[] pixels;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            D = sc.nextInt();
            I = sc.nextInt();
            M = sc.nextInt();
            N = sc.nextInt();
            pixels = new int[N];
            for (int i = 0; i < N; i++) pixels[i] = sc.nextInt();
            int minVal = Integer.MAX_VALUE;
            for (int i = 0; i <= 255; i++) {
                cache = new int[300 * 300];
                Arrays.fill(cache, -1);
                int val = N == 0 ? 0 : find(i, 0);
                if (val < minVal) minVal = val;
            }
            String ans = minVal + "";
            // System.out.println(D + " " + I + " " + M + " " + N);
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
    static int find(int lastVal, int i) {
        if (i == N) return 0;
        int hash = lastVal * 300 + i;
        if (cache[hash] == -1) {
            int res = Integer.MAX_VALUE;
            res = Math.min(res, find(lastVal, i + 1) + D);
            if (Math.abs(lastVal - pixels[i]) <= M)
                res = Math.min(res, find(pixels[i], i + 1));
            else {
                if (lastVal > pixels[i] + M) {
                    int lastVal2 = Math.max(lastVal - M, 0);
                    if (M != 0) res = Math.min(res, find(lastVal - M, i) + I);
                    res = Math.min(res, find(lastVal - M, i + 1) + lastVal - M - pixels[i]);
                } else {
                    if (M != 0) res = Math.min(res, find(lastVal + M, i) + I);
                    res = Math.min(res, find(lastVal + M, i + 1) + pixels[i] - lastVal - M);
                }
            }
            cache[hash] = res;
        }
        return cache[hash];
    }
}
