import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int R, C;
            R = sc.nextInt();
            C = sc.nextInt();
            char[][] map = new char[R][C];
            boolean success = true;
            for (int i = 0; i < R; i++) {
                map[i] = sc.next().toCharArray();
            }
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == '#') {
                        if (i + 1 >= R || j + 1 >= C) success = false;
                        else {
                            success &= map[i + 1][j] == '#';
                            success &= map[i][j + 1] == '#';
                            success &= map[i + 1][j + 1] == '#';
                            map[i][j] = '/';
                            map[i][j + 1] = '\\';
                            map[i + 1][j] = '\\';
                            map[i + 1][j + 1] = '/';
                        }
                    }
                }
            }
            String ans = "";
            if (!success) {
                ans = "\nImpossible";
            } else {
                for (int i = 0; i < R; i++) {
                    ans += "\n";
                    for (int j = 0; j < C; j++) {
                        ans += map[i][j];
                    }
                }
            }
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
}
