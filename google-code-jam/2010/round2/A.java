import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    static int K;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            String ans = "";
            K = sc.nextInt();
            int[][] map = new int[6 * K + 1][6 * K + 1];
            for (int i = 0; i < map.length; i++) {
                Arrays.fill(map[i], -1);
            }
            int x = 2 * K;
            int y = 3 * K - 1;
            int halfWidth = 0;
            for (int i = 0; i < 2 * K - 1; i++) {
                if (i < K) halfWidth++;
                else halfWidth--;
                for (int j = -halfWidth; j < halfWidth + 1; j++) {
                    if ((i + j) % 2 == 0) {
                        map[x + i][y + j] = sc.nextInt();
                    }
                }
                out.println(Arrays.toString(map[x + i]));
            }
            int minVal = Integer.MAX_VALUE;
            for (int i = 2 * K; i < 4 * K - 1; i++) {
                for (int j = 2 * K; j < 4 * K - 1; j++) {
                    out.println(map[i][j] + " :CENTER");
                    minVal = Math.min(minVal, findVal(i, j, map));
                }
            }
            ans += minVal;
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
    static int findVal(int i, int j, int[][] map) {
        int dx = 0;
        int cnt = 0;
        while (i - dx >= 0 && i + dx < map.length) {
            int dy = 0;
            // if ((i + dx) % 2 != 0) continue;
            while (j - dy >= 0 && j + dy < map[i].length) {
                int numMinus = 0;
                // if ((j + dy) % 2 != (K - 1) % 2) continue;
                int[][] coords = new int[][] {{i - dx, j - dy}, {i - dx, j + dy}, {i + dx, j - dy}, {i + dx, j + dy}};
                int val = -2;
                for (int[] coord : coords) {
                    int val2 = map[coord[0]][coord[1]];
                    // out.println("CORD VAL: " + val2);
                    if (val2 == -1) numMinus++;
                    else if (val == -2) val = val2;
                    else if (val != val2) return Integer.MAX_VALUE;
                }
                if (numMinus != 4)
                    cnt += numMinus;
                dy++;
            }
            dx++;
        }
        return cnt;
    }
}
