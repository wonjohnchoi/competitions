import java.util.*;
import java.io.*;

public class B {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(new FileReader("B-large.in"));
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            P = sc.nextInt();
            Q = sc.nextInt();
            N = sc.nextInt();
            H = new int[N];
            G = new int[N];

            for (int n = 0; n < N; n++) {
                H[n] = sc.nextInt();
                G[n] = sc.nextInt();
            }
            
            cache = new int[201][N + 1][N * 10 + 1];
            for (int i = 0; i < cache.length; i++) {
                for (int j = 0; j < cache[0].length; j++) {
                    Arrays.fill(cache[i][j], -1);
                }
            }

            System.out.printf("Case #%d: %d\n", t, maxGold(H[0], 0, 1));
        }
    }

    static int P, Q, N;
    static int[] H, G;
    static int[][][] cache;
    static int maxGold(int h, int i, int e) {
        // System.out.printf("%d %d %d\n", h, i, e);
        if (h <= 0 && i + 1 == N) {
            return 0;
        }
        if (h <= 0) {
            return maxGold(H[i + 1], i + 1, e);
        }
        if (cache[h][i][e] == -1) {
            int best = -1;
            if (e >= 1) {
                int gold = h <= P ? G[i] : 0;
                best = gold + maxGold(h - P, i, e - 1);
            }
            best = Math.max(maxGold(h - Q, i, e + 1), best);
            cache[h][i][e] = best;
        }
        // System.out.printf("%d %d %d %d\n", h, i, e, cache[h][i][e]);
        return cache[h][i][e];
    }
}
