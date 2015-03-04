import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    static int N, M;
    static char[][] map;
    static int[][] fourSideDelta = {
        {1, 0}, {-1, 0}, {0, 1}, {0,-1}
    };
    static int[][][] deltas = {
        {{1, 0}, {-1, 0}, {0, 1}},
        {{1, 0}, {-1, 0}, {0, -1}},
        {{1, 0}, {0, -1}, {0, 1}},
        {{-1, 0}, {0, -1}, {0, 1}}
    };
    static int[][] rests = {
        {0, -1},
        {0, 1},
        {-1, 0},
        {1, 0}
    };
    static char[][] syms = {
        {'>', '<'},
        {'<', '>'},
        {'v', '^'},
        {'^', 'v'}
    };
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String ans = "";
        N = sc.nextInt();
        M = sc.nextInt();
        map = new char[N][];
        for (int i = 0; i < N; i++) {
            String S = sc.next();
            map[i] = S.toCharArray();
        }
        boolean fail = false;
        loop : for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != '.') continue;
                if (!fill(i, j)) {
                    fail = true;
                    break loop;
                }
            }
        }
        loop : for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '.') {
                    fail = true;
                    break loop;
                }
            }
        }
        if (fail) out.println("Not unique");
        else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    out.print(map[i][j]);
                }
                out.println();
            }
        }
    }
    static boolean fill(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M) return true;
        if (map[x][y] != '.') return true;
        for (int i = 0; i < deltas.length; i++) {
            int[][] delta = deltas[i];
            int[] rest = rests[i];
            char[] sym = syms[i];
            boolean isOpen = false;
            for (int k = 0; k < delta.length; k++) {
                int i2 = delta[k][0] + x;
                int j2 = delta[k][1] + y;
                if (i2 < 0 || j2 < 0 || i2 >= N || j2 >= M) {
                    continue;
                }
                if (map[i2][j2] == '.') isOpen = true;
            }
            if (!isOpen) {
                if (x + rest[0] < 0 || y + rest[1] < 0 || x + rest[0] >= N || y + rest[1] >= M
                    || map[x + rest[0]][y + rest[1]] != '.')
                    return false;
                map[x][y] = sym[0];
                map[x + rest[0]][y + rest[1]] = sym[1];
                for (int[] fourSide : fourSideDelta) {
                    if (!fill(x + rest[0] + fourSide[0], y + rest[1] + fourSide[1]))
                        return false;
                }
                break;
            }
        }
        return true;
    }
}
