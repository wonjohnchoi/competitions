import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int N, M, K;
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        boolean[][] b = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            int x, y;
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
            // i + 1
            if (b[x][y]) continue;
            b[x][y] = true;
            if (check(b, x - 1, y - 1)
                || check(b, x - 1, y)
                || check(b, x, y - 1)
                || check(b, x, y)) {
                out.println(i + 1);
                System.exit(0);
            }
        }
        out.println(0);
    }
    static boolean check(boolean[][] b, int x, int y) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int nx = x + i;
                int ny = y + j;
                if (nx < 0 || nx >= b.length || ny < 0 || ny >= b[0].length)
                    return false;
                if (!b[nx][ny]) return false;
            }
        }
        return true;
    }
}
