import java.util.*;
import java.io.*;
public class BichromePainting {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static String isThatPossible(String[] board, int k){
        int N = board.length;
        char[][] b1 = new char[N][N];
        for (int i = 0; i < N; i++) {
            b1[i] = board[i].toCharArray();
        }
        char[][] b2 = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                b2[i][j] = 'W';
            }
        }
        for (int cnt = 0; cnt < 1000000; ) {
        int[] best = new int[2];
        char bestC = ' ';
        int bestS = 0;
        int i = (int) (Math.random() * N);
        int j = (int) (Math.random() * N);
        cnt++;
        if (b1[i][j] == b2[i][j]) continue;
        int i2 = (int) (Math.random() * k);
        int j2 = (int) (Math.random() * k);
        if (i - i2 < 0 || j - j2 < 0 || i - i2 + k - 1 >= N || j - j2 + k - 1 >= N) continue;
        cnt += N * N;
        /*
        for (int i = 0; i < N - k + 1; i++) {
            // i ~ < i + k
            for (int j = 0; j < N - k + 1; j++) {
                for (char b : new char[] {'B', 'W'}) {
                    int score = 0;
                    for (int l = 0; l < k; l++) {
                        for (int m = 0; m < k; m++) {
                            char bb1 = b1[i + l][j + m];
                            char bb2 = b2[i + l][j + m];
                            if (bb2 != b) {
                                score += bb1 == b ? 1 : -1;
                            }
                        }
                    }
                    if (bestC == ' ' || bestS < score) {
                        best = new int[] {i, j};
                        bestC = b;
                        bestS = score;
                    }
                }
            }
            }*/
        for (int l = 0; l < k; l++) {
            for (int m = 0; m < k; m++) {
                b2[i - i2 + l][j - j2 + m] = b1[i][j];
            }
        }/*
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                out.print(b2[i][j]);
            }
            out.println();
        }out.println();
         */
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (b1[i][j] != b2[i][j]) return "Impossible";
            }
        }
        return "Possible";
    }
    public static void main(String args[]) {
        out.println(isThatPossible(new String[] {"BBBB", "BBWW", "BBWW", "WWBW"}, 2));
    }
}
