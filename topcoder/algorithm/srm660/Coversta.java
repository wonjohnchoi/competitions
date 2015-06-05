import java.util.*;
import java.io.*;
public class Coversta {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    static int[][] aa;
    static int N, M, K;
    static int get(int i, int j) {
        if (inRange(i, j)) {
            return aa[i][j];
        }
        return 0;
    }
    static void set(int i, int j, int k) {
        if (inRange(i, j)) aa[i][j] = k;
    }
    static boolean inRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < M;
    }
    public static int place(String[] a, int[] x, int[] y) {
        N = a.length;
        M = a[0].length();
        K = x.length;
        aa = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                aa[i][j] = (int) a[i].charAt(j) - '0';
            }
        }
        int[][] p = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < K; k++) {
                    p[i][j] += get(i + x[k], j + y[k]);
                }
            }
        }
        int max = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int pp = p[i][j];
                //do
                for (int k = 0; k < K; k++) {
                    for (int k2 = 0; k2 < K; k2++) {
                        int dx = x[k] - x[k2];
                        int dy = y[k] - y[k2];
                        int nx = i + dx;
                        int ny = j + dy;
                        if (!inRange(i + x[k], j + y[k])) continue;
                        if (!inRange(nx, ny)) continue;
                        p[nx][ny] -= aa[i + x[k]][j + y[k]];
                    }
                }
                for (int i2 = 0; i2 < N; i2++) {
                    for (int j2 = 0; j2 < M; j2++) {
                        if (i == i2 && j == j2) continue;
                        int pp2 = pp + p[i2][j2];
                        max = Math.max(max, pp2);
                    }
                }
                //restore
                for (int k = 0; k < K; k++) {
                    for (int k2 = 0; k2 < K; k2++) {
                        int dx = x[k] - x[k2];
                        int dy = y[k] - y[k2];
                        int nx = i + dx;
                        int ny = j + dy;
                        if (!inRange(i + x[k], j + y[k])) continue;
                        if (!inRange(nx, ny)) continue;
                        p[nx][ny] += aa[i + x[k]][j + y[k]];
                    }
                }
            }
        }
        return max;
    }
    public static void main(String args[]) {
    }
}
