import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            int M, N;
            M = sc.nextInt();
            N = sc.nextInt();
            char[][] board = new char[M][];
            for (int i = 0; i < M; i++) {
                String s = "";
                String hex = sc.next();
                for (int j = 0; j < N / 4; j++) {
                    String bin = Integer.toBinaryString(Integer.parseInt(hex.charAt(j) + "", 16));
                    while (bin.length() < 4) bin = "0" + bin;
                    s += bin;
                }
                board[i] = s.toCharArray();
            }
            List<Integer> sizes = new ArrayList<Integer>();
            List<Integer> cnts = new ArrayList<Integer>();
            for (int i = (int) Math.min(M, N); i >= 1; i--) {
                int cnt = 0;
                for (int j = 0; j < M - i + 1; j++) {
                    for (int k = 0; k < N - i + 1; k++) {
                        if (check(board, j, k, i)) {
                            cnt++;
                        }
                    }
                }
                if (cnt != 0) {
                    sizes.add(i);
                    cnts.add(cnt);
                }
            }
            String ans = sizes.size() + "";
            for (int i = 0; i < sizes.size(); i++) {
                ans += "\n" + sizes.get(i) + " " + cnts.get(i);
            }
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
    static boolean check(char[][] board, int a, int b, int size) {
        char col = ' ';
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (col == ' ') {
                    col = board[i + a][j + b];
                    if (col == 'x') return false;
                }
                else {
                    char c = (i + j) % 2 == 0 ? col : (col == '1' ? '0' : '1');
                    if (c != board[i + a][j + b]) return false;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i + a][j + b] = 'x';
            }
        }
        return true;
    }
}
