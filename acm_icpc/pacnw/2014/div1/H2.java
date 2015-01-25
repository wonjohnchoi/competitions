import java.util.*;
import java.io.*;

public class H2 {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(new FileReader("H.in"));
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            Integer[] S = new Integer[M];
            for (int i = 0; i < M; i++) {
                S[i] = sc.nextInt();
            }

            if (N == 0) {
                System.out.println(0);
                continue;
            }

            int[][] cache = new int[N + 1][N + 2]; // (#pushups, #scores) => total score
            for (int i = 0; i < N + 1; i += 1) {
                Arrays.fill(cache[i], -1);
            }
            cache[0][0] = 0;
            for (int i = 0; i < S.length; i+= 1) {
                if (S[i] < N + 1) {
                    cache[S[i]][1] = S[i];
                }
            }

            for (int i = 1; i < N + 1; i += 1) {
                for (int j = 1; j < N + 1; j += 1) {
                    int bestScore = -1;
                    for (int k = 0; k < S.length; k += 1) {
                        int i2 = i - S[k] * j;
                        int j2 = j - 1;
                        if (i2 >= 0 && cache[i2][j2] != -1) {
                            bestScore = Math.max(bestScore, cache[i2][j2] + S[k]);
                        }
                    }
                    cache[i][j] = bestScore;
                }
            }
            
            int bestScore = -1;
            for (int i = 0; i < N + 1; i += 1) {
                bestScore = Math.max(bestScore, cache[N][i]);
            }
            System.out.println(bestScore);
        }
    } 
}
