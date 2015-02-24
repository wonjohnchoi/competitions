import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static int N, K;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            K = sc.nextInt();
            char[][] map = new char[N][];
            for (int i = 0; i < N; i++) {
                map[i] = sc.next().toCharArray();
            }
            for (int c = N - 2; c >= 0; c--) {
                for (int r = 0; r < N; r++) {
                    if (map[r][c] != '.') {
                        int c2 = c + 1;
                        while (c2 < N && map[r][c2] == '.') {
                            c2++;
                        }
                        c2--;
                        if (c != c2) {
                            map[r][c2] = map[r][c];
                            map[r][c] = '.';
                        }
                    }
                }
            }
            boolean red = false;
            red |= check(map, 'R', 1, 1);
            red |= check(map, 'R', 1, -1);
            red |= check(map, 'R', 0, 1);
            red |= check(map, 'R', 1, 0);
            boolean blue = false;
            blue |= check(map, 'B', 1, 1);
            blue |= check(map, 'B', 1, -1);
            blue |= check(map, 'B', 0, 1);
            blue |= check(map, 'B', 1, 0);
            String ans;
            if (red && blue) ans = "Both";
            else if (red) ans = "Red";
            else if (blue) ans = "Blue";
            else ans = "Neither";
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
    public static boolean check(char[][] map, char c, int dx, int dy) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (check(map, c, dx, dy, i, j)) return true;
            }
        }
        return false;
    }
    public static boolean check(char[][] map, char c, int dx, int dy, int i, int j) {
        for (int k = 0; k < K; k++, i += dx, j += dy) {
            if (i < 0 || j < 0 || i >= N || j >= N) return false;
            if (map[i][j] != c) return false;
        }
        return true;
    }
}
